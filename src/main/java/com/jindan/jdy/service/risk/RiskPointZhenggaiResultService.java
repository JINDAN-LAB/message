package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.RiskPointZhenggaiResult;

import java.util.List;

/**   
 * @Description:TODO(整改返回结果服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointZhenggaiResultService extends IService<RiskPointZhenggaiResult> {

    List<RiskPointZhenggaiResult> seleListWapper(RiskPointZhenggaiResult riskPointContent);
}