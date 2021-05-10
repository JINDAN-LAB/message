package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAlterationDto;
import com.jindan.jdy.mapper.StarchOrganizationAlterationMapper;
import com.jindan.jdy.common.pojo.StarchOrganizationAlteration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产信息变更服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchOrganizationAlterationServiceImpl  extends ServiceImpl<StarchOrganizationAlterationMapper,StarchOrganizationAlteration> implements StarchOrganizationAlterationService  {

    @Autowired
    StarchOrganizationAlterationMapper starchManageCheckDao;

    @Override
    public Page<StarchOrganizationAlteration> queryCatList(StarchOrganizationAlterationDto jdyRole) {
        Page<StarchOrganizationAlteration> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchOrganizationAlteration> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getAid() !=null &&  !jdyRole.getAid().isEmpty()){
            queryWrapper.eq("aid",jdyRole.getAid());
        }
        if( jdyRole.getAstatus() !=null && !jdyRole.getAstatus().isEmpty() ){
            queryWrapper.like("astatus",jdyRole.getAstatus());
        }
        if( jdyRole.getAlterationNumber() !=null && !jdyRole.getAlterationNumber().isEmpty() ){
            queryWrapper.like("alteration_number",jdyRole.getAlterationNumber());
        }
        if( jdyRole.getAlterationDate() !=null && !jdyRole.getAlterationDate().isEmpty() ){
            queryWrapper.like("alteration_date",jdyRole.getAlterationDate());
        }
        if( jdyRole.getAlterationPerson() !=null && !jdyRole.getAlterationPerson().isEmpty() ){
            queryWrapper.like("alteration_person",jdyRole.getAlterationPerson());
        }
        if( jdyRole.getAlterationType() !=null ){
            queryWrapper.like("alteration_type",jdyRole.getAlterationType());
        }
        if( jdyRole.getAlterationName() !=null && !jdyRole.getAlterationName().isEmpty() ){
            queryWrapper.like("alteration_name",jdyRole.getAlterationName());
        }
        if( jdyRole.getAlterationEares() !=null && !jdyRole.getAlterationEares().isEmpty() ){
            queryWrapper.like("alteration_eares",jdyRole.getAlterationEares());
        }
        if( jdyRole.getDepositRegion() !=null && !jdyRole.getDepositRegion().isEmpty() ){
            queryWrapper.like("deposit_region",jdyRole.getDepositRegion());
        }
        if( jdyRole.getUseCompany() !=null && !jdyRole.getUseCompany().isEmpty() ){
            queryWrapper.like("use_company",jdyRole.getUseCompany());
        }
        if( jdyRole.getUseDepartment() !=null && !jdyRole.getUseDepartment().isEmpty() ){
            queryWrapper.like("use_department",jdyRole.getUseDepartment());
        }
        if( jdyRole.getUsePerson() !=null && !jdyRole.getUsePerson().isEmpty() ){
            queryWrapper.like("use_person",jdyRole.getUsePerson());
        }
        if( jdyRole.getRemarks() !=null && !jdyRole.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",jdyRole.getRemarks());
        }
        if( jdyRole.getParentId() !=null && !jdyRole.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",jdyRole.getParentId());
        }
        return (Page<StarchOrganizationAlteration>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchOrganizationAlteration warehouseDepository) {
        return starchManageCheckDao.updateById(warehouseDepository)> 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchOrganizationAlteration departmentSuggestDto) {
        return starchManageCheckDao.insert(departmentSuggestDto) > 0;
    }


    @Override
    public PageInfo<StarchOrganizationAlterationDto> queryRelevanceCatList(StarchOrganizationAlterationDto jdyFlowCatalog) {
        PageHelper.startPage(jdyFlowCatalog.getCurrentPage(), jdyFlowCatalog.getPageSize());

        List<StarchOrganizationAlterationDto> iPage =  starchManageCheckDao.queryRelevanceCatList(jdyFlowCatalog);

        PageInfo<StarchOrganizationAlterationDto> pageInfo = new PageInfo<StarchOrganizationAlterationDto>(iPage);

        return pageInfo;

    }


}