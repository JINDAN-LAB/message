package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblems;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(食品安全小程序服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodSafetyProblemsService extends IService<JdyAppletFoodSafetyProblems> {

    List<JdyAppletFoodSafetyProblems> seleteAlllist(JdyAppletFoodSafetyProblems jdyAppletFoodSafetyDto);
}