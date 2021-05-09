package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.dto.UserPermissionDto;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.mapper.UserPermissionMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserPermissionServiceImpl  extends ServiceImpl<UserPermissionMapper,UserPermission> implements UserPermissionService  {

    @Autowired
    UserPermissionMapper userPermissionMapper;

    @Override
    public List<UserPermission> seletelist(UserPermission userPermission) {

        QueryWrapper<UserPermission> queryWrapper =new QueryWrapper<>();
        if( userPermission.getPermissionId() !=null &&  !userPermission.getPermissionId().isEmpty()  ){
            queryWrapper.eq("permission_id",userPermission.getPermissionId());
        }
        if( userPermission.getPermissionName() !=null && !userPermission.getPermissionName().isEmpty() ){
            queryWrapper.eq("permission_name",userPermission.getPermissionName());
        }
        return userPermissionMapper.selectList(queryWrapper);
    }
}