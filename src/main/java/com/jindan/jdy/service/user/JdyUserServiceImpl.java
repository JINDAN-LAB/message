package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.mapper.JdyUserMapper;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.UserPermission;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: BianPeng
 * 
 */
@Slf4j
@Component
public class JdyUserServiceImpl  extends ServiceImpl<JdyUserMapper, JdyUser> implements JdyUserService {

    @Autowired
    JdyUserMapper jdyUserDao;

    @Override
    public List<UserPermission> seleAllUserPriment(Integer userId) {
        return jdyUserDao.seleAllUserPriment(userId);
    }

    @Override
    public List<JdyUserDto> seleAllUserUserRole(String userId) {
        return jdyUserDao.seleAllUserUserRole(userId);
    }

    @Override
    public List<JdyUser> seletelist(JdyUserDto jdyUserDto) {
        return jdyUserDao.selectJdyUserDtoList(jdyUserDto);
    }

    @Override
    public JdyUserDto seleteUserDetailsOne(String userId) {
        return jdyUserDao.seleteUserDetailsOne(userId);
    }

    @Override
    public JdyUser saveOne(JdyUser jdyUser) {
        int insert = jdyUserDao.insert(jdyUser);
        if(insert > 0){
         return jdyUser;
        }
        return null;
    }

    @Override
    public JdyUser seletelistOne(JdyUser jdyUser) {
            log.info("kongshihao",jdyUser);
            QueryWrapper<JdyUser> queryWrapper =new QueryWrapper<>();
            queryWrapper.like("phone",jdyUser.getPhone());
            queryWrapper.like("password",jdyUser.getPassword());
        return jdyUserDao.selectList(queryWrapper).get(0);
    }

    @Override
    public List<JdyUserDto> selegetInfo(String id) {
            return jdyUserDao.selegetInfo(id);
    }

    @Override
    public JdyUser seleteOne(JdyUser jdyUser) {
        if(jdyUser != null){
            QueryWrapper<JdyUser> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("phone",jdyUser.getPhone());
            return  jdyUserDao.selectOne(queryWrapper);
        }
        return null;
    }

    @Override
    public List<JdyUser> seleListUsers(JdyUser jdyUser) {
        QueryWrapper<JdyUser> queryWrapper =new QueryWrapper<>();
        if( jdyUser.getDepartments() !=null &&  !jdyUser.getDepartments().isEmpty()){
            queryWrapper.eq("departments",jdyUser.getDepartments());
        }
        if( jdyUser.getPhone() !=null &&  !jdyUser.getPhone().isEmpty()){
            queryWrapper.eq("phone",jdyUser.getPhone());
        }
        if( jdyUser.getUsername() !=null &&  !jdyUser.getUsername().isEmpty()){
            queryWrapper.eq("username",jdyUser.getUsername());
        }
        return jdyUserDao.selectList(queryWrapper);
    }

}

