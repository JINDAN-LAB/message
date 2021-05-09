package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;

import java.util.List;

/**   
 * @Description:TODO(食品安全人员管理数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyAppletFootSafetyPersonMapper extends BaseMapper<JdyAppletFootSafetyPerson> {

    List<JdyAppletFootSafetyPerson> selectSMSList(String id);

}
