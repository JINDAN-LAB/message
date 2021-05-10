package com.jindan.jdy.service.keypoint;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.KeyPointPracticableDto;
import com.jindan.jdy.mapper.KeyPointPracticableDao;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
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
public class KeyPointPracticableServiceImpl  extends ServiceImpl<KeyPointPracticableDao,KeyPointPracticable> implements KeyPointPracticableService  {

    @Autowired
    KeyPointPracticableDao keyPointPracticableDao;


    @Override
    public Page<KeyPointPracticable> seleteDepartmentFacility(KeyPointPracticableDto departmentSuggestDto) {

        if(departmentSuggestDto.getCurrentPage() <= 0  || departmentSuggestDto.getPageSize()  <= 0){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<KeyPointPracticable> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());
        QueryWrapper<KeyPointPracticable> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getPtitles() !=null &&  departmentSuggestDto.getPtitles()!= ""  ){
            queryWrapper.like("ptitles",departmentSuggestDto.getPtitles());
        }
        if( departmentSuggestDto.getPcontents() !=null && !departmentSuggestDto.getPcontents().isEmpty() ){
            queryWrapper.like("pcontents",departmentSuggestDto.getPcontents());
        }
        if( departmentSuggestDto.getPremarks() !=null &&  departmentSuggestDto.getPremarks()!= ""  ){
            queryWrapper.like("premarks",departmentSuggestDto.getPremarks());
        }
        if( departmentSuggestDto.getPstatus() !=null && !departmentSuggestDto.getPstatus().isEmpty() ){
            queryWrapper.like("pstatus",departmentSuggestDto.getPstatus());
        }
        if( departmentSuggestDto.getParentId() !=null && !departmentSuggestDto.getParentId().isEmpty() ){
            queryWrapper.like("parent_id",departmentSuggestDto.getParentId());
        }
        if( departmentSuggestDto.getPpersons() !=null &&  departmentSuggestDto.getPpersons()!= ""  ){
            queryWrapper.like("ppersons",departmentSuggestDto.getPpersons());
        }
        return  (Page)keyPointPracticableDao.selectPage(page,queryWrapper);

    }
}