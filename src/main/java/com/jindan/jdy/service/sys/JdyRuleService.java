package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.JdyRule;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(规则服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyRuleService extends IService<JdyRule> {

    List<JdyRule> seletelist(JdyRule jdyDomain);
}