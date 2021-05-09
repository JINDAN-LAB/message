package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.RiskPointDtoDetails;
import com.jindan.jdy.common.pojo.RiskPointPersons;

import java.util.List;

/**   
 * @Description:TODO(风险控制人服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointPersonsService extends IService<RiskPointPersons> {

    List<RiskPointPersons> seleListWapper(RiskPointPersons riskPointContent);

    List<RiskPointDtoDetails> seleListBaohanrenwuWapper(RiskPointPersons riskPointContent) throws Exception;

    List<RiskPointDtoDetails> seleteChaoqiPersons(RiskPointPersons riskPointContent) throws Exception;

    List<RiskPointDtoDetails> seleListchaoqiBaohanrenwuWapper(RiskPointPersons riskPointContent) throws Exception;
}