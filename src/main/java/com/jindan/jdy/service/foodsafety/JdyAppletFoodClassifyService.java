package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(食品安全问题分类服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodClassifyService extends IService<JdyAppletFoodClassify> {

    List<JdyAppletFoodClassify> seleteList(JdyAppletFoodClassify jdyAppletFoodSafetyDto);
}