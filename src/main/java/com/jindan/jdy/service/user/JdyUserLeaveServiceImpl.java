package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.dto.JdyUserLeaveDto;
import com.jindan.jdy.common.mapper.JdyUserMapper;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.pojo.JdyUserLeave;
import com.jindan.jdy.common.mapper.JdyUserLeaveDao;
import com.jindan.jdy.service.user.JdyUserLeaveService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(人员请假登记服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyUserLeaveServiceImpl  extends ServiceImpl<JdyUserLeaveDao,JdyUserLeave> implements JdyUserLeaveService  {

    @Autowired
    JdyUserLeaveDao jdyUserLeaveDao;

    @Override
    public List<JdyUserLeave> seletelist(JdyUserLeaveDto jdyUserFileDto) {
        QueryWrapper<JdyUserLeave> queryWrapper =new QueryWrapper<>();
        if( jdyUserFileDto.getLeaveId() !=null &&  !jdyUserFileDto.getLeaveId().isEmpty()){
            queryWrapper.eq("leave_id",jdyUserFileDto.getLeaveId());
        }
        if( jdyUserFileDto.getLeaveUsername() !=null &&  !jdyUserFileDto.getLeaveUsername().isEmpty()){
            queryWrapper.eq("leave_username",jdyUserFileDto.getLeaveUsername());
        }
        if( jdyUserFileDto.getLeaveDepartment() !=null &&  !jdyUserFileDto.getLeaveDepartment().isEmpty()){
            queryWrapper.like("leave_department",jdyUserFileDto.getLeaveDepartment());
        }
        if( jdyUserFileDto.getLeavePost() !=null &&  !jdyUserFileDto.getLeavePost().isEmpty()){
            queryWrapper.eq("leave_post",jdyUserFileDto.getLeavePost());
        }
        if( jdyUserFileDto.getLeaveType() !=null &&  !jdyUserFileDto.getLeaveType().isEmpty()){
            queryWrapper.eq("leave_type",jdyUserFileDto.getLeaveType());
        }
        if( jdyUserFileDto.getLeaveContent() !=null &&  !jdyUserFileDto.getLeaveContent().isEmpty()){
            queryWrapper.like("leave_content",jdyUserFileDto.getLeaveContent());
        }
        if( jdyUserFileDto.getStartTime() !=null &&  !jdyUserFileDto.getStartTime().isEmpty()){
            queryWrapper.le("start_time",jdyUserFileDto.getStartTime());
        }
        if( jdyUserFileDto.getEndTime() !=null &&  !jdyUserFileDto.getEndTime().isEmpty()){
            queryWrapper.ge("end_time",jdyUserFileDto.getEndTime());
        }
        if( jdyUserFileDto.getLeaveStatus() !=null &&  !jdyUserFileDto.getLeaveStatus().isEmpty()){
            queryWrapper.eq("leave_status",jdyUserFileDto.getLeaveStatus());
        }
        if( jdyUserFileDto.getLeaveDay() !=null &&  !jdyUserFileDto.getLeaveDay().isEmpty()){
            queryWrapper.eq("leave_day",jdyUserFileDto.getLeaveDay());
        }
        return jdyUserLeaveDao.selectList(queryWrapper);
    }
}