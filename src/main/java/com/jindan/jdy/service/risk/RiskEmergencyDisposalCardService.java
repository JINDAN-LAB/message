package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.DepartmentAndDutiesDto;
import com.jindan.jdy.common.pojo.RiskEmergencyDisposalCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-17
 */

public interface RiskEmergencyDisposalCardService extends IService<RiskEmergencyDisposalCard> {

    RiskEmergencyDisposalCard selectRiskEmergencyDisposalCard(DepartmentAndDutiesDto departmentAndDutiesDto);
}
