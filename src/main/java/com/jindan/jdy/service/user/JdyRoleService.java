package com.jindan.jdy.service.user;

import com.jindan.jdy.common.pojo.JdyRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyRoleService extends IService<JdyRole> {

    List<JdyRole> seletelist(JdyRole jdyRole);

    JdyRole  saveJdyRole(JdyRole jdyRole);
}