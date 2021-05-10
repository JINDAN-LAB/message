package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.DepartmentSuggestDto;
import com.jindan.jdy.mapper.DepartmentSuggestMapper;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class DepartmentSuggestServiceImpl  extends ServiceImpl<DepartmentSuggestMapper,DepartmentSuggest> implements DepartmentSuggestService  {

    @Autowired
    DepartmentSuggestMapper departmentSuggestMapper;


    @Override
    public List<DepartmentSuggest> seletelist(DepartmentSuggestDto departmentSuggestDto) {

        if(departmentSuggestDto.getCurrentPage() <= 0   || departmentSuggestDto.getPageSize()  <= 0){
            departmentSuggestDto.setCurrentPage(1);
        }
        QueryWrapper<DepartmentSuggest> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getUserId() !=null &&  !departmentSuggestDto.getUserId().isEmpty()  ){
            queryWrapper.eq("user_id",departmentSuggestDto.getUserId());
        }
        if( departmentSuggestDto.getSuggestName() !=null && !departmentSuggestDto.getSuggestName().isEmpty() ){
            queryWrapper.eq("suggest_name",departmentSuggestDto.getSuggestName());
        }
        if( departmentSuggestDto.getDeaprtment() !=null && !departmentSuggestDto.getDeaprtment().isEmpty() ){
            queryWrapper.eq("deaprtment",departmentSuggestDto.getDeaprtment());
        }
        if( departmentSuggestDto.getStatus() !=null &&  departmentSuggestDto.getStatus() > 0 ){
            queryWrapper.eq("status",departmentSuggestDto.getStatus());
        }
        return departmentSuggestMapper.selectList(queryWrapper);
    }

    @Override
    public Page<DepartmentSuggest> seletePagelist(DepartmentSuggestDto departmentSuggestDto) {
        if(departmentSuggestDto.getCurrentPage() <= 0  ){
            departmentSuggestDto.setCurrentPage(1);
        }

        Page<DepartmentSuggest> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());

        QueryWrapper<DepartmentSuggest> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getUserId() !=null &&  !departmentSuggestDto.getUserId().isEmpty()  ){
            queryWrapper.eq("user_id",departmentSuggestDto.getUserId());
        }
        if( departmentSuggestDto.getSuggestName() !=null && !departmentSuggestDto.getSuggestName().isEmpty() ){
            queryWrapper.like("suggest_name",departmentSuggestDto.getSuggestName());
        }
        if( departmentSuggestDto.getDeaprtment() !=null && !departmentSuggestDto.getDeaprtment().isEmpty() ){
            queryWrapper.like("deaprtment",departmentSuggestDto.getDeaprtment());
        }
        if( departmentSuggestDto.getStatus() !=null &&  departmentSuggestDto.getStatus() > 0 ){
            queryWrapper.like("status",departmentSuggestDto.getStatus());
        }
        return (Page)departmentSuggestMapper.selectPage(page,queryWrapper);
    }
}