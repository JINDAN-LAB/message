package com.jindan.jdy.mapper;

import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
@Mapper
public interface RiskSpecialCertificateAccountMapper extends BaseMapper<RiskSpecialCertificateAccount> {

    int updateSave(RiskSpecialCertificateAccount riskSpecialCertificateAccount);

    /*特种证书台账导出*/
    List<RiskSpecialCertificateAccount> getRiskSCAExcel();

    /*特种证书台账导入*/
    void saveAllBatch(List<RiskSpecialCertificateAccount> list);
}
