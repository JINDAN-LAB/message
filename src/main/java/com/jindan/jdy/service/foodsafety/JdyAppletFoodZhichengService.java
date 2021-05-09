package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodZhicheng;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(食品安全人员职称服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodZhichengService extends IService<JdyAppletFoodZhicheng> {

    List<JdyAppletFoodZhicheng> seleteList(JdyAppletFoodZhicheng jdyAppletFoodSafetyDto);
}