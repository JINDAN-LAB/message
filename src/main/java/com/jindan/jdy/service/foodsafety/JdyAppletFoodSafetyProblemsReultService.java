package com.jindan.jdy.service.foodsafety;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsExcle;
import com.jindan.jdy.common.dto.JdyAppletFoodSafetyProblemsReultDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;

import java.util.List;

/**   
 * @Description:TODO(食品安全小程序服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyAppletFoodSafetyProblemsReultService extends IService<JdyAppletFoodSafetyProblemsReult> {

    List<JdyAppletFoodSafetyProblemsReult> seleteList(JdyAppletFoodSafetyProblemsReult jdyAppletFoodSafetyDto);

    void sentSms(JdyAppletFoodSafetyProblemsReult users);

    List<JdyAppletFoodSafetyProblemsReult> seleteProList();

    List<JdyAppletFoodSafetyProblemsReultDto> seletePreProList();

    List<JdyAppletFoodSafetyProblemsReultDto> seletePreLvzhiProList();

    void sentPersonSms(String[] phoneNumbers);

    List<JdyAppletFoodSafetyProblemsReult> seleteWentiList(JdyAppletFoodSafetyProblemsReult jdyAppletFoodSafetyDto);

//    根据公司查询出导出信息
    List<JdyAppletFoodSafetyProblemsExcle> seleteAlllist(JdyAppletFootSafetyPerson param);

    //    根据人员查询出导出信息
    List<JdyAppletFoodSafetyProblemsExcle> seleteAllSinglelist(JdyAppletFootSafetyPerson param);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuSinglelist(List<String> param);

    List<JdyAppletFoodSafetyProblemsExcle> seleteAllBanzuchuliSinglelist(List<String> param);
//  车间提交的问题
    List<JdyAppletFoodSafetyProblemsExcle> seleteAllChejianSinglelist(List<String> param);

//    外部提交给车间的信息
    List<JdyAppletFoodSafetyProblemsExcle> seleteAllWaibuChejianSinglelist(List<String> param);

//    总监提交的问题
    List<JdyAppletFoodSafetyProblemsReultDto> seleteAllZognjianSinglelist(JdyAppletFoodSafetyProblemsReult param);

//    公司提交的问题
    List<JdyAppletFoodSafetyProblemsReultDto> seleteAllGongsiSinglelist(JdyAppletFoodSafetyProblemsReult param);
}