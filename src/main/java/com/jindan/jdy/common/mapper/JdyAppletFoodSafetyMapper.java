package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;

import java.util.List;

/**   
 * @Description:TODO(视频安全小程序数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyAppletFoodSafetyMapper extends BaseMapper<JdyAppletFoodSafety> {

    List<JdyAppletFoodSafetyDto> seleteList(JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto);

    List<JdyAppletFoodSafetyDto> seletePersonList(JdyAppletFootSafetyPerson jdyAppletFoodSafetyDto);
}
