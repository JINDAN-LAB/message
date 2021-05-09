package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DepartmentFacilityService extends IService<DepartmentFacility> {

//    List<DepartmentFacility> seletelist(DepartmentFacilityDto departmentSuggestDto);

    PageInfo<DepartmentFacilityDto> seleteDepartmentFacility(DepartmentFacilityDto departmentSuggestDto);

    List<DepartmentFacilityDto> exportExclelist(DepartmentFacilityDto param);

    DepartmentFacility insertSave(DepartmentFacility userPermission);
}