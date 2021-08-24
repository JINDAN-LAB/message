package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskSafetyMeetingAccountDto;
import com.jindan.jdy.common.pojo.RiskSafetyMeetingAccount;
import com.jindan.jdy.mapper.RiskSafetyMeetingAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-17
 */
@Service
public class RiskSafetyMeetingAccountServiceImpl extends ServiceImpl<RiskSafetyMeetingAccountMapper, RiskSafetyMeetingAccount> implements RiskSafetyMeetingAccountService {

    @Autowired
    private RiskSafetyMeetingAccountMapper riskSafetyMeetingAccountMapper;

    @Override
    public Page<RiskSafetyMeetingAccount> selectRiskSMAByPage(RiskSafetyMeetingAccountDto riskSafetyMeetingAccountDto) {
        if (riskSafetyMeetingAccountDto.getCurrentPage() <= 0){
            riskSafetyMeetingAccountDto.setCurrentPage(1);
        }
        Page<RiskSafetyMeetingAccount> page = new Page<>(riskSafetyMeetingAccountDto.getCurrentPage(),riskSafetyMeetingAccountDto.getPageSize());

        String startTime = riskSafetyMeetingAccountDto.getStartTime()+" 00:00:00";
        String endTime = riskSafetyMeetingAccountDto.getEndTime()+" 23:59:59";

        QueryWrapper<RiskSafetyMeetingAccount> queryWrapper = new QueryWrapper<>();
        if (riskSafetyMeetingAccountDto.getConferenceName() != null && !riskSafetyMeetingAccountDto.getConferenceName().equals("")){
            queryWrapper.eq("conference_name",riskSafetyMeetingAccountDto.getConferenceName());
        }
        if (riskSafetyMeetingAccountDto.getState() != null && !riskSafetyMeetingAccountDto.getState().equals("")){
            queryWrapper.eq("state",riskSafetyMeetingAccountDto.getState());
        }
        if (riskSafetyMeetingAccountDto.getStartTime() != null && riskSafetyMeetingAccountDto.getEndTime() != null){
            queryWrapper.between("actual_start_time",startTime,endTime);
        }else if (riskSafetyMeetingAccountDto.getStartTime() != null && riskSafetyMeetingAccountDto.getEndTime() == null){
            queryWrapper.ge("actual_start_time",startTime);
        }else if (riskSafetyMeetingAccountDto.getStartTime() == null && riskSafetyMeetingAccountDto.getEndTime() != null){
            queryWrapper.le("actual_start_time",endTime);
        }
        return (Page<RiskSafetyMeetingAccount>) riskSafetyMeetingAccountMapper.selectPage(page,queryWrapper);
    }
}
