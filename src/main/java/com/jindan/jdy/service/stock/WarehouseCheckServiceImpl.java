package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.mapper.WarehouseCheckDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(盘点单服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WarehouseCheckServiceImpl  extends ServiceImpl<WarehouseCheckDao,WarehouseCheck> implements WarehouseCheckService  {

    @Autowired
    WarehouseCheckDao warehouseCheckDao;

    @Override
    public Page<WarehouseCheck> seletelist(WarehouseCheckDto departmentSuggestDto) {

        Page<WarehouseCheck>  page = new Page<>(1,200);
        QueryWrapper<WarehouseCheck> queryWrapper =new QueryWrapper<>();
        if(departmentSuggestDto.getCheckId() !=null &&  !departmentSuggestDto.getCheckId().isEmpty()  ){
            queryWrapper.eq("check_id",departmentSuggestDto.getCheckId());
        }
        if(departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty() ){
            queryWrapper.eq("parent_id",departmentSuggestDto.getParentId());
        }
        if(departmentSuggestDto.getCheckNum() !=null && departmentSuggestDto.getCheckNum() > 0 ){
            queryWrapper.eq("check_num",departmentSuggestDto.getCheckNum());
        }
        if(departmentSuggestDto.getCheckTime() !=null && !departmentSuggestDto.getCheckTime().isEmpty() ){
            queryWrapper.eq("check_time",departmentSuggestDto.getCheckTime());
        }
        if(departmentSuggestDto.getCheckPersons() !=null && !departmentSuggestDto.getCheckPersons().isEmpty() ){
            queryWrapper.eq("check_persons",departmentSuggestDto.getCheckPersons());
        }
        return (Page<WarehouseCheck>) warehouseCheckDao.selectPage(page,queryWrapper);
    }

}