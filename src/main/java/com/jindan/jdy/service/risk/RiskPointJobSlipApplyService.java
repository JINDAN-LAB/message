package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.RiskPointJobSlipDetailsDto;
import com.jindan.jdy.common.dto.RiskPointJobSlipDto;
import com.jindan.jdy.common.pojo.RiskPointJobSlipApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(作业票批准服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointJobSlipApplyService extends IService<RiskPointJobSlipApply> {

    List<RiskPointJobSlipApply> seleList(RiskPointJobSlipApply riskPointContent);

    boolean seleDetails(RiskPointJobSlipApply userPermission);

    List<RiskPointJobSlipDto> seleteDetailsContent(RiskPointJobSlipApply userPermission);

    List<RiskPointJobSlipDetailsDto> seleteZhenggaiRiskPoint(RiskPointJobSlipApply riskPointContent);
}