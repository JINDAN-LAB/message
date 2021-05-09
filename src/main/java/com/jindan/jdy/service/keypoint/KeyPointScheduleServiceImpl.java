package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointScheduleDto;
import com.jindan.jdy.common.pojo.KeyPointSchedule;
import com.jindan.jdy.common.mapper.KeyPointScheduleDao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(重点项目服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class KeyPointScheduleServiceImpl  extends ServiceImpl<KeyPointScheduleDao,KeyPointSchedule> implements KeyPointScheduleService  {

    @Autowired
    KeyPointScheduleDao keyPointPracticableDao;

    @Override
    public Page<KeyPointSchedule> seleteDepartmentFacility(KeyPointScheduleDto departmentSuggestDto) {

            if(departmentSuggestDto.getCurrentPage() <= 0  || departmentSuggestDto.getPageSize()  <= 0){
                departmentSuggestDto.setCurrentPage(1);
            }
            Page<KeyPointSchedule> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
            QueryWrapper<KeyPointSchedule> queryWrapper =new QueryWrapper<>();
            if( departmentSuggestDto.getSid() !=null &&  departmentSuggestDto.getSid()!= ""  ){
                queryWrapper.eq("sid",departmentSuggestDto.getSid());
            }
            if( departmentSuggestDto.getStitles() !=null && !departmentSuggestDto.getStitles().isEmpty() ){
                queryWrapper.like("stitles",departmentSuggestDto.getStitles());
            }
            if( departmentSuggestDto.getSpersons() !=null &&  departmentSuggestDto.getSpersons()!= ""  ){
                queryWrapper.like("spersons",departmentSuggestDto.getSpersons());
            }
            if( departmentSuggestDto.getSstatus() !=null && !departmentSuggestDto.getSstatus().isEmpty() ){
                queryWrapper.like("sstatus",departmentSuggestDto.getSstatus());
            }
            if( departmentSuggestDto.getSremarks() !=null &&  departmentSuggestDto.getSremarks()!= ""  ){
                queryWrapper.like("sremarks",departmentSuggestDto.getSremarks());
            }
            if( departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty() ){
                queryWrapper.like("parent_id",departmentSuggestDto.getParentId());
            }
            if( departmentSuggestDto.getSfileId() !=null &&  departmentSuggestDto.getSfileId()!= ""  ){
                queryWrapper.like("sfile_id",departmentSuggestDto.getSfileId());
            }

            return  (Page)keyPointPracticableDao.selectPage(page,queryWrapper);

    }


}