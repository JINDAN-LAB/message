package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskFireEquipmentAccountDto;
import com.jindan.jdy.common.pojo.RiskFireEquipmentAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-07-16
 */
public interface RiskFireEquipmentAccountService extends IService<RiskFireEquipmentAccount> {

    Page<RiskFireEquipmentAccount> selectRiskFEAByPage(RiskFireEquipmentAccountDto riskFireEquipmentAccountDto);

    RiskFireEquipmentAccount selectRiskFEA(RiskFireEquipmentAccountDto riskFireEquipmentAccountDto);

    boolean updateRiskFEA(RiskFireEquipmentAccount riskFireEquipmentAccount);
}
