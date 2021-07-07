package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskManagementWarningDto;
import com.jindan.jdy.common.pojo.RiskManagementWarning;
import com.jindan.jdy.mapper.RiskManagementWarningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-07
 */
@Service
public class RiskManagementWarningServiceImpl extends ServiceImpl<RiskManagementWarningMapper, RiskManagementWarning> implements RiskManagementWarningService {

    @Autowired
    private RiskManagementWarningMapper riskManagementWarningMapper;

    @Override
    public Page<RiskManagementWarning> selectRiskManagementWarning(RiskManagementWarningDto riskManagementWarningDto) {

        if (riskManagementWarningDto.getCurrentPage() <= 0){
            riskManagementWarningDto.setCurrentPage(1);
        }
        Page<RiskManagementWarning> page = new Page<>(riskManagementWarningDto.getCurrentPage(),riskManagementWarningDto.getPageSize());

        QueryWrapper<RiskManagementWarning> queryWrapper = new QueryWrapper<>();

        String startTime = riskManagementWarningDto.getStartTime()+" 00:00:00";
        String endTime = riskManagementWarningDto.getEndTime()+" 23:59:59";

        if (riskManagementWarningDto.getStartTime() != null && riskManagementWarningDto.getEndTime() != null){
            queryWrapper.between("warning_time",startTime,endTime);
        }else if (riskManagementWarningDto.getStartTime() != null && riskManagementWarningDto.getEndTime() == null){
            queryWrapper.ge("warning_time",startTime);
        }else if (riskManagementWarningDto.getStartTime() == null && riskManagementWarningDto.getEndTime() != null){
            queryWrapper.le("warning_time",endTime);
        }
        return (Page<RiskManagementWarning>) riskManagementWarningMapper.selectPage(page,queryWrapper);
    }
}
