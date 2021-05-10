package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchPropertyScrapDto;
import com.jindan.jdy.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.mapper.StarchPropertyScrapMapper;
import com.jindan.jdy.mapper.StarchScrapPutDao;
import com.jindan.jdy.common.pojo.StarchPropertyScrap;
import com.jindan.jdy.common.pojo.StarchScrapPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(清理报废服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
@Slf4j
public class StarchPropertyScrapServiceImpl  extends ServiceImpl<StarchPropertyScrapMapper,StarchPropertyScrap> implements StarchPropertyScrapService  {

    @Autowired
    StarchPropertyScrapMapper starchManageCheckDao;

    @Autowired
    StarchScrapPutDao starchScrapPutDao;
    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutDao;

    @Override
    public Page<StarchPropertyScrap> queryCatList(StarchPropertyScrapDto jdyRole) {
        Page<StarchPropertyScrap> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchPropertyScrap> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getBid() !=null &&  !jdyRole.getBid().isEmpty()){
            queryWrapper.eq("bid",jdyRole.getBid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getClearNumber() !=null && !jdyRole.getClearNumber().isEmpty() ){
            queryWrapper.like("clear_number",jdyRole.getClearNumber());
        }
        if( jdyRole.getClearDate() !=null && !jdyRole.getClearDate().isEmpty() ){
            queryWrapper.like("clear_date",jdyRole.getClearDate());
        }
        if( jdyRole.getClearPerson() !=null && !jdyRole.getClearPerson().isEmpty() ){
            queryWrapper.like("clear_person",jdyRole.getClearPerson());
        }
        if( jdyRole.getClearExplain() !=null && !jdyRole.getClearExplain().isEmpty() ){
            queryWrapper.like("clear_explain",jdyRole.getClearExplain());
        }
        if( jdyRole.getParentId() !=null && !jdyRole.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyRole.getParentId());
        }
        return (Page<StarchPropertyScrap>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchPropertyScrapDto departmentSuggestDto) {

        StarchPropertyScrap starchOrganizationAccess =new StarchPropertyScrap();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if( starchManageCheckDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                Map<String,Object> map =new HashMap<>();
                map.put("put_id",departmentSuggestDto.getBid());
                starchScrapPutDao.deleteByMap(map);
                StarchScrapPut starchAccessPut =new StarchScrapPut();
                starchAccessPut.setScrapId(starchOrganizationAccess.getBid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchScrapPutDao.insert(starchAccessPut);
            }
            return true;
        }
      return false;
    }
    @Override
    public boolean addJdyFlowCatalog(StarchPropertyScrapDto departmentSuggestDto) {
        StarchPropertyScrap starchOrganizationAccess =new StarchPropertyScrap();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
            log.debug("starchOrganizationAccess",starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if( starchManageCheckDao.insert(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                StarchScrapPut starchAccessPut =new StarchScrapPut();
                starchAccessPut.setScrapId(starchOrganizationAccess.getBid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchScrapPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
     }

    @Override
    public PageInfo<StarchPropertyScrapDto> queryRelevanceCatList(StarchPropertyScrapDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchPropertyScrapDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchPropertyScrapDto> pageInfo = new PageInfo<StarchPropertyScrapDto>(iPage);
        return pageInfo;
    }

    @Override
    public boolean removeDetailsById(String id) {
        starchManageCheckDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("scrap_id",id);
        starchScrapPutDao.deleteByMap(hap);
        return true;
    }


}