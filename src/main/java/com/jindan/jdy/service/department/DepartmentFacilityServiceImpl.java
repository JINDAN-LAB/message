package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.mapper.DepartmentFacilityMapper;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class DepartmentFacilityServiceImpl  extends ServiceImpl<DepartmentFacilityMapper,DepartmentFacility> implements DepartmentFacilityService  {

    @Autowired
    DepartmentFacilityMapper departmentFacilityDto;

    @Override
    public PageInfo<DepartmentFacilityDto> seleteDepartmentFacility(DepartmentFacilityDto departmentSuggestDto) {
        PageHelper.startPage(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
         List<DepartmentFacilityDto> departmentFacilityDtos = departmentFacilityDto.seleteDepartmentFacility(departmentSuggestDto);
        PageInfo<DepartmentFacilityDto> pageInfo = new PageInfo<DepartmentFacilityDto>(departmentFacilityDtos);
        return pageInfo;
    }

    @Override
    public List<DepartmentFacilityDto> exportExclelist(DepartmentFacilityDto param) {
        return departmentFacilityDto.seleteDepartmentFacility(param);
    }

    @Override
    public DepartmentFacility insertSave(DepartmentFacility userPermission) {
        int insert = departmentFacilityDto.insert(userPermission);
        return userPermission;
    }
}