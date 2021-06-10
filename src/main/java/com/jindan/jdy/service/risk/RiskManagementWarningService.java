package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskManagementWarningDto;
import com.jindan.jdy.common.pojo.RiskManagementWarning;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-07
 */
public interface RiskManagementWarningService extends IService<RiskManagementWarning> {

    Page<RiskManagementWarning> selectRiskManagementWarning(RiskManagementWarningDto riskManagementWarningDto);
}
