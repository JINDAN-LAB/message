package com.jindan.jdy.service.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.SecurityRiskpointJudgedDto;
import com.jindan.jdy.common.pojo.SecurityRiskpointJudged;
import com.jindan.jdy.mapper.SecurityRiskpointJudgedMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-10-12
 */
@Slf4j
@Service
public class SecurityRiskpointJudgedServiceImpl extends ServiceImpl<SecurityRiskpointJudgedMapper, SecurityRiskpointJudged> implements SecurityRiskpointJudgedService {

    @Autowired
    private SecurityRiskpointJudgedMapper securityRiskpointJudgedMapper;

    @Override
    public Page<SecurityRiskpointJudged> selectRiskpointJudgedByPage(SecurityRiskpointJudgedDto securityRiskpointJudgedDto) {
        if (securityRiskpointJudgedDto.getCurrentPage() <= 0){
            securityRiskpointJudgedDto.setCurrentPage(1);
        }
        Page<SecurityRiskpointJudged> page = new Page<>(securityRiskpointJudgedDto.getCurrentPage(),securityRiskpointJudgedDto.getPageSize());

        QueryWrapper<SecurityRiskpointJudged> queryWrapper = new QueryWrapper<>();
        if (securityRiskpointJudgedDto.getRiskpointName() != null && !securityRiskpointJudgedDto.getRiskpointName().equals("")){
            queryWrapper.eq("riskpoint_name",securityRiskpointJudgedDto.getRiskpointName());
        }
        if (securityRiskpointJudgedDto.getRiskpointLocation() != null && !securityRiskpointJudgedDto.getRiskpointLocation().equals("")){
            queryWrapper.eq("riskpoint_location",securityRiskpointJudgedDto.getRiskpointLocation());
        }
        if (securityRiskpointJudgedDto.getPersonInCharge() != null && !securityRiskpointJudgedDto.getPersonInCharge().equals("")){
            queryWrapper.eq("person_in_charge",securityRiskpointJudgedDto.getPersonInCharge());
        }
        if (securityRiskpointJudgedDto.getRiskpointClassification() != null && !securityRiskpointJudgedDto.getRiskpointClassification().equals("")){
            queryWrapper.eq("riskpoint_classification",securityRiskpointJudgedDto.getRiskpointClassification());
        }
        if (securityRiskpointJudgedDto.getRiskLevel() != null && !securityRiskpointJudgedDto.getRiskLevel().equals("")){
            queryWrapper.eq("risk_level",securityRiskpointJudgedDto.getRiskLevel());
        }
        if (securityRiskpointJudgedDto.getRiskpointStatus() != null && !securityRiskpointJudgedDto.getRiskpointStatus().equals("")){
            queryWrapper.eq("riskpoint_status",securityRiskpointJudgedDto.getRiskpointStatus());
        }
        if (securityRiskpointJudgedDto.getIsSpecialEquipment() != null && !securityRiskpointJudgedDto.getIsSpecialEquipment().equals("")){
            queryWrapper.eq("is_special_equipment",securityRiskpointJudgedDto.getIsSpecialEquipment());
        }
        return (Page<SecurityRiskpointJudged>) securityRiskpointJudgedMapper.selectPage(page,queryWrapper);
    }

    @Override
    public SecurityRiskpointJudgedDto selectRiskpointJudgedByOne(SecurityRiskpointJudged securityRiskpointJudged) {
        return securityRiskpointJudgedMapper.getRiskpointJudgedByOne(securityRiskpointJudged);
    }

    @Override
    public List<SecurityRiskpointJudgedDto> getRiskpointJudgedExcel(List<SecurityRiskpointJudged> securityRiskpointJudgedList) {
        return securityRiskpointJudgedMapper.getRiskpointJudgedExcel(securityRiskpointJudgedList);
    }

    @Override
    public boolean setRiskpointStatus(List<SecurityRiskpointJudged> securityRiskpointJudgedList) {
        //log.info("securityRiskpointJudgedList的值为："+securityRiskpointJudgedList);
        int result = securityRiskpointJudgedMapper.updateRiskpointStatus(securityRiskpointJudgedList);
        if(result > 0){
            return true;
        }
        return false;
    }
}
