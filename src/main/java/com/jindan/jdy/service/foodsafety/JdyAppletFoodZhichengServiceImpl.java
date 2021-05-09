package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.mapper.JdyAppletFoodClassifyDao;
import com.jindan.jdy.common.pojo.JdyAppletFoodClassify;
import com.jindan.jdy.common.pojo.JdyAppletFoodZhicheng;
import com.jindan.jdy.common.mapper.JdyAppletFoodZhichengDao;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodZhichengService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(食品安全人员职称服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFoodZhichengServiceImpl  extends ServiceImpl<JdyAppletFoodZhichengDao,JdyAppletFoodZhicheng> implements JdyAppletFoodZhichengService  {

    @Autowired
    JdyAppletFoodZhichengDao jdyAppletFoodSafetyProblemsDao;


    @Override
    public List<JdyAppletFoodZhicheng> seleteList(JdyAppletFoodZhicheng domesticFahuoDto) {

        QueryWrapper<JdyAppletFoodZhicheng> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getZcid() !=null && !domesticFahuoDto.getZcid().isEmpty()){
            queryWrapper.eq("zcid",domesticFahuoDto.getZcid());
        }
        if( domesticFahuoDto.getZcname() !=null && !domesticFahuoDto.getZcname().isEmpty()  ){
            queryWrapper.eq("zcname",domesticFahuoDto.getZcname());
        }
        queryWrapper.eq("delete_id","0");
        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }
}