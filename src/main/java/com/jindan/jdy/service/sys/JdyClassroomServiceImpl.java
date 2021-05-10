package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.mapper.JdyClassroomMapper;
import com.jindan.jdy.common.pojo.JdyClassroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(二维码目录服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyClassroomServiceImpl  extends ServiceImpl<JdyClassroomMapper,JdyClassroom> implements JdyClassroomService  {

    @Autowired
    JdyClassroomMapper jdyClassroomMapper;

    @Override
    public Page<JdyClassroom> seletelist(JdyClassroomDto jdyClassroom) {


        if(jdyClassroom.getCurrentPage() <= 0   || jdyClassroom.getPageSize()  <= 0){
            jdyClassroom.setCurrentPage(1);
        }
        Page<JdyClassroom> iPage =new Page<>(jdyClassroom.getCurrentPage(),jdyClassroom.getPageSize());

        QueryWrapper<JdyClassroom> queryWrapper =new QueryWrapper<>();
        if(jdyClassroom.getId() !=null &&  !jdyClassroom.getId().isEmpty()  ){
            queryWrapper.eq("id",jdyClassroom.getId());
        }
        if(jdyClassroom.getJiaoshi() !=null && !jdyClassroom.getJiaoshi().isEmpty() ){
            queryWrapper.eq("jiaoshi",jdyClassroom.getJiaoshi());
        }
        if(jdyClassroom.getPerson() !=null && !jdyClassroom.getPerson().isEmpty() ){
            queryWrapper.eq("person",jdyClassroom.getPerson());
        }
        if(jdyClassroom.getBumen() !=null && !jdyClassroom.getBumen().isEmpty() ){
            queryWrapper.eq("bumen",jdyClassroom.getBumen());
        }
        if(jdyClassroom.getNumber() !=null && !jdyClassroom.getNumber().isEmpty() ){
            queryWrapper.eq("number",jdyClassroom.getNumber());
        }
        if(jdyClassroom.getStarttime() !=null &&  !jdyClassroom.getStarttime().isEmpty()  ){
            queryWrapper.ge("starttime",jdyClassroom.getStarttime());
        }
        if(jdyClassroom.getEndtime() !=null && !jdyClassroom.getEndtime().isEmpty() ){
            queryWrapper.eq("endtime",jdyClassroom.getEndtime());
        }
        if(jdyClassroom.getUptime() !=null && !jdyClassroom.getUptime().isEmpty() ){
            queryWrapper.eq("uptime",jdyClassroom.getUptime());
        }
        if(jdyClassroom.getDowntime() !=null && !jdyClassroom.getDowntime().isEmpty() ){
            queryWrapper.eq("downtime",jdyClassroom.getDowntime());
        }
        return (Page<JdyClassroom>) jdyClassroomMapper.selectPage(iPage,queryWrapper);
    }

    @Override
    public List<JdyClassroom> seleteseletelist(JdyClassroom jdyClassroom) {
        QueryWrapper<JdyClassroom> queryWrapper =new QueryWrapper<>();
        if(jdyClassroom.getId() !=null &&  !jdyClassroom.getId().isEmpty()  ){
            queryWrapper.eq("id",jdyClassroom.getId());
        }
        if(jdyClassroom.getJiaoshi() !=null && !jdyClassroom.getJiaoshi().isEmpty() ){
            queryWrapper.eq("jiaoshi",jdyClassroom.getJiaoshi());
        }
        if(jdyClassroom.getPerson() !=null && !jdyClassroom.getPerson().isEmpty() ){
            queryWrapper.eq("person",jdyClassroom.getPerson());
        }
        if(jdyClassroom.getBumen() !=null && !jdyClassroom.getBumen().isEmpty() ){
            queryWrapper.eq("bumen",jdyClassroom.getBumen());
        }
        if(jdyClassroom.getNumber() !=null && !jdyClassroom.getNumber().isEmpty() ){
            queryWrapper.eq("number",jdyClassroom.getNumber());
        }
        if(jdyClassroom.getEndtime() !=null && !jdyClassroom.getEndtime().isEmpty()  && jdyClassroom.getStarttime() !=null &&  !jdyClassroom.getStarttime().isEmpty() ){
            queryWrapper.between("starttime",jdyClassroom.getStarttime(),jdyClassroom.getEndtime());
            queryWrapper.between("endtime",jdyClassroom.getStarttime(),jdyClassroom.getEndtime());
        }
        if(jdyClassroom.getUptime() !=null && !jdyClassroom.getUptime().isEmpty() ){
            queryWrapper.eq("uptime",jdyClassroom.getUptime());
        }
        if(jdyClassroom.getDowntime() !=null && !jdyClassroom.getDowntime().isEmpty() ){
            queryWrapper.eq("downtime",jdyClassroom.getDowntime());
        }
        return jdyClassroomMapper.selectList(queryWrapper);
    }



    @Override
    public List<JdyClassroom> seleteAlllist(JdyClassroomDto jdyClassroom) {

        QueryWrapper<JdyClassroom> queryWrapper =new QueryWrapper<>();
        if(jdyClassroom.getId() !=null &&  !jdyClassroom.getId().isEmpty() ){
            queryWrapper.eq("id",jdyClassroom.getId());
        }
        if(jdyClassroom.getJiaoshi() !=null && !jdyClassroom.getJiaoshi().isEmpty() ){
            queryWrapper.eq("jiaoshi",jdyClassroom.getJiaoshi());
        }
        if(jdyClassroom.getPerson() !=null && !jdyClassroom.getPerson().isEmpty() ){
            queryWrapper.eq("person",jdyClassroom.getPerson());
        }
        if(jdyClassroom.getBumen() !=null && !jdyClassroom.getBumen().isEmpty() ){
            queryWrapper.eq("bumen",jdyClassroom.getBumen());
        }
        if(jdyClassroom.getNumber() !=null && !jdyClassroom.getNumber().isEmpty() ){
            queryWrapper.eq("number",jdyClassroom.getNumber());
        }
        if(jdyClassroom.getStarttime() !=null &&  !jdyClassroom.getStarttime().isEmpty()  ){
            queryWrapper.ge("starttime",jdyClassroom.getStarttime());
        }
        if(jdyClassroom.getEndtime() !=null && !jdyClassroom.getEndtime().isEmpty() ){
            queryWrapper.lt("endtime",jdyClassroom.getEndtime());
        }
        if(jdyClassroom.getUptime() !=null && !jdyClassroom.getUptime().isEmpty() ){
            queryWrapper.eq("uptime",jdyClassroom.getUptime());
        }
        if(jdyClassroom.getDowntime() !=null && !jdyClassroom.getDowntime().isEmpty() ){
            queryWrapper.eq("downtime",jdyClassroom.getDowntime());
        }
        return jdyClassroomMapper.selectList(queryWrapper);
    }


}