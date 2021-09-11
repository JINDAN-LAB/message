package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskEquipmentFacilitiesAccountDto;
import com.jindan.jdy.common.pojo.RiskEquipmentFacilitiesAccount;
import com.jindan.jdy.mapper.RiskEquipmentFacilitiesAccountMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-06
 */
@Service
public class RiskEquipmentFacilitiesAccountServiceImpl extends ServiceImpl<RiskEquipmentFacilitiesAccountMapper, RiskEquipmentFacilitiesAccount> implements RiskEquipmentFacilitiesAccountService {

    @Autowired
    private RiskEquipmentFacilitiesAccountMapper riskEquipmentFacilitiesAccountMapper;

    @Override
    public Page<RiskEquipmentFacilitiesAccount> selectRiskEFAByPage(RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto) {

        if (riskEquipmentFacilitiesAccountDto.getCurrentPage() <= 0){
            riskEquipmentFacilitiesAccountDto.setCurrentPage(1);
        }
        Page<RiskEquipmentFacilitiesAccount> page = new Page<>(riskEquipmentFacilitiesAccountDto.getCurrentPage(),riskEquipmentFacilitiesAccountDto.getPageSize());

        String startTime = riskEquipmentFacilitiesAccountDto.getStartTime()+" 00:00:00";
        String endTime = riskEquipmentFacilitiesAccountDto.getEndTime()+" 23:59:59";

        QueryWrapper<RiskEquipmentFacilitiesAccount> queryWrapper = new QueryWrapper<>();
        if (riskEquipmentFacilitiesAccountDto.getEquipmentName() != null && !riskEquipmentFacilitiesAccountDto.getEquipmentName().equals("")){
            queryWrapper.eq("equipment_name",riskEquipmentFacilitiesAccountDto.getEquipmentName());
        }
        if (riskEquipmentFacilitiesAccountDto.getRegistrationCertificateNo() != null && !riskEquipmentFacilitiesAccountDto.getRegistrationCertificateNo().equals("")){
            queryWrapper.eq("registration_certificate_no",riskEquipmentFacilitiesAccountDto.getRegistrationCertificateNo());
        }
        if (riskEquipmentFacilitiesAccountDto.getLicenseNo() != null && !riskEquipmentFacilitiesAccountDto.getLicenseNo().equals("")){
            queryWrapper.eq("license_no",riskEquipmentFacilitiesAccountDto.getLicenseNo());
        }
        if (riskEquipmentFacilitiesAccountDto.getSpecificationAndModel() != null && !riskEquipmentFacilitiesAccountDto.getSpecificationAndModel().equals("")){
            queryWrapper.eq("specification_and_model",riskEquipmentFacilitiesAccountDto.getSpecificationAndModel());
        }
        if (riskEquipmentFacilitiesAccountDto.getFactoryNumber() != null && !riskEquipmentFacilitiesAccountDto.getFactoryNumber().equals("")){
            queryWrapper.eq("factory_number",riskEquipmentFacilitiesAccountDto.getFactoryNumber());
        }
        if (riskEquipmentFacilitiesAccountDto.getDetectionStatus() != null && !riskEquipmentFacilitiesAccountDto.getDetectionStatus().equals("")){
            queryWrapper.eq("detection_status",riskEquipmentFacilitiesAccountDto.getDetectionStatus());
        }
        if (riskEquipmentFacilitiesAccountDto.getStartTime() != null && riskEquipmentFacilitiesAccountDto.getEndTime() != null){
            queryWrapper.between("next_overhaul_time",startTime,endTime);
        }else if (riskEquipmentFacilitiesAccountDto.getStartTime() != null && riskEquipmentFacilitiesAccountDto.getEndTime() == null){
            queryWrapper.ge("next_overhaul_time",startTime);
        }else if (riskEquipmentFacilitiesAccountDto.getStartTime() == null && riskEquipmentFacilitiesAccountDto.getEndTime() != null){
            queryWrapper.le("next_overhaul_time",endTime);
        }
        return (Page<RiskEquipmentFacilitiesAccount>) riskEquipmentFacilitiesAccountMapper.selectPage(page,queryWrapper);

    }

    @Override
    public RiskEquipmentFacilitiesAccount selectRiskEFA(RiskEquipmentFacilitiesAccountDto riskEquipmentFacilitiesAccountDto) {
        QueryWrapper<RiskEquipmentFacilitiesAccount> queryWrapper = new QueryWrapper<>();
        if (riskEquipmentFacilitiesAccountDto.getRefaId() != null && !riskEquipmentFacilitiesAccountDto.getRefaId().equals("")){
            queryWrapper.eq("refa_id",riskEquipmentFacilitiesAccountDto.getRefaId());
        }
        return riskEquipmentFacilitiesAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public List<RiskEquipmentFacilitiesAccount> getRiskEFAExcel() {
        return riskEquipmentFacilitiesAccountMapper.getRiskEFAExcel();
    }

    @Override
    public void saveAllBatch(List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList) {

        riskEquipmentFacilitiesAccountMapper.saveAllBatch(riskEquipmentFacilitiesAccountList);
    }

    @Override
    public boolean setRiskEFAPersonInCharge(List<RiskEquipmentFacilitiesAccount> riskEquipmentFacilitiesAccountList) {
        int result = riskEquipmentFacilitiesAccountMapper.updateSave(riskEquipmentFacilitiesAccountList);
        if(result > 0){
            return true;
        }
        return false;
    }
}
