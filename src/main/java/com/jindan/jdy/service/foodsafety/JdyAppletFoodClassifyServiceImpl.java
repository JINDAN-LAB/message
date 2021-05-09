package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.JdyAppletFoodClassifyDao;
import com.jindan.jdy.common.pojo.JdyAppletFoodClassify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(食品安全问题分类服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFoodClassifyServiceImpl  extends ServiceImpl<JdyAppletFoodClassifyDao,JdyAppletFoodClassify> implements JdyAppletFoodClassifyService  {

    @Autowired
    JdyAppletFoodClassifyDao jdyAppletFoodSafetyProblemsDao;


    @Override
    public List<JdyAppletFoodClassify> seleteList(JdyAppletFoodClassify domesticFahuoDto) {

        QueryWrapper<JdyAppletFoodClassify> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getLid() !=null && !domesticFahuoDto.getLid().isEmpty()){
            queryWrapper.eq("lid",domesticFahuoDto.getLid());
        }
        if( domesticFahuoDto.getLname() !=null && !domesticFahuoDto.getLname().isEmpty()  ){
            queryWrapper.eq("lname",domesticFahuoDto.getLname());
        }
        queryWrapper.eq("delete_id","0");
        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }
}