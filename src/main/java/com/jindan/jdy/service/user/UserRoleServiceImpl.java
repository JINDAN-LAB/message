package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.UserRoleDto;
import com.jindan.jdy.mapper.UserRoleMapper;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.UserRole;
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
public class UserRoleServiceImpl  extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService  {

     @Autowired
     UserRoleMapper  userRoleMapper;


    @Override
    public List<UserRoleDto> seleteAllUserRolelist(UserRole userRole) {
        return userRoleMapper.seleteAllUserRolelist(userRole);
    }

    @Override
    public List<UserPermission> seleteRolePermission(JdyRole userRole) {
        return userRoleMapper.seleteRolePermission(userRole);
    }
}