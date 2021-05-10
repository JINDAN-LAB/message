package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.UserPermissionDto;
import com.jindan.jdy.common.pojo.UserRolePermission;
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
public interface UserRolePermissionMapper extends BaseMapper<UserRolePermission> {

    List<UserPermissionDto> seleteUserRolelist(UserRolePermission userRolePermission);

    List<UserPermissionDto> seleteUserPermission(UserRolePermission userRolePermission);

}
