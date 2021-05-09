package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.mapper.JdyUserMapper;
import com.jindan.jdy.common.mapper.StarchMaintainRegisterMapper;
import com.jindan.jdy.common.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.mapper.StarchRegisterPutMapper;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.pojo.StarchRegisterPut;
import com.jindan.jdy.service.config.CommonUtils;
import com.jindan.jdy.wechat.AccessSmsXiaoxi;
import com.jindan.jdy.wechat.Datas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**   
 * @Description:TODO(资产维修登记服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchMaintainRegisterServiceImpl  extends ServiceImpl<StarchMaintainRegisterMapper,StarchMaintainRegister> implements StarchMaintainRegisterService  {

    @Autowired
    StarchMaintainRegisterMapper starchMaintainRegisterDao;

    @Autowired
    StarchRegisterPutMapper starchRegisterPutDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutDao;

    @Autowired
    JdyUserMapper jdyUserDto;

    @Override
    public Page<StarchMaintainRegister> queryCatList(StarchMaintainRegisterDto jdyRole) {

        Page<StarchMaintainRegister> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchMaintainRegister> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getWxid() !=null &&  jdyRole.getWxid().isEmpty() ){
            queryWrapper.eq("wxid",jdyRole.getWxid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getWxstatus() !=null && !jdyRole.getWxstatus().isEmpty() ){
            queryWrapper.like("wxstatus",jdyRole.getWxstatus());
        }
        if( jdyRole.getServiceNumber() !=null && !jdyRole.getServiceNumber().isEmpty() ){
            queryWrapper.like("service_number",jdyRole.getServiceNumber());
        }
        if( jdyRole.getImgurl() !=null && !jdyRole.getImgurl().isEmpty() ){
            queryWrapper.like("imgurl",jdyRole.getImgurl());
        }
        if( jdyRole.getServiceDate() !=null ){
            queryWrapper.like("service_date",jdyRole.getServiceDate());
        }
        if( jdyRole.getServicePerson() !=null && !jdyRole.getServicePerson().isEmpty() ){
            queryWrapper.like("service_person",jdyRole.getServicePerson());
        }
        if( jdyRole.getWarrantyPerson() !=null && !jdyRole.getWarrantyPerson().isEmpty() ){
            queryWrapper.like("warranty_person",jdyRole.getWarrantyPerson());
        }
        if( jdyRole.getServiceJine() !=null && !jdyRole.getServiceJine().isEmpty() ){
            queryWrapper.like("service_jine",jdyRole.getServiceJine());
        }
        if( jdyRole.getServiceContent() !=null && !jdyRole.getServiceContent().isEmpty() ){
            queryWrapper.like("service_content",jdyRole.getServiceContent());
        }
        return (Page<StarchMaintainRegister>) starchMaintainRegisterDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchMaintainRegisterDto departmentSuggestDto) {
        StarchMaintainRegister  starchOrganizationAccess =new StarchMaintainRegister();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchMaintainRegisterDao.updateById(starchOrganizationAccess) > 0){
            for(int i = 0; i < departmentSuggestDto.getPutList().size(); i++){
                starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                QueryWrapper<StarchRegisterPut> queryWrapper =new QueryWrapper<>();
                queryWrapper.eq("register_id",departmentSuggestDto.getWxid());
                int i1 = starchRegisterPutDao.delete(queryWrapper);
                StarchRegisterPut starchAccessPut =new StarchRegisterPut();
                starchAccessPut.setRegisterId(starchOrganizationAccess.getWxid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchRegisterPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }
    @Override
    public boolean addJdyFlowCatalog(StarchMaintainRegisterDto departmentSuggestDto) {
     if(departmentSuggestDto.getPutList().get(0).getWeixiuren() !=null){
        StarchMaintainRegister  starchOrganizationAccess = new StarchMaintainRegister();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
            starchOrganizationAccess.setServicePerson(departmentSuggestDto.getPutList().get(0).getWeixiuren());
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchMaintainRegisterDao.insert(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                StarchRegisterPut starchAccessPut = new StarchRegisterPut();
                starchAccessPut.setRegisterId(starchOrganizationAccess.getWxid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchRegisterPutDao.insert(starchAccessPut);
                QueryWrapper<JdyUser> queryWrapper =new QueryWrapper<>();
                queryWrapper.eq("username",departmentSuggestDto.getPutList().get(i).getWeixiuren());
                List<JdyUser> jdyUsers = jdyUserDto.selectList(queryWrapper);
                for (int j = 0; j < jdyUsers.size() ; j++) {
                    if(jdyUsers.size() > 0){
                    if(jdyUsers.size() > 0){
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                                RestTemplate restTemplate=new RestTemplate();
                                Map<String,String> params=new HashMap<>();
                                params.put("appid","wxb5b6dfa9e8b12f50");
                                params.put("secret","6da768a60cdd737cc30c32134d7071c6");
                                ResponseEntity<Object> responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}",Object.class,params);
                                Map<String,String>  maps = (Map<String, String>) responseEntity.getBody();
                                System.out.println(maps.get("access_token"));
                                RestTemplate restTemplates=new RestTemplate();
                                AccessSmsXiaoxi accessSmsXiaoxi =new AccessSmsXiaoxi();
                                accessSmsXiaoxi.setTouser(jdyUsers.get(j).getWecharId());
                                accessSmsXiaoxi.setTemplate_id("sAD1h93Zje9tvfzYeNJScrR0ahU2ZBLjwhOPsB-mqoA");
                                Map<String, Datas> data  = new HashMap<>();
                                Datas datas =new Datas();
                                datas.setValue(departmentSuggestDto.getPutList().get(0).getPropertyName());
                                datas.setColor("FF0000");
                                data.put("first",datas);
                                Datas data1 =new Datas();
                                data1.setValue(departmentSuggestDto.getPutList().get(0).getStoreAreas());
                                data1.setColor("#000000");
                                data.put("keyword1",data1);
                                Datas data2 =new Datas();
                                data2.setValue(departmentSuggestDto.getPutList().get(0).getPropertyName());
                                data2.setColor("#000000");
                                data.put("keyword2",data2);
                                Datas data3 =new Datas();
                                data3.setValue(departmentSuggestDto.getServiceContent());
                                data3.setColor("#000000");
                                data.put("keyword3",data3);
                                Datas data4 =new Datas();
                                data4.setValue(departmentSuggestDto.getPtype());
                                data4.setColor("#000000");
                                data.put("keyword4",data4);
                                Datas data5 =new Datas();
                                data5.setValue(departmentSuggestDto.getServiceDate());
                                data5.setColor("#000000");
                                data.put("keyword5",data5);
                                Datas data6=new Datas();
                                data6.setValue("位号为："+departmentSuggestDto.getPutList().get(0).getWeihao());
                                data6.setColor("#000000");
                                data.put("remark",data6);
                                accessSmsXiaoxi.setData(data);
                                //  问题项啥需要处理
                                ResponseEntity<String> responseEntityss=restTemplates.postForEntity("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+maps.get("access_token"),accessSmsXiaoxi,String.class); //提交的body内容为user对象，请求的返回的body类型为String
                                String body=responseEntityss.getBody();
                        }
                    }
                }
            }
            return true;
          }
        return false;
        }
        return false;
    }

    @Override
    public PageInfo<StarchMaintainRegisterDto> queryRelevanceCatList(StarchMaintainRegisterDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchMaintainRegisterDto> iPage =  starchMaintainRegisterDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchMaintainRegisterDto> pageInfo = new PageInfo<StarchMaintainRegisterDto>(iPage);
        return pageInfo;
    }

    @Override
    public boolean removeDetailsById(String id) {
        starchMaintainRegisterDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("register_id",id);
        starchRegisterPutDao.deleteByMap(hap);
        return true;
    }

    @Override
    public List<StarchMaintainRegister> seletByZichan(String id) {
        return starchMaintainRegisterDao.seletByZichan(id);
    }

    @Override
    public List<StarchOrganizationPut> seleteOrganizationPut(StarchOrganizationPut starchOrganizationPut) throws Exception {
        List<StarchOrganizationPut>  dtoListceshi  =new ArrayList<>();
        List<StarchOrganizationPut>  dtoList = starchOrganizationPutDao.seleteOrganizationPut(starchOrganizationPut);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String createTime = dateFormat.format(now);//格式化然后放入字符串中
        for (int i = 0; i < dtoList.size() ; i++){
            if(CommonUtils.getDistanceDays(createTime,dtoList.get(i).getShangcibaoyang()) > Integer.valueOf(dtoList.get(i).getMaintainCycle())){
                dtoListceshi.add(dtoList.get(i));
                dtoList.get(i).setStatus("待检修");
                starchOrganizationPutDao.updateById(dtoList.get(i));
            }
        }
        return dtoList;
    }
    



    @Override
    public boolean addJdyaddFlowCatalog(StarchMaintainRegisterDto departmentSuggestDto) {
        StarchMaintainRegister  starchOrganizationAccess =new StarchMaintainRegister();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
            starchOrganizationAccess.setPtypes("3");
        }catch(Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {

        }
        
        if(starchMaintainRegisterDao.insert(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++){
                StarchRegisterPut starchAccessPut = new StarchRegisterPut();
                starchAccessPut.setRegisterId(starchOrganizationAccess.getWxid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchRegisterPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<StarchMaintainRegister> queryWBaoxiurenRelevanceCatList(StarchMaintainRegisterDto jdyRole) {
        Page<StarchMaintainRegister> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchMaintainRegister> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getWxid() !=null &&  jdyRole.getWxid().isEmpty() ){
            queryWrapper.eq("wxid",jdyRole.getWxid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getWxstatus() !=null && !jdyRole.getWxstatus().isEmpty() ){
            queryWrapper.like("wxstatus",jdyRole.getWxstatus());
        }
        if( jdyRole.getServiceNumber() !=null && !jdyRole.getServiceNumber().isEmpty() ){
            queryWrapper.like("service_number",jdyRole.getServiceNumber());
        }
        if( jdyRole.getImgurl() !=null && !jdyRole.getImgurl().isEmpty() ){
            queryWrapper.like("imgurl",jdyRole.getImgurl());
        }
        if( jdyRole.getServiceDate() !=null ){
            queryWrapper.like("service_date",jdyRole.getServiceDate());
        }
        if( jdyRole.getServicePerson() !=null && !jdyRole.getServicePerson().isEmpty() ){
            queryWrapper.like("service_person",jdyRole.getServicePerson());
        }
        if( jdyRole.getWarrantyPerson() !=null && !jdyRole.getWarrantyPerson().isEmpty() ){
            queryWrapper.like("warranty_person",jdyRole.getWarrantyPerson());
        }
        if( jdyRole.getServiceJine() !=null && !jdyRole.getServiceJine().isEmpty() ){
            queryWrapper.like("service_jine",jdyRole.getServiceJine());
        }
        if( jdyRole.getServiceContent() !=null && !jdyRole.getServiceContent().isEmpty() ){
            queryWrapper.like("service_content",jdyRole.getServiceContent());
        }
        queryWrapper.isNull("evaluate_baoxiu");
        return (PageInfo<StarchMaintainRegister>) starchMaintainRegisterDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public PageInfo<StarchMaintainRegister> queryWeixiurenRelevanceCatList(StarchMaintainRegisterDto jdyRole) {
        Page<StarchMaintainRegister> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchMaintainRegister> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getWxid() !=null &&  jdyRole.getWxid().isEmpty() ){
            queryWrapper.eq("wxid",jdyRole.getWxid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getWxstatus() !=null && !jdyRole.getWxstatus().isEmpty() ){
            queryWrapper.like("wxstatus",jdyRole.getWxstatus());
        }
        if( jdyRole.getServiceNumber() !=null && !jdyRole.getServiceNumber().isEmpty() ){
            queryWrapper.like("service_number",jdyRole.getServiceNumber());
        }
        if( jdyRole.getImgurl() !=null && !jdyRole.getImgurl().isEmpty() ){
            queryWrapper.like("imgurl",jdyRole.getImgurl());
        }
        if( jdyRole.getServiceDate() !=null ){
            queryWrapper.like("service_date",jdyRole.getServiceDate());
        }
        if( jdyRole.getServicePerson() !=null && !jdyRole.getServicePerson().isEmpty() ){
            queryWrapper.like("service_person",jdyRole.getServicePerson());
        }
        if( jdyRole.getWarrantyPerson() !=null && !jdyRole.getWarrantyPerson().isEmpty() ){
            queryWrapper.like("warranty_person",jdyRole.getWarrantyPerson());
        }
        if( jdyRole.getServiceJine() !=null && !jdyRole.getServiceJine().isEmpty() ){
            queryWrapper.like("service_jine",jdyRole.getServiceJine());
        }
        if( jdyRole.getServiceContent() !=null && !jdyRole.getServiceContent().isEmpty() ){
            queryWrapper.like("service_content",jdyRole.getServiceContent());
        }
        queryWrapper.isNull("evaluate_weixiu");
        return (PageInfo<StarchMaintainRegister>) starchMaintainRegisterDao.selectPage(iPage,queryWrapper);

    }

    @Override
    public boolean updateSingleJdyFlowCatalog(StarchMaintainRegister departmentSuggestDto) {
        return starchMaintainRegisterDao.updateById(departmentSuggestDto) > 0;
    }


    @Override
    public boolean updateFenpeiMaintainRegister(StarchMaintainRegister warehouseDepository) {
        QueryWrapper<JdyUser> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("username",warehouseDepository.getWxPersons());
        List<JdyUser> jdyUsers = jdyUserDto.selectList(queryWrapper);
        StarchOrganizationPut  starchOrganizationPut = starchRegisterPutDao.getStringputDetails(warehouseDepository.getParentId());
        for (int i = 0; i <jdyUsers.size() ; i++){
            RestTemplate restTemplate=new RestTemplate();
            Map<String,String> params=new HashMap<>();
            params.put("appid","wxb5b6dfa9e8b12f50");
            params.put("secret","6da768a60cdd737cc30c32134d7071c6");
            ResponseEntity<Object> responseEntity = restTemplate.getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}",Object.class,params);
            Map<String,String>  maps = (Map<String, String>) responseEntity.getBody();
            System.out.println(maps.get("access_token"));
            RestTemplate restTemplates=new RestTemplate();
            AccessSmsXiaoxi accessSmsXiaoxi =new AccessSmsXiaoxi();
            accessSmsXiaoxi.setTouser(jdyUsers.get(i).getWecharId());
            accessSmsXiaoxi.setTemplate_id("sAD1h93Zje9tvfzYeNJScrR0ahU2ZBLjwhOPsB-mqoA");
            Map<String, Datas> data  = new HashMap<>();
            Datas datas =new Datas();
            datas.setValue(starchOrganizationPut.getPropertyName());
            datas.setColor("FF0000");
            data.put("first",datas);
            Datas data1 =new Datas();
            data1.setValue(starchOrganizationPut.getStoreAreas());
            data1.setColor("#000000");
            data.put("keyword1",data1);
            Datas data2 =new Datas();
            data2.setValue(starchOrganizationPut.getPropertyName());
            data2.setColor("#000000");
            data.put("keyword2",data2);
            Datas data3 =new Datas();
            data3.setValue(warehouseDepository.getServiceContent());
            data3.setColor("#000000");
            data.put("keyword3",data3);
            Datas data4 =new Datas();
            data4.setValue(warehouseDepository.getPtype());
            data4.setColor("#000000");
            data.put("keyword4",data4);
            Datas data5 =new Datas();
            data5.setValue(warehouseDepository.getServiceDate());
            data5.setColor("#000000");
            data.put("keyword5",data5);
            Datas data6=new Datas();
            data6.setValue("位号为："+starchOrganizationPut.getWeihao());
            data6.setColor("#000000");
            data.put("remark",data6);
            accessSmsXiaoxi.setData(data);
            ResponseEntity<String> responseEntityss=restTemplates.postForEntity("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+maps.get("access_token"),accessSmsXiaoxi,String.class); //提交的body内容为user对象，请求的返回的body类型为String
            String body=responseEntityss.getBody();
        }
        return starchMaintainRegisterDao.updateById(warehouseDepository) > 0;
    }


}