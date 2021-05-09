package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyCompanyDto;
import com.jindan.jdy.common.mapper.JdyDepartmentsMapper;
import com.jindan.jdy.common.pojo.JdyDepartments;
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
public class JdyDepartmentsServiceImpl  extends ServiceImpl<JdyDepartmentsMapper,JdyDepartments> implements JdyDepartmentsService  {

    @Autowired
    JdyDepartmentsMapper jdyDepartmentsMapper;

    @Override
    public List<JdyDepartments> seletelist(JdyDepartments jdyDepartments) {

       QueryWrapper<JdyDepartments> queryWrapper =new QueryWrapper<>();
        if( jdyDepartments.getDepartments() !=null && !jdyDepartments.getDepartments().isEmpty()  ){
            queryWrapper.eq("departments",jdyDepartments.getDepartments());
        }
        if( jdyDepartments.getId() !=null &&  !jdyDepartments.getId().isEmpty() ){
            queryWrapper.eq("id",jdyDepartments.getId());
        }
        return jdyDepartmentsMapper.selectList(queryWrapper);
    }

    @Override
    public List<JdyCompanyDto> seleteJdyCompanylist(JdyCompanyDto jdyDepartments) {
        return jdyDepartmentsMapper.seleteJdyCompanylist(null);
    }
}