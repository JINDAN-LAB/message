package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsExcle;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsReultDto;
import com.jindan.jdy.mapper.JdyAppletFoodSafetyProblemsReultMapper;
import com.jindan.jdy.mapper.JdyAppletFootSafetyPersonMapper;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import com.jindan.jdy.service.config.SentSmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**   
 * @Description:TODO(食品安全小程序服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFoodSafetyProblemsReultServiceImpl  extends ServiceImpl<JdyAppletFoodSafetyProblemsReultMapper,JdyAppletFoodSafetyProblemsReult> implements JdyAppletFoodSafetyProblemsReultService  {


    @Autowired
    JdyAppletFoodSafetyProblemsReultMapper jdyAppletFoodSafetyProblemsDao;

    @Autowired
    JdyAppletFootSafetyPersonMapper jdyAppletFootSafetyPersonDao;


    @Override
    public List<JdyAppletFoodSafetyProblemsReult> seleteList(JdyAppletFoodSafetyProblemsReult domesticFahuoDto) {
        QueryWrapper<JdyAppletFoodSafetyProblemsReult> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getRid() !=null && !domesticFahuoDto.getRid().isEmpty()){
            queryWrapper.eq("rid",domesticFahuoDto.getRid());
        }
        if( domesticFahuoDto.getContents() !=null && !domesticFahuoDto.getContents().isEmpty()  ){
            queryWrapper.eq("contents",domesticFahuoDto.getContents());
        }
        if( domesticFahuoDto.getParentId() !=null && domesticFahuoDto.getParentId() != null ){
            queryWrapper.eq("parent_id",domesticFahuoDto.getParentId());
        }
        if( domesticFahuoDto.getNormalTime() !=null && !domesticFahuoDto.getNormalTime().isEmpty()){
            queryWrapper.eq("normal_time",domesticFahuoDto.getNormalTime());
        }
        if( domesticFahuoDto.getResultTimes() !=null && !domesticFahuoDto.getResultTimes().isEmpty()  ){
            queryWrapper.eq("result_times",domesticFahuoDto.getResultTimes());
        }
        if( domesticFahuoDto.getRpersons() !=null && domesticFahuoDto.getRpersons() != null ){
            queryWrapper.eq("rpersons",domesticFahuoDto.getRpersons());
        }
        if( domesticFahuoDto.getTijiaoMulv() !=null && domesticFahuoDto.getTijiaoMulv() != null ){
            queryWrapper.eq("tijiao_mulv",domesticFahuoDto.getTijiaoMulv());
        }
        if( domesticFahuoDto.getResultPerson() !=null && domesticFahuoDto.getResultPerson() != null ){
            queryWrapper.eq("result_person",domesticFahuoDto.getResultPerson());
        }
        queryWrapper.eq("delete_id","0").orderByDesc("result_times");
        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }

    @Async
    @Override
    public void sentSms(JdyAppletFoodSafetyProblemsReult users) {
       List<JdyAppletFootSafetyPerson> jdys = jdyAppletFootSafetyPersonDao.selectSMSList(users.getParentId());
       if(jdys.size() > 0){
        String[] phoneNumbers = new String[jdys.size()];
        String[] templateParams = new String[jdys.size()];
        for (int i = 0; i < jdys.size() ; i++) {
            phoneNumbers[i] = "+86"+jdys.get(i).getPassword();
        }
        if(jdys.size() > 0){
            templateParams[0] ="用户";
            SentSmsUtils.SentFasongSms("问题提交","1400408129","679489",phoneNumbers,  templateParams);
        }
       }
    }
    @Override
    public List<JdyAppletFoodSafetyProblemsReult> seleteProList() {
        QueryWrapper<JdyAppletFoodSafetyProblemsReult> queryWrapper =new QueryWrapper<>();
        queryWrapper.isNotNull("contents");
        queryWrapper.isNull("result_content");
        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }
    @Override
    public List<JdyAppletFoodSafetyProblemsReultDto> seletePreProList() {
        return jdyAppletFoodSafetyProblemsDao.seletePreProList();
    }
    @Override
    public List<JdyAppletFoodSafetyProblemsReultDto> seletePreLvzhiProList() {
        return jdyAppletFoodSafetyProblemsDao.seletePreLvzhiProList();
    }

    @Override
    public void sentPersonSms(String[] phoneNumbers) {
        String[] templateParams = new String[phoneNumbers.length];
        templateParams[0] = "用户";
        SentSmsUtils.SentFasongSms(UUID.randomUUID().toString().replaceAll("-",""),"1400408129","679489",phoneNumbers,  templateParams);
    }


    @Override
    public List<JdyAppletFoodSafetyProblemsReult> seleteWentiList(JdyAppletFoodSafetyProblemsReult domesticFahuoDto) {
        String datas ="";
        if( domesticFahuoDto.getResultTimes() !=null && !domesticFahuoDto.getResultTimes().isEmpty()){
              datas = domesticFahuoDto.getResultTimes();
        }else{
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            Date date =  new Date();
            long time = date.getTime();
            time = time - 7*24*60*60*1000;
            Date date2 =  new Date(time);
            datas =  df.format(date2);
        }
        QueryWrapper<JdyAppletFoodSafetyProblemsReult> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getRid() !=null && !domesticFahuoDto.getRid().isEmpty()){
            queryWrapper.eq("rid",domesticFahuoDto.getRid());
        }
        if( domesticFahuoDto.getContents() !=null && !domesticFahuoDto.getContents().isEmpty()){
            queryWrapper.eq("contents",domesticFahuoDto.getContents());
        }
        if( domesticFahuoDto.getParentId() !=null && domesticFahuoDto.getParentId() != null ){
            queryWrapper.eq("parent_id",domesticFahuoDto.getParentId());
        }
        if( domesticFahuoDto.getNormalTime() !=null && !domesticFahuoDto.getNormalTime().isEmpty()){
            queryWrapper.eq("normal_time",domesticFahuoDto.getNormalTime());
        }
        if( domesticFahuoDto.getResultTimes() !=null && !domesticFahuoDto.getResultTimes().isEmpty()){
            queryWrapper.ge("result_times",datas);
        }
        if(domesticFahuoDto.getRpersons() !=null && domesticFahuoDto.getRpersons() != null){
            queryWrapper.eq("rpersons",domesticFahuoDto.getRpersons());
        }
        if( domesticFahuoDto.getResultPerson() !=null && domesticFahuoDto.getResultPerson() != null ){
            queryWrapper.eq("result_person",domesticFahuoDto.getResultPerson());
        }
        queryWrapper.eq("delete_id","0");
        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAlllist(JdyAppletFootSafetyPerson param) {

        List<JdyAppletFootSafetyPerson> password = jdyAppletFootSafetyPersonDao.selectList(new QueryWrapper<JdyAppletFootSafetyPerson>().eq("password", param));
        if(password.size() > 0){
          switch (password.get(0).getQuanxian()){
            case "班组":
                return jdyAppletFoodSafetyProblemsDao.seleteAlllBanzuist(param.getBanzu());
            case "车间":
                return jdyAppletFoodSafetyProblemsDao.seleteAlllChejianist(param.getChejian());
            case "总监":
                return jdyAppletFoodSafetyProblemsDao.seleteAlllZongjianist(param.getZongjianid());
            case "公司":
                return jdyAppletFoodSafetyProblemsDao.seleteAllGongsilist(param.getZongjianid());
            default:
                return null;
         }
       }
      return  null;
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAllSinglelist(JdyAppletFootSafetyPerson param) {

        return jdyAppletFoodSafetyProblemsDao.seleteAllSinglelist(param.getPassword());
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuSinglelist(List<String>   param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllBanzuSinglelist(param);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuchuliSinglelist(List<String>   param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllBanzuchuliSinglelist(param);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAllChejianSinglelist(List<String>   param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllChejianSinglelist(param);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsExcle> seleteAllWaibuChejianSinglelist(List<String> param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllWaibuChejianSinglelist(param);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsReultDto> seleteAllZognjianSinglelist(JdyAppletFoodSafetyProblemsReult param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllZognjianSinglelist(param);
    }

    @Override
    public List<JdyAppletFoodSafetyProblemsReultDto> seleteAllGongsiSinglelist(JdyAppletFoodSafetyProblemsReult  param) {
        return jdyAppletFoodSafetyProblemsDao.seleteAllGongsiSinglelist(param);
    }

}