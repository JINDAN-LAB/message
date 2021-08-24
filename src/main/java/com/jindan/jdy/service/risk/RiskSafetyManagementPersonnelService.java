package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSafetyManagementPersonnelDto;
import com.jindan.jdy.common.pojo.RiskSafetyManagementPersonnel;
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
public interface RiskSafetyManagementPersonnelService extends IService<RiskSafetyManagementPersonnel> {

    Page<RiskSafetyManagementPersonnel> selectRiskSMPByPage(RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto);

    RiskSafetyManagementPersonnel selectRiskSMP(RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto);

    boolean updateSave(RiskSafetyManagementPersonnel riskSafetyManagementPersonnel);

    /*特种证书台账导出*/
    List<RiskSafetyManagementPersonnel> getRiskSMPExcel();

    /*特种证书台账导入*/
    void saveAllBatch(List<RiskSafetyManagementPersonnel> riskSafetyManagementPersonnelList);
}
