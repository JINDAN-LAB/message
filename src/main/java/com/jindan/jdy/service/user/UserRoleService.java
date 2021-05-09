package com.jindan.jdy.service.user;

import com.jindan.jdy.common.dto.UserRoleDto;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface UserRoleService extends IService<UserRole> {

    List<UserRoleDto> seleteAllUserRolelist(UserRole userRole);

    List<UserPermission> seleteRolePermission(JdyRole userRole);
}