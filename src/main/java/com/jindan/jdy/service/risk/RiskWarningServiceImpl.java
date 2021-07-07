package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskWarningDto;
import com.jindan.jdy.common.pojo.RiskWarning;
import com.jindan.jdy.mapper.RiskWarningMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-04
 */
@Service
public class RiskWarningServiceImpl extends ServiceImpl<RiskWarningMapper, RiskWarning> implements RiskWarningService {

    @Autowired
    private RiskWarningMapper riskWarningMapper;

    @Override
    public Page<RiskWarning> seleteRiskWarning(RiskWarningDto riskWarningDto) {

        if(riskWarningDto.getCurrentPage() <= 0  ){
            riskWarningDto.setCurrentPage(1);
        }
        Page<RiskWarning> page =new Page<>(riskWarningDto.getCurrentPage(),riskWarningDto.getPageSize());

        QueryWrapper<RiskWarning> queryWrapper =new QueryWrapper<>();

        String startTime = riskWarningDto.getStartTime() + " 00:00:00";
        String endTime = riskWarningDto.getEndTime() + " 23:59:59";
        if( riskWarningDto.getStartTime() !=null && riskWarningDto.getEndTime() != null){
            queryWrapper.between("warning_time",startTime,endTime);
        }else if(riskWarningDto.getStartTime() != null && riskWarningDto.getEndTime() == null){
            queryWrapper.ge("warning_time",startTime);
        }else if(riskWarningDto.getStartTime() == null && riskWarningDto.getEndTime() != null){
            queryWrapper.le("warning_time",endTime);
        }

        return (Page<RiskWarning>) riskWarningMapper.selectPage(page,queryWrapper);

    }
}
