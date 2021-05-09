package com.jindan.jdy.service.foodsafety;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;

import java.util.List;

/**   
 * @Description:TODO(食品安全人员管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFootSafetyPersonService extends IService<JdyAppletFootSafetyPerson> {

    List<JdyAppletFootSafetyPerson> seleteList(JdyAppletFootSafetyPerson jdyAppletFoodSafetyDto);

}