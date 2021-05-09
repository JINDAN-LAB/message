package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.JdyAppletManagers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletManagersService extends IService<JdyAppletManagers> {

    List<JdyAppletManagers> seletedslist(JdyAppletManagers jdyClassroom);

}