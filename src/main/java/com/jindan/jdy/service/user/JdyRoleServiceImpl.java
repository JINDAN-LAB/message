package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyRoleMapper;
import com.jindan.jdy.common.pojo.JdyRole;
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
public class JdyRoleServiceImpl  extends ServiceImpl<JdyRoleMapper,JdyRole> implements JdyRoleService  {

    @Autowired
    JdyRoleMapper  jdyRoleMapper;

    @Override
    public List<JdyRole> seletelist(JdyRole jdyRole) {
        QueryWrapper<JdyRole> queryWrapper =new QueryWrapper<>();
        if(jdyRole.getRoleId() !=null && !jdyRole.getRoleId().isEmpty()){
            queryWrapper.eq("role_id",jdyRole.getRoleId());
        }
        if(jdyRole.getRoleName() !=null && !jdyRole.getRoleName().isEmpty() ){
            queryWrapper.like("role_name",jdyRole.getRoleName());
        }
        if(jdyRole.getRoleDescribe() !=null && !jdyRole.getRoleDescribe().isEmpty() ){
            queryWrapper.like("role_describe",jdyRole.getRoleDescribe());
        }
        return jdyRoleMapper.selectList(queryWrapper);
    }

    @Override
    public JdyRole  saveJdyRole(JdyRole jdyRole) {
        Integer integer = jdyRoleMapper.insert(jdyRole);
        return jdyRole;
    }

}