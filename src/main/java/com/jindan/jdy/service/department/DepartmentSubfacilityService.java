package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DepartmentSubfacilityDto;
import com.jindan.jdy.common.pojo.DepartmentSubfacility;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DepartmentSubfacilityService extends IService<DepartmentSubfacility> {

    Page<DepartmentSubfacility> seleteDepartmentSubfacility(DepartmentSubfacilityDto departmentSuggestDto);

}