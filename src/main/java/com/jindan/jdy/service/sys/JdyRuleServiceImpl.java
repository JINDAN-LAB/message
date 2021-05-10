package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyRuleDao;
import com.jindan.jdy.common.pojo.JdyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(规则服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyRuleServiceImpl  extends ServiceImpl<JdyRuleDao,JdyRule> implements JdyRuleService  {

    @Autowired
    JdyRuleDao jdyRuleDao;

    @Override
    public List<JdyRule> seletelist(JdyRule jdyDomain) {
        QueryWrapper<JdyRule> queryWrapper =new QueryWrapper<>();
        if(jdyDomain.getRuleId() !=null &&  !jdyDomain.getRuleId().isEmpty() ){
            queryWrapper.eq("rule_id",jdyDomain.getRuleId());
        }
        if(jdyDomain.getRuleOne() !=null && !jdyDomain.getRuleOne().isEmpty() ){
            queryWrapper.eq("rule_one",jdyDomain.getRuleOne());
        }
        if(jdyDomain.getRuleTwo() !=null && !jdyDomain.getRuleTwo().isEmpty() ){
            queryWrapper.eq("rule_two",jdyDomain.getRuleTwo());
        }
        if(jdyDomain.getRuleThree() !=null && !jdyDomain.getRuleThree().isEmpty() ){
            queryWrapper.eq("rule_three",jdyDomain.getRuleThree());
        }
        if(jdyDomain.getRuleThreeCondition() !=null && !jdyDomain.getRuleThreeCondition().isEmpty() ){
            queryWrapper.eq("rule_three_condition",jdyDomain.getRuleThreeCondition());
        }
        if(jdyDomain.getRuleFour() !=null &&  !jdyDomain.getRuleFour().isEmpty() ){
            queryWrapper.eq("rule_four",jdyDomain.getRuleFour());
        }
        if(jdyDomain.getRuleFourCondition() !=null && !jdyDomain.getRuleFourCondition().isEmpty() ){
            queryWrapper.eq("rule_four_condition",jdyDomain.getRuleFourCondition());
        }
        if(jdyDomain.getDepartments() !=null && !jdyDomain.getDepartments().isEmpty() ){
            queryWrapper.eq("departments",jdyDomain.getDepartments());
        }
        if(jdyDomain.getRuleType() !=null && !jdyDomain.getRuleType().isEmpty() ){
            queryWrapper.eq("rule_type",jdyDomain.getRuleType());
        }
        return jdyRuleDao.selectList(queryWrapper);
    }
}