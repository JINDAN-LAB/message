package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import com.jindan.jdy.common.mapper.KeyPointProjectMapper;
import com.jindan.jdy.common.pojo.KeyPointProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(重点项目服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class KeyPointProjectServiceImpl  extends ServiceImpl<KeyPointProjectMapper,KeyPointProject> implements KeyPointProjectService  {

        @Autowired
        KeyPointProjectMapper keyPointPracticableDao;

        @Override
        public Page<KeyPointProject> seleteDepartmentFacility(KeyPointProjectDto departmentSuggestDto) {

            if(departmentSuggestDto.getCurrentPage() <= 0  || departmentSuggestDto.getPageSize()  <= 0){
                departmentSuggestDto.setCurrentPage(1);
            }
            Page<KeyPointProject> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
            QueryWrapper<KeyPointProject> queryWrapper =new QueryWrapper<>();
            if( departmentSuggestDto.getId() !=null &&  departmentSuggestDto.getId()!= ""  ){
                queryWrapper.eq("id",departmentSuggestDto.getId());
            }
            if( departmentSuggestDto.getTitles() !=null && !departmentSuggestDto.getTitles().isEmpty() ){
                queryWrapper.like("titles",departmentSuggestDto.getTitles());
            }
            if( departmentSuggestDto.getContents() !=null &&  departmentSuggestDto.getContents()!= ""  ){
                queryWrapper.like("contents",departmentSuggestDto.getContents());
            }
            if( departmentSuggestDto.getStatus() !=null && !departmentSuggestDto.getStatus().isEmpty() ){
                queryWrapper.eq("status",departmentSuggestDto.getStatus());
            }
            if( departmentSuggestDto.getDeparement() !=null &&  departmentSuggestDto.getDeparement()!= ""  ){
                queryWrapper.like("deparement",departmentSuggestDto.getDeparement());
            }
            if( departmentSuggestDto.getPersons() !=null && !departmentSuggestDto.getPersons().isEmpty() ){
                queryWrapper.like("persons",departmentSuggestDto.getPersons());
            }
            if( departmentSuggestDto.getSubmitTime() !=null &&  departmentSuggestDto.getSubmitTime()!= ""){
                if( departmentSuggestDto.getEndsubmitTime() !=null &&  departmentSuggestDto.getEndsubmitTime()!= ""){
                    queryWrapper.le("submit_time",departmentSuggestDto.getSubmitTime()).ge("submit_time",departmentSuggestDto.getEndsubmitTime());
                }else{
                    queryWrapper.ge("submit_time",departmentSuggestDto.getSubmitTime());
                }
            }
            queryWrapper.orderByDesc("insert_time","persons");
            return  (Page)keyPointPracticableDao.selectPage(page,queryWrapper);
        }


        @Override
        public List<KeyPointProjectDto> seleteKeyPointProjectexcle(KeyPointProject keyPointProject) {
            return keyPointPracticableDao.seleteKeyPointProjectexcle(keyPointProject);
        }


}