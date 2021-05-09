package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsExcle;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsReultDto;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;

import java.util.List;

/**   
 * @Description:TODO(食品安全小程序数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyAppletFoodSafetyProblemsReultMapper extends BaseMapper<JdyAppletFoodSafetyProblemsReult> {

    List<JdyAppletFoodSafetyProblemsReultDto> seletePreProList();

    List<JdyAppletFoodSafetyProblemsReultDto> seletePreLvzhiProList();

    List<JdyAppletFoodSafetyProblemsExcle> seleteAlllist(String param);

//    获取班组提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAlllBanzuist(String id);
//获取车间提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAlllChejianist(String id);
//获取总监提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAlllZongjianist(String id);
//获取公司提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAllGongsilist(String id);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllSinglelist(String id);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuSinglelist(List<String> list);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuchuliSinglelist(List<String> list);

//    车间提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAllChejianSinglelist(List<String> list);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllWaibuChejianSinglelist(List<String> list);

//    总监提交的问题
    List<JdyAppletFoodSafetyProblemsReultDto> seleteAllZognjianSinglelist(JdyAppletFoodSafetyProblemsReult list);

//    公司提交的问题
    List<JdyAppletFoodSafetyProblemsReultDto> seleteAllGongsiSinglelist(JdyAppletFoodSafetyProblemsReult list);
}
