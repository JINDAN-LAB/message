package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskMonitorWarningDto;
import com.jindan.jdy.common.pojo.RiskMonitorWarning;
import com.jindan.jdy.mapper.RiskMonitorWarningDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RiskMonitorWarningServiceImpl extends ServiceImpl<RiskMonitorWarningDao, RiskMonitorWarning> implements RiskMonitorWarningService {

    @Autowired
    private RiskMonitorWarningDao riskMonitorWarningDao;

    @Override
    public Page<RiskMonitorWarning> seleteRiskMonitorWarning(RiskMonitorWarningDto RiskMonitorWarningDto) {

        if(RiskMonitorWarningDto.getCurrentPage() <= 0  ){
            RiskMonitorWarningDto.setCurrentPage(1);
        }
        Page<RiskMonitorWarning> page =new Page<>(RiskMonitorWarningDto.getCurrentPage(),RiskMonitorWarningDto.getPageSize());

        QueryWrapper<RiskMonitorWarning> queryWrapper =new QueryWrapper<>();

        String startTime = RiskMonitorWarningDto.getStartTime() + " 00:00:00";
        String endTime = RiskMonitorWarningDto.getEndTime() + " 23:59:59";
        if( RiskMonitorWarningDto.getStartTime() !=null && RiskMonitorWarningDto.getEndTime() != null){
            queryWrapper.between("warning_time",startTime,endTime);
        }else if(RiskMonitorWarningDto.getStartTime() != null && RiskMonitorWarningDto.getEndTime() == null){
            queryWrapper.ge("warning_time",startTime);
        }else if(RiskMonitorWarningDto.getStartTime() == null && RiskMonitorWarningDto.getEndTime() != null){
            queryWrapper.le("warning_time",endTime);
        }

        return (Page<RiskMonitorWarning>) riskMonitorWarningDao.selectPage(page,queryWrapper);

    }
}
