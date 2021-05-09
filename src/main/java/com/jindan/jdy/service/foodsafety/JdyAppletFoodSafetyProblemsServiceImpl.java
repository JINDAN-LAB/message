package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblems;
import com.jindan.jdy.common.mapper.JdyAppletFoodSafetyProblemsDao;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyProblemsService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(食品安全小程序服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFoodSafetyProblemsServiceImpl  extends ServiceImpl<JdyAppletFoodSafetyProblemsDao,JdyAppletFoodSafetyProblems> implements JdyAppletFoodSafetyProblemsService  {


    @Autowired
    JdyAppletFoodSafetyProblemsDao jdyAppletFoodSafetyProblemsDao;


    @Override
    public List<JdyAppletFoodSafetyProblems> seleteAlllist(JdyAppletFoodSafetyProblems domesticFahuoDto) {

        QueryWrapper<JdyAppletFoodSafetyProblems> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getFid() !=null && !domesticFahuoDto.getFid().isEmpty()){
            queryWrapper.eq("fid",domesticFahuoDto.getFid());
        }
        if( domesticFahuoDto.getProblems() !=null && !domesticFahuoDto.getProblems().isEmpty()  ){
            queryWrapper.eq("problems",domesticFahuoDto.getProblems());
        }
        if( domesticFahuoDto.getParentId() !=null && domesticFahuoDto.getParentId() != null ){
            queryWrapper.eq("parent_id",domesticFahuoDto.getParentId());
        }
        queryWrapper.eq("delete_id","0");

        return  jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }
}