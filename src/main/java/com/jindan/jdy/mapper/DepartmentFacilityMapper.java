package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface DepartmentFacilityMapper extends BaseMapper<DepartmentFacility> {

    List<DepartmentFacilityDto> seleteDepartmentFacility(DepartmentFacilityDto departmentSuggestDto);
}
