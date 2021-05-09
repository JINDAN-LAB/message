package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.mapper.JdySspMapper;
import com.jindan.jdy.common.pojo.JdySsp;
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
public class JdySspServiceImpl  extends ServiceImpl<JdySspMapper,JdySsp> implements JdySspService  {

    @Autowired
    JdySspMapper jdySspMapper;

    @Override
    public List<JdySsp> seletelist(JdySspDto jdySsp) {

        QueryWrapper<JdySsp> queryWrapper =new QueryWrapper<>();
        if( jdySsp.getContent() !=null && !jdySsp.getContent().isEmpty() ){
            queryWrapper.eq("content",jdySsp.getContent());
        }
        if( jdySsp.getLocations() !=null && !jdySsp.getLocations().isEmpty() ){
            queryWrapper.eq("locations",jdySsp.getLocations());
        }
        if( jdySsp.getUserId() !=null && !jdySsp.getUserId().isEmpty()){
            queryWrapper.eq("user_id",jdySsp.getUserId());
        }
        if( jdySsp.getStatus() !=null && !jdySsp.getStatus().isEmpty() ){
            queryWrapper.eq("status",jdySsp.getStatus());
        }
        if( jdySsp.getChuliTime() !=null && !jdySsp.getChuliTime().isEmpty() ){
            queryWrapper.eq("chuli_time",jdySsp.getChuliTime());
        }
        if( jdySsp.getResultPer() !=null && !jdySsp.getResultPer().isEmpty() ){
            queryWrapper.eq("result_per",jdySsp.getResultPer());
        }
        if( jdySsp.getExceedTime() !=null &&  !jdySsp.getExceedTime().isEmpty()){
            queryWrapper.eq("exceed_time",jdySsp.getExceedTime());
        }
        return jdySspMapper.selectList(queryWrapper);
    }

    @Override
    public boolean deleteById(String ids) {
       QueryWrapper<JdySsp> queryWrapper =new QueryWrapper<>();
        if( ids !=null &&  !ids.isEmpty() ){
            queryWrapper.eq("id",ids);
        }
        return jdySspMapper.delete(queryWrapper) > 0;
    }

    @Override
    public Page<JdySsp> seletePagelist(JdySspDto jdySsp) {
        if(jdySsp.getCurrentPage() <= 0  ){
            jdySsp.setCurrentPage(1);
        }
        Page<JdySsp> page =new Page<>(jdySsp.getCurrentPage(),jdySsp.getPageSize());
        QueryWrapper<JdySsp> queryWrapper =new QueryWrapper<>();
        if( jdySsp.getContent() !=null && !jdySsp.getContent().isEmpty() ){
            queryWrapper.eq("content",jdySsp.getContent());
        }
        if( jdySsp.getLocations() !=null && !jdySsp.getLocations().isEmpty() ){
            queryWrapper.like("locations",jdySsp.getLocations());
        }
        if( jdySsp.getUserId() !=null && !jdySsp.getUserId().isEmpty()){
            queryWrapper.like("user_id",jdySsp.getUserId());
        }
        if( jdySsp.getStatus() !=null && !jdySsp.getStatus().isEmpty() ){
            queryWrapper.like("status",jdySsp.getStatus());
        }
        if( jdySsp.getChuliTime() !=null && !jdySsp.getChuliTime().isEmpty() ){
            queryWrapper.like("chuli_time",jdySsp.getChuliTime());
        }
        if( jdySsp.getResultPer() !=null && !jdySsp.getResultPer().isEmpty() ){
            queryWrapper.like("result_per",jdySsp.getResultPer());
        }
        if( jdySsp.getExceedTime() !=null &&  !jdySsp.getExceedTime().isEmpty()){
            queryWrapper.like("exceed_time",jdySsp.getExceedTime());
        }
        return (Page)jdySspMapper.selectPage(page,queryWrapper);
    }
}