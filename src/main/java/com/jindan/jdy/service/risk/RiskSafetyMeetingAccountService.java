package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSafetyMeetingAccountDto;
import com.jindan.jdy.common.pojo.RiskSafetyMeetingAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-17
 */
public interface RiskSafetyMeetingAccountService extends IService<RiskSafetyMeetingAccount> {

    Page<RiskSafetyMeetingAccount> selectRiskSMAByPage(RiskSafetyMeetingAccountDto riskSafetyMeetingAccountDto);
}
