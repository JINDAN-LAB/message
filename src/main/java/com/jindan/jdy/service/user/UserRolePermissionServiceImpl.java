package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.UserPermissionDto;
import com.jindan.jdy.mapper.UserRolePermissionMapper;
import com.jindan.jdy.common.pojo.UserRolePermission;
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
public class UserRolePermissionServiceImpl  extends ServiceImpl<UserRolePermissionMapper,UserRolePermission> implements UserRolePermissionService  {


    @Autowired
    UserRolePermissionMapper userRolePermissionMapper;



    @Override
    public List<UserPermissionDto> seleteUserRolelist(UserRolePermission userRolePermission) {
        return userRolePermissionMapper.seleteUserRolelist(userRolePermission);
    }

    @Override
    public List<UserPermissionDto> seleteUserPermission(UserRolePermission userRolePermission) {
        return userRolePermissionMapper.seleteUserPermission(userRolePermission);
    }

    @Override
    public boolean deleteRolePermission(UserRolePermission userRolePermission) {
        QueryWrapper<UserRolePermission> queryWrapper =new QueryWrapper<>();
        if( userRolePermission.getRoleId() !=null &&  !userRolePermission.getRoleId().isEmpty()  ){
            queryWrapper.eq("role_id",userRolePermission.getRoleId());
        }
        if( userRolePermission.getPermissionId() !=null &&  !userRolePermission.getPermissionId().isEmpty() ){
            queryWrapper.eq("permission_id",userRolePermission.getPermissionId());
        }
        if( userRolePermission.getId() !=null &&  !userRolePermission.getId().isEmpty()){
            queryWrapper.eq("id",userRolePermission.getId());
        }
        return userRolePermissionMapper.delete(queryWrapper) > 0;
    }


}