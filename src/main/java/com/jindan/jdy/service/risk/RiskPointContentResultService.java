package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskPointContentResultDto;
import com.jindan.jdy.common.dto.RiskPointDetails;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(风险控制点内容返回值服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointContentResultService extends IService<RiskPointContentResult> {

    List<RiskPointContentResult> seleListWapper(RiskPointContentResult riskPointContent);

    Page<RiskPointContentResult> seleFenyeListWapper(RiskPointContentResultDto riskPointContent);

    List<RiskPointDetails> seleListDetailsWapper(RiskPointContentResult riskPointContent) throws Exception;
}