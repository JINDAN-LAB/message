package com.jindan.jdy.service.user;

import com.jindan.jdy.common.dto.JdyUserLeaveDto;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.pojo.JdyUserLeave;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(人员请假登记服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyUserLeaveService extends IService<JdyUserLeave> {

    List<JdyUserLeave> seletelist(JdyUserLeaveDto jdyUserFileDto);
}