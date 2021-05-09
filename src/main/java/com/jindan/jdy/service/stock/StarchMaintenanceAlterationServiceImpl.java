package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintenanceAlterationDto;
import com.jindan.jdy.common.mapper.StarchAlterationPutDao;
import com.jindan.jdy.common.mapper.StarchMaintenanceAlterationMapper;
import com.jindan.jdy.common.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.common.pojo.StarchAlterationPut;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产维保信息变更服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchMaintenanceAlterationServiceImpl  extends ServiceImpl<StarchMaintenanceAlterationMapper,StarchMaintenanceAlteration> implements StarchMaintenanceAlterationService  {

    @Autowired
    StarchMaintenanceAlterationMapper starchMaintenanceAlterationDao;

    @Autowired
    StarchAlterationPutDao starchAlterationPutDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutDao;

    @Override
    public PageInfo<StarchMaintenanceAlterationDto> queryCatList(StarchMaintenanceAlterationDto jdyRole) {


        PageHelper.startPage(jdyRole.getCurrentPage(), jdyRole.getPageSize());

        List<StarchMaintenanceAlterationDto> iPage =  starchMaintenanceAlterationDao.queryRelevanceCatList(jdyRole);

        PageInfo<StarchMaintenanceAlterationDto> pageInfo = new PageInfo<StarchMaintenanceAlterationDto>(iPage);

        return pageInfo;
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchMaintenanceAlterationDto departmentSuggestDto) {

        StarchMaintenanceAlteration starchOrganizationAccess =new StarchMaintenanceAlteration();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchMaintenanceAlterationDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {

                Map<String,Object> map =new HashMap<>();
                map.put("put_id",departmentSuggestDto.getWid());
                starchAlterationPutDao.deleteByMap(map);
                StarchAlterationPut starchAccessPut =new StarchAlterationPut();
                starchAccessPut.setAlterationId(starchOrganizationAccess.getWid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchAlterationPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchMaintenanceAlterationDto departmentSuggestDto) {
        StarchMaintenanceAlteration starchOrganizationAccess =new StarchMaintenanceAlteration();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        int i1 = starchMaintenanceAlterationDao.insert(starchOrganizationAccess);
        if(i1 > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++){
               starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                Map<String,Object> map =new HashMap<>();
                map.put("put_id",departmentSuggestDto.getPutList().get(i).getOid());
                starchAlterationPutDao.deleteByMap(map);
                StarchAlterationPut starchAccessPut =new StarchAlterationPut();
                starchAccessPut.setAlterationId(starchOrganizationAccess.getWid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchAlterationPutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeDetailsById(String id) {
        starchMaintenanceAlterationDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("alteration_id",id);
        starchAlterationPutDao.deleteByMap(hap);
        return true;
    }



}