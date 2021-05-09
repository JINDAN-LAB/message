package com.jindan.jdy.service.user;

import com.jindan.jdy.common.dto.UserPermissionDto;
import com.jindan.jdy.common.pojo.UserRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface UserRolePermissionService extends IService<UserRolePermission> {

    List<UserPermissionDto> seleteUserRolelist(UserRolePermission userRolePermission);

    List<UserPermissionDto> seleteUserPermission(UserRolePermission userRolePermission);

    boolean deleteRolePermission(UserRolePermission userRolePermission);
}