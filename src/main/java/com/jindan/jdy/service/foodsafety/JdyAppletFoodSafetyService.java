package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;

import java.util.List;

/**   
 * @Description:TODO(视频安全小程序服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodSafetyService extends IService<JdyAppletFoodSafety> {

    List<JdyAppletFoodSafetyDto> seleteList(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto);

    List<JdyAppletFoodSafetyDto> seletePersonList(String jdyAppletFoodSafetyDto);

    List<JdyAppletFoodSafetyDto> seleChejianProblems(String pwd);

// 获取所有车间信息
    List<JdyAppletFoodSafetyDto> seleteAllChejianDrtment(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto);

    boolean insertsave(JdyAppletFoodSafety users);

    List<JdyAppletFoodSafetyDto> seleteAllList(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto);
}