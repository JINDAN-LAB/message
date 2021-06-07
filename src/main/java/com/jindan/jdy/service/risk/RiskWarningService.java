package com.jindan.jdy.service.risk;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.dto.RiskWarningDto;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import com.jindan.jdy.common.pojo.RiskWarning;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-04
 */
public interface RiskWarningService extends IService<RiskWarning> {

    Page<RiskWarning> seleteRiskWarning(RiskWarningDto riskWarningDto);

}
