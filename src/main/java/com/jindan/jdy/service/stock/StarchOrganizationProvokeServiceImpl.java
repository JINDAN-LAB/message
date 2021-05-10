package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationProvokeDto;
import com.jindan.jdy.mapper.StarchOrganizationProvokeMapper;
import com.jindan.jdy.mapper.StarchOrganizationPutMapper;
import com.jindan.jdy.mapper.StarchProvokePutDao;
import com.jindan.jdy.common.pojo.StarchOrganizationProvoke;
import com.jindan.jdy.common.pojo.StarchProvokePut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(资产调拨服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationProvokeServiceImpl  extends ServiceImpl<StarchOrganizationProvokeMapper,StarchOrganizationProvoke> implements StarchOrganizationProvokeService  {

    @Autowired
    StarchOrganizationProvokeMapper starchManageCheckDao;

    @Autowired
    StarchProvokePutDao starchProvokePutDao;

    @Autowired
    StarchOrganizationPutMapper starchOrganizationPutDao;


    @Override
    public Page<StarchOrganizationProvoke> queryCatList(StarchOrganizationProvokeDto jdyRole) {
        Page<StarchOrganizationProvoke> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchOrganizationProvoke> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getTid() !=null &&  !jdyRole.getTid().isEmpty()){
            queryWrapper.eq("tid",jdyRole.getTid());
        }
        if( jdyRole.getStatus() !=null && !jdyRole.getStatus().isEmpty() ){
            queryWrapper.like("status",jdyRole.getStatus());
        }
        if( jdyRole.getAllotNumber() !=null && !jdyRole.getAllotNumber().isEmpty() ){
            queryWrapper.like("allot_number",jdyRole.getAllotNumber());
        }
        if( jdyRole.getAllotTime() !=null && !jdyRole.getAllotTime().isEmpty() ){
            queryWrapper.like("allot_time",jdyRole.getAllotTime());
        }
        if( jdyRole.getAllotManager() !=null && !jdyRole.getAllotManager().isEmpty() ){
            queryWrapper.like("allot_manager",jdyRole.getAllotManager());
        }
        if( jdyRole.getAllotCompany() !=null ){
            queryWrapper.like("allot_company",jdyRole.getAllotCompany());
        }
        if( jdyRole.getFoldDate() !=null && !jdyRole.getFoldDate().isEmpty() ){
            queryWrapper.like("fold_date",jdyRole.getFoldDate());
        }
        if( jdyRole.getFoldManagers() !=null && !jdyRole.getFoldManagers().isEmpty() ){
            queryWrapper.like("fold_managers",jdyRole.getFoldManagers());
        }
        if( jdyRole.getFoldCompany() !=null && !jdyRole.getFoldCompany().isEmpty() ){
            queryWrapper.like("fold_company",jdyRole.getFoldCompany());
        }
        if( jdyRole.getFoldExplain() !=null && !jdyRole.getFoldExplain().isEmpty() ){
            queryWrapper.like("fold_explain",jdyRole.getFoldExplain());
        }
        if( jdyRole.getFoldPropertyId() !=null && !jdyRole.getFoldPropertyId().isEmpty() ){
            queryWrapper.like("fold_property_id",jdyRole.getFoldPropertyId());
        }
        return (Page<StarchOrganizationProvoke>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationProvokeDto departmentSuggestDto) {

        StarchOrganizationProvoke starchOrganizationAccess =new StarchOrganizationProvoke();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.updateById(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                Map<String,Object> map =new HashMap<>();
                map.put("put_id",departmentSuggestDto.getTid());
                starchProvokePutDao.deleteByMap(map);
                StarchProvokePut starchAccessPut =new StarchProvokePut();
                starchAccessPut.setProvokeId(starchOrganizationAccess.getTid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchProvokePutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }



    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationProvokeDto departmentSuggestDto) {

       StarchOrganizationProvoke starchOrganizationAccess =new StarchOrganizationProvoke();
        try{
            BeanUtils.copyProperties(departmentSuggestDto,starchOrganizationAccess);
        }catch(Exception e){
            e.printStackTrace();
        }
        if(starchManageCheckDao.insert(starchOrganizationAccess) > 0){
            for (int i = 0; i < departmentSuggestDto.getPutList().size(); i++) {
                starchOrganizationPutDao.updateById(departmentSuggestDto.getPutList().get(i));
                StarchProvokePut starchAccessPut =new StarchProvokePut();
                starchAccessPut.setProvokeId(starchOrganizationAccess.getTid());
                starchAccessPut.setPutId(departmentSuggestDto.getPutList().get(i).getOid());
                starchProvokePutDao.insert(starchAccessPut);
            }
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<StarchOrganizationProvokeDto> queryRelevanceCatList(StarchOrganizationProvokeDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());
        List<StarchOrganizationProvokeDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyFlowCatalog);
        PageInfo<StarchOrganizationProvokeDto> pageInfo = new PageInfo<StarchOrganizationProvokeDto>(iPage);
        return pageInfo;
    }

    @Override
    public boolean removeDetailsById(String id) {
        starchManageCheckDao.deleteById(id);
        Map hap =new HashMap();
        hap.put("provoke_id",id);
        starchProvokePutDao.deleteByMap(hap);
        return true;
    }


}