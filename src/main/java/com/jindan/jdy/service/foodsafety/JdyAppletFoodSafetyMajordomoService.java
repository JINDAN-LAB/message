package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyMajordomo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(食品安全总监管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodSafetyMajordomoService extends IService<JdyAppletFoodSafetyMajordomo> {

    List<JdyAppletFoodSafetyMajordomo> seleteList(JdyAppletFoodSafetyMajordomo jdyAppletFoodSafetyDto);

    boolean insertsave(JdyAppletFoodSafetyMajordomo users);
}