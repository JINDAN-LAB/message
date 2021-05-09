package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.JdyAppletFoodSafetyMajordomoDao;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyMajordomo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(食品安全总监管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyAppletFoodSafetyMajordomoServiceImpl  extends ServiceImpl<JdyAppletFoodSafetyMajordomoDao,JdyAppletFoodSafetyMajordomo> implements JdyAppletFoodSafetyMajordomoService  {

    @Autowired
    JdyAppletFoodSafetyMajordomoDao jdyAppletFoodSafetyProblemsDao;

    @Override
    public List<JdyAppletFoodSafetyMajordomo> seleteList(JdyAppletFoodSafetyMajordomo domesticFahuoDto) {

        QueryWrapper<JdyAppletFoodSafetyMajordomo> queryWrapper =new QueryWrapper<>();
        if( domesticFahuoDto.getZid() !=null && !domesticFahuoDto.getZid().isEmpty()){
            queryWrapper.eq("zid",domesticFahuoDto.getZid());
        }
        if( domesticFahuoDto.getAjob() !=null && !domesticFahuoDto.getAjob().isEmpty()  ){
            queryWrapper.eq("ajob",domesticFahuoDto.getAjob());
        }
        if( domesticFahuoDto.getZlevel() !=null && !domesticFahuoDto.getZlevel().isEmpty()  ){
            queryWrapper.eq("zlevel",domesticFahuoDto.getZlevel());
        }
        queryWrapper.eq("delete_id","0");

        return jdyAppletFoodSafetyProblemsDao.selectList(queryWrapper);
    }

    @Override
    public boolean insertsave(JdyAppletFoodSafetyMajordomo users) {
        return jdyAppletFoodSafetyProblemsDao.insert(users) > 0;
    }
}