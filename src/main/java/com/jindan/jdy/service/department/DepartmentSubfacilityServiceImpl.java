package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DepartmentSubfacilityDto;
import com.jindan.jdy.common.pojo.DepartmentSubfacility;
import com.jindan.jdy.common.mapper.DepartmentSubfacilityMapper;
import com.jindan.jdy.common.pojo.ZxingErwei;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class DepartmentSubfacilityServiceImpl  extends ServiceImpl<DepartmentSubfacilityMapper,DepartmentSubfacility> implements DepartmentSubfacilityService  {

    @Autowired
    DepartmentSubfacilityMapper departmentSubfacilityMapper;

    @Override
    public Page<DepartmentSubfacility> seleteDepartmentSubfacility(DepartmentSubfacilityDto departmentSuggestDto) {
        if(departmentSuggestDto.getCurrentPage() <= 0  || departmentSuggestDto.getPageSize()  <= 0){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<DepartmentSubfacility> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
        QueryWrapper<DepartmentSubfacility> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getBrand() !=null &&  departmentSuggestDto.getBrand()!= ""  ){
            queryWrapper.like("brand",departmentSuggestDto.getBrand());
        }
        if( departmentSuggestDto.getModel() !=null && !departmentSuggestDto.getModel().isEmpty() ){
            queryWrapper.like("model",departmentSuggestDto.getModel());
        }
        if( departmentSuggestDto.getId() !=null &&  departmentSuggestDto.getId()!= ""  ){
            queryWrapper.eq("id",departmentSuggestDto.getId());
        }
        if( departmentSuggestDto.getPowers() !=null && !departmentSuggestDto.getPowers().isEmpty() ){
            queryWrapper.like("powers",departmentSuggestDto.getPowers());
        }
        if( departmentSuggestDto.getLubrication() !=null &&  departmentSuggestDto.getLubrication()!= ""  ){
            queryWrapper.like("lubrication",departmentSuggestDto.getLubrication());
        }
        if( departmentSuggestDto.getDepartFacilityId() !=null &&  departmentSuggestDto.getDepartFacilityId()!= ""  ){
            queryWrapper.like("depart_facility_id",departmentSuggestDto.getDepartFacilityId());
        }
        return (Page)departmentSubfacilityMapper.selectPage(page,queryWrapper);
    }
}