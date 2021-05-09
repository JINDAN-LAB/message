package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdySsp;

import java.util.List;

/**   
 * @Description:TODO(二维码目录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyClassroomService extends IService<JdyClassroom> {

    Page<JdyClassroom> seletelist(JdyClassroomDto jdyClassroom);

    List<JdyClassroom> seleteseletelist(JdyClassroom  jdyClassroom1);

    List<JdyClassroom> seleteAlllist(JdyClassroomDto jdyClassroomDto);
}