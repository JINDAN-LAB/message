package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyCompanyDto;
import com.jindan.jdy.common.pojo.JdyDepartments;
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
public interface JdyDepartmentsMapper extends BaseMapper<JdyDepartments> {

    List<JdyCompanyDto> seleteJdyCompanylist(Object o);
}
