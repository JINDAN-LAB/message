package com.jindan.jdy.mapper;

import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
