package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskEquipmentFacilitiesAccountDto;
import com.jindan.jdy.common.pojo.RiskEquipmentFacilitiesAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-06
 */
public interface RiskEquipmentFacilitiesAccountService extends IService<RiskEquipmentFacilitiesAccount> {

    Page<RiskEquipmentFacilitiesAccount> selectRiskEFAByPage(RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto);

    RiskEquipmentFacilitiesAccount selectRiskEFA(RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto);

    /*设备设施台账导出*/
    List<RiskEquipmentFacilitiesAccount> getRiskEFAExcel();

    /*设备设施台账导入*/
    void saveAllBatch(List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList);

    boolean setRiskEFAPersonInCharge(List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList);
}
