package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.UserRoleDto;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.UserRole;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserRoleDto> seleteAllUserRolelist(UserRole userRole);

    List<UserPermission> seleteRolePermission(JdyRole userRole);
}
