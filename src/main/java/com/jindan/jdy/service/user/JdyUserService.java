package com.jindan.jdy.service.user;

import com.jindan.jdy.common.dto.JdyRoleDto;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.UserPermission;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: BianPeng
 * 
 */
public interface JdyUserService extends IService<JdyUser> {

    List<UserPermission> seleAllUserPriment(Integer userId);

    List<JdyUserDto> seleAllUserUserRole(String userId);

    List<JdyUser> seletelist(JdyUserDto jdyUserDto);

    JdyUserDto seleteUserDetailsOne(String userid);

    JdyUser saveOne(JdyUser jdyUser);

    JdyUser seletelistOne(JdyUser jdyUser);

    List<JdyUserDto> selegetInfo(String id);

    JdyUser seleteOne(JdyUser jdyUser);

    List<JdyUser> seleListUsers(JdyUser jdyUser);
}