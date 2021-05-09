package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.RiskPointMakeover;

import java.util.List;

/**   
 * @Description:TODO(风险控制转让服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointMakeoverService extends IService<RiskPointMakeover> {

    List<RiskPointMakeover> seleListWapper(RiskPointMakeover riskPointContent);
}