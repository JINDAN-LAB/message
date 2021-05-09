package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.StarchManageCheckDto;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import com.jindan.jdy.common.pojo.StarchManageCheck;
import com.jindan.jdy.common.mapper.StarchManageCheckDao;
import com.jindan.jdy.service.stock.StarchManageCheckService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(盘点管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchManageCheckServiceImpl  extends ServiceImpl<StarchManageCheckDao,StarchManageCheck> implements StarchManageCheckService  {

    @Autowired
    StarchManageCheckDao starchManageCheckDao;

    @Override
    public Page<StarchManageCheck> queryCatList(StarchManageCheckDto jdyRole) {
        Page<StarchManageCheck> iPage =new Page<>(jdyRole.getCurrentPage(),jdyRole.getPageSize());
        QueryWrapper<StarchManageCheck> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getCid() !=null &&  !jdyRole.getCid().isEmpty()){
            queryWrapper.eq("cid",jdyRole.getCid());
        }
        if( jdyRole.getCheckName() !=null && !jdyRole.getCheckName().isEmpty() ){
            queryWrapper.like("check_name",jdyRole.getCheckName());
        }
        if( jdyRole.getCheckTakeStock() !=null && !jdyRole.getCheckTakeStock().isEmpty() ){
            queryWrapper.like("check_take_stock",jdyRole.getCheckTakeStock());
        }
        if( jdyRole.getCreckPerson() !=null && !jdyRole.getCreckPerson().isEmpty() ){
            queryWrapper.like("creck_person",jdyRole.getCreckPerson());
        }
        if( jdyRole.getCreationDate() !=null && !jdyRole.getCreationDate().isEmpty() ){
            queryWrapper.like("creation_date",jdyRole.getCreationDate());
        }
        if( jdyRole.getCreationStatus() !=null ){
            queryWrapper.like("creation_status",jdyRole.getCreationStatus());
        }
        if( jdyRole.getCheckCompany() !=null && !jdyRole.getCheckCompany().isEmpty() ){
            queryWrapper.like("check_company",jdyRole.getCheckCompany());
        }
        if( jdyRole.getBelongCompany() !=null && !jdyRole.getBelongCompany().isEmpty() ){
            queryWrapper.like("belong_company",jdyRole.getBelongCompany());
        }
        if( jdyRole.getZichanType() !=null && !jdyRole.getZichanType().isEmpty() ){
            queryWrapper.like("zichan_type",jdyRole.getZichanType());
        }
        if( jdyRole.getQuyu() !=null && !jdyRole.getQuyu().isEmpty() ){
            queryWrapper.like("quyu",jdyRole.getQuyu());
        }
        if( jdyRole.getManagers() !=null && !jdyRole.getManagers().isEmpty() ){
            queryWrapper.like("managers",jdyRole.getManagers());
        }
        return (Page<StarchManageCheck>) starchManageCheckDao.selectPage(iPage,queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchManageCheck warehouseDepository) {
        return starchManageCheckDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchManageCheck departmentSuggestDto) {
        return starchManageCheckDao.insert(departmentSuggestDto) > 0;
    }


}