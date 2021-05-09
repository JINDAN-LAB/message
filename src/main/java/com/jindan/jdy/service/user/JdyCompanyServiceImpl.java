package com.jindan.jdy.service.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.mapper.JdyDepartmentsMapper;
import com.jindan.jdy.common.pojo.JdyCompany;
import com.jindan.jdy.common.mapper.JdyCompanyDao;
import com.jindan.jdy.common.pojo.JdyDepartments;
import com.jindan.jdy.service.user.JdyCompanyService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产子设备信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyCompanyServiceImpl  extends ServiceImpl<JdyCompanyDao,JdyCompany> implements JdyCompanyService  {

    @Autowired
    JdyCompanyDao jdyDepartmentsMapper;


    @Override
    public List<JdyCompany> seletelist(JdyCompany jdyDepartments) {

        QueryWrapper<JdyCompany> queryWrapper =new QueryWrapper<>();
        if( jdyDepartments.getIds() !=null && !jdyDepartments.getIds().isEmpty()  ){
            queryWrapper.eq("ids",jdyDepartments.getIds());
        }
        if( jdyDepartments.getCompanyName() !=null &&  !jdyDepartments.getCompanyName().isEmpty() ){
            queryWrapper.eq("company_name",jdyDepartments.getCompanyName());
        }
        if( jdyDepartments.getCompanyPerson() !=null &&  !jdyDepartments.getCompanyPerson().isEmpty() ){
            queryWrapper.eq("company_person",jdyDepartments.getCompanyPerson());
        }
        if( jdyDepartments.getYingyeZhizhao() !=null &&  !jdyDepartments.getYingyeZhizhao().isEmpty() ){
            queryWrapper.eq("yingye_zhizhao",jdyDepartments.getYingyeZhizhao());
        }
        if( jdyDepartments.getPersonsNumbers() !=null &&  !jdyDepartments.getPersonsNumbers().isEmpty() ){
            queryWrapper.eq("persons_numbers",jdyDepartments.getPersonsNumbers());
        }
        if( jdyDepartments.getShuihao() !=null &&  !jdyDepartments.getShuihao().isEmpty() ){
            queryWrapper.eq("shuihao",jdyDepartments.getShuihao());
        }
        return jdyDepartmentsMapper.selectList(queryWrapper);
    }
}