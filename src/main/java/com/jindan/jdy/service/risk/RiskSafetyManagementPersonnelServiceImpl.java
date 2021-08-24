package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskSafetyManagementPersonnelDto;
import com.jindan.jdy.common.pojo.RiskSafetyManagementPersonnel;
import com.jindan.jdy.mapper.RiskSafetyManagementPersonnelMapper;
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
public class RiskSafetyManagementPersonnelServiceImpl extends ServiceImpl<RiskSafetyManagementPersonnelMapper, RiskSafetyManagementPersonnel> implements RiskSafetyManagementPersonnelService {

    @Autowired
    private RiskSafetyManagementPersonnelMapper riskSafetyManagementPersonnelMapper;

    @Override
    public Page<RiskSafetyManagementPersonnel> selectRiskSMPByPage(RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto) {

        if (riskSafetyManagementPersonnelDto.getCurrentPage() <= 0){
            riskSafetyManagementPersonnelDto.setCurrentPage(1);
        }
        Page<RiskSafetyManagementPersonnel> page = new Page<>(riskSafetyManagementPersonnelDto.getCurrentPage(),riskSafetyManagementPersonnelDto.getPageSize());

        String startTime = riskSafetyManagementPersonnelDto.getStartTime()+" 00:00:00";
        String endTime = riskSafetyManagementPersonnelDto.getEndTime()+" 23:59:59";

        QueryWrapper<RiskSafetyManagementPersonnel> queryWrapper = new QueryWrapper<>();
        if (riskSafetyManagementPersonnelDto.getSafetyManagementPersonnel() != null && !riskSafetyManagementPersonnelDto.getSafetyManagementPersonnel().equals("")){
            queryWrapper.eq("safety_management_personnel",riskSafetyManagementPersonnelDto.getSafetyManagementPersonnel());
        }
        if (riskSafetyManagementPersonnelDto.getStartTime() != null && riskSafetyManagementPersonnelDto.getEndTime() != null){
            queryWrapper.between("effective_end_time",startTime,endTime);
        }else if (riskSafetyManagementPersonnelDto.getStartTime() != null && riskSafetyManagementPersonnelDto.getEndTime() == null){
            queryWrapper.ge("effective_end_time",startTime);
        }else if (riskSafetyManagementPersonnelDto.getStartTime() == null && riskSafetyManagementPersonnelDto.getEndTime() != null){
            queryWrapper.le("effective_end_time",endTime);
        }
        return (Page<RiskSafetyManagementPersonnel>) riskSafetyManagementPersonnelMapper.selectPage(page,queryWrapper);
    }

    @Override
    public RiskSafetyManagementPersonnel selectRiskSMP(RiskSafetyManagementPersonnelDto riskSafetyManagementPersonnelDto) {
        QueryWrapper<RiskSafetyManagementPersonnel> queryWrapper = new QueryWrapper<>();
        if (riskSafetyManagementPersonnelDto.getRsmpId() != null && !riskSafetyManagementPersonnelDto.getRsmpId().equals("")){
            queryWrapper.eq("rsmp_id",riskSafetyManagementPersonnelDto.getRsmpId());
        }
        return riskSafetyManagementPersonnelMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean updateSave(RiskSafetyManagementPersonnel riskSafetyManagementPersonnel) {
        int result = riskSafetyManagementPersonnelMapper.updateSave(riskSafetyManagementPersonnel);
        if(result > 0){
            return true;
        }
        return false;
    }

    /*安全管理人员台账导出*/
    @Override
    public List<RiskSafetyManagementPersonnel> getRiskSMPExcel() {

        return riskSafetyManagementPersonnelMapper.getRiskSMPExcel();
    }

    /*安全管理人员台账导入*/
    @Override
    public void saveAllBatch(List<RiskSafetyManagementPersonnel> riskSafetyManagementPersonnelList) {
        riskSafetyManagementPersonnelMapper.saveAllBatch(riskSafetyManagementPersonnelList);
    }
}
