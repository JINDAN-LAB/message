package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
public interface RiskSpecialCertificateAccountService extends IService<RiskSpecialCertificateAccount> {

    /*RiskSpecialCertificateAccount insert(RiskSpecialCertificateAccount riskSpecialCertificateAccount);*/

    Page<RiskSpecialCertificateAccount> selectRiskSCAByPage(RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto);

    RiskSpecialCertificateAccount selectRiskSCA(RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto);

    boolean updateCertificate(RiskSpecialCertificateAccount riskSpecialCertificateAccount);

    /*特种证书台账导出*/
    List<RiskSpecialCertificateAccount> getRiskSCAExcel();

    /*特种证书台账导入*/
    void saveAllBatch(List<RiskSpecialCertificateAccount> riskSpecialCertificateAccountList);
}
