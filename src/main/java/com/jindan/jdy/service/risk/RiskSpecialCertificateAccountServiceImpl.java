package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.jindan.jdy.mapper.RiskSpecialCertificateAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
@Service
public class RiskSpecialCertificateAccountServiceImpl extends ServiceImpl<RiskSpecialCertificateAccountMapper, RiskSpecialCertificateAccount> implements RiskSpecialCertificateAccountService {

    @Autowired
    private RiskSpecialCertificateAccountMapper riskSpecialCertificateAccountMapper;

    /*@Override
    public RiskSpecialCertificateAccount insert(RiskSpecialCertificateAccount riskSpecialCertificateAccount) {
        if(riskSpecialCertificateAccountMapper.insert(riskSpecialCertificateAccount) > 0){
            return riskSpecialCertificateAccount;
        }
        return riskSpecialCertificateAccount;
    }*/

    @Override
    public Page<RiskSpecialCertificateAccount> selectRiskSCAByPage(RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto) {

        if (riskSpecialCertificateAccountDto.getCurrentPage() <= 0){
            riskSpecialCertificateAccountDto.setCurrentPage(1);
        }
        Page<RiskSpecialCertificateAccount> page = new Page<>(riskSpecialCertificateAccountDto.getCurrentPage(),riskSpecialCertificateAccountDto.getPageSize());

        String startTime = riskSpecialCertificateAccountDto.getStartTime()+" 00:00:00";
        String endTime = riskSpecialCertificateAccountDto.getEndTime()+" 23:59:59";

        QueryWrapper<RiskSpecialCertificateAccount> queryWrapper = new QueryWrapper<>();
        if (riskSpecialCertificateAccountDto.getCertificateNo() != null && !riskSpecialCertificateAccountDto.getCertificateNo().equals("")){
            queryWrapper.eq("certificate_no",riskSpecialCertificateAccountDto.getCertificateNo());
        }
        if (riskSpecialCertificateAccountDto.getCertificateHolder() != null && !riskSpecialCertificateAccountDto.getCertificateHolder().equals("")){
            queryWrapper.eq("certificate_holder",riskSpecialCertificateAccountDto.getCertificateHolder());
        }
        if (riskSpecialCertificateAccountDto.getJobCategory() != null && !riskSpecialCertificateAccountDto.getJobCategory().equals("")){
            queryWrapper.eq("job_category",riskSpecialCertificateAccountDto.getJobCategory());
        }
        if (riskSpecialCertificateAccountDto.getStandardOperationItems() != null && !riskSpecialCertificateAccountDto.getStandardOperationItems().equals("")){
            queryWrapper.eq("standard_operation_items",riskSpecialCertificateAccountDto.getStandardOperationItems());
        }
        if (riskSpecialCertificateAccountDto.getStartTime() != null && riskSpecialCertificateAccountDto.getEndTime() != null){
            queryWrapper.between("warning_time",startTime,endTime);
        }else if (riskSpecialCertificateAccountDto.getStartTime() != null && riskSpecialCertificateAccountDto.getEndTime() == null){
            queryWrapper.ge("warning_time",startTime);
        }else if (riskSpecialCertificateAccountDto.getStartTime() == null && riskSpecialCertificateAccountDto.getEndTime() != null){
            queryWrapper.le("warning_time",endTime);
        }
        return (Page<RiskSpecialCertificateAccount>) riskSpecialCertificateAccountMapper.selectPage(page,queryWrapper);
    }

    @Override
    public RiskSpecialCertificateAccount selectRiskSCA(RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto) {
        QueryWrapper<RiskSpecialCertificateAccount> queryWrapper = new QueryWrapper<>();
        if (riskSpecialCertificateAccountDto.getRscaId() != null && !riskSpecialCertificateAccountDto.getRscaId().equals("")){
            queryWrapper.eq("rsca_id",riskSpecialCertificateAccountDto.getRscaId());
        }
        return riskSpecialCertificateAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateCertificate(RiskSpecialCertificateAccount riskSpecialCertificateAccount) {
        int result = riskSpecialCertificateAccountMapper.updateSave(riskSpecialCertificateAccount);
        if(result > 0){
            return true;
        }
        return false;
    }
}
