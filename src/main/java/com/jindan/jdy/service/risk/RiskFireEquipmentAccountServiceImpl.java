package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskFireEquipmentAccountDto;
import com.jindan.jdy.common.pojo.RiskFireEquipmentAccount;
import com.jindan.jdy.mapper.RiskFireEquipmentAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-07-16
 */
@Service
public class RiskFireEquipmentAccountServiceImpl extends ServiceImpl<RiskFireEquipmentAccountMapper, RiskFireEquipmentAccount> implements RiskFireEquipmentAccountService {

    @Autowired
    private RiskFireEquipmentAccountMapper riskFireEquipmentAccountMapper;

    @Override
    public Page<RiskFireEquipmentAccount> selectRiskFEAByPage(RiskFireEquipmentAccountDto riskFireEquipmentAccountDto) {

        if (riskFireEquipmentAccountDto.getCurrentPage() <= 0){
            riskFireEquipmentAccountDto.setCurrentPage(1);
        }
        Page<RiskFireEquipmentAccount> page = new Page<>(riskFireEquipmentAccountDto.getCurrentPage(),riskFireEquipmentAccountDto.getPageSize());

        QueryWrapper<RiskFireEquipmentAccount> queryWrapper = new QueryWrapper<>();
        if (riskFireEquipmentAccountDto.getEquipmentName() != null && !riskFireEquipmentAccountDto.getEquipmentName().equals("")){
            queryWrapper.eq("equipment_name",riskFireEquipmentAccountDto.getEquipmentName());
        }
        if (riskFireEquipmentAccountDto.getSpecificationAndModel() != null && !riskFireEquipmentAccountDto.getSpecificationAndModel().equals("")){
            queryWrapper.eq("specification_and_model",riskFireEquipmentAccountDto.getSpecificationAndModel());
        }
        if (riskFireEquipmentAccountDto.getSiteNumber() != null && !riskFireEquipmentAccountDto.getSiteNumber().equals("")){
            queryWrapper.eq("site_number",riskFireEquipmentAccountDto.getSiteNumber());
        }
        if (riskFireEquipmentAccountDto.getPersonInCharge() != null && !riskFireEquipmentAccountDto.getPersonInCharge().equals("")){
            queryWrapper.eq("person_in_charge",riskFireEquipmentAccountDto.getPersonInCharge());
        }
        if (riskFireEquipmentAccountDto.getDepartment() != null && !riskFireEquipmentAccountDto.getDepartment().equals("")){
            queryWrapper.eq("department",riskFireEquipmentAccountDto.getDepartment());
        }
        return (Page<RiskFireEquipmentAccount>) riskFireEquipmentAccountMapper.selectPage(page,queryWrapper);
    }

    @Override
    public RiskFireEquipmentAccount selectRiskFEA(RiskFireEquipmentAccountDto riskFireEquipmentAccountDto) {
        QueryWrapper<RiskFireEquipmentAccount> queryWrapper = new QueryWrapper<>();
        if (riskFireEquipmentAccountDto.getRfeaId() != null && !riskFireEquipmentAccountDto.getRfeaId().equals("")){
            queryWrapper.eq("rfea_id",riskFireEquipmentAccountDto.getRfeaId());
        }
        return riskFireEquipmentAccountMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateRiskFEA(RiskFireEquipmentAccount riskFireEquipmentAccount) {
        int result = riskFireEquipmentAccountMapper.updateById(riskFireEquipmentAccount);
        if(result > 0){
            return true;
        }
        return false;
    }

    //消防设备台账导出
    @Override
    public List<RiskFireEquipmentAccount> getRiskFEAExcel() {
        return riskFireEquipmentAccountMapper.getRiskFEAExcel();
    }

    //消防设备台账导入
    @Override
    public void saveAllBatch(List<RiskFireEquipmentAccount> riskFireEquipmentAccountList) {
        riskFireEquipmentAccountMapper.saveAllBatch(riskFireEquipmentAccountList);
    }
}
