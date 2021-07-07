package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.DepartmentAndDutiesDto;
import com.jindan.jdy.common.pojo.RiskEmergencyDisposalCard;
import com.jindan.jdy.mapper.RiskEmergencyDisposalCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-17
 */

@Service
public class RiskEmergencyDisposalCardServiceImpl extends ServiceImpl<RiskEmergencyDisposalCardMapper, RiskEmergencyDisposalCard> implements RiskEmergencyDisposalCardService {

    @Autowired
    private RiskEmergencyDisposalCardMapper riskEmergencyDisposalCardMapper;

    @Override
    public RiskEmergencyDisposalCard selectRiskEmergencyDisposalCard(DepartmentAndDutiesDto departmentAndDutiesDto) {
        QueryWrapper<RiskEmergencyDisposalCard> queryWrapper = new QueryWrapper<>();
        if (departmentAndDutiesDto.getJdyUserId() != null && !departmentAndDutiesDto.getJdyUserId().equals("")){
            queryWrapper.eq("jdy_user_id",departmentAndDutiesDto.getJdyUserId());
        }
        return riskEmergencyDisposalCardMapper.selectOne(queryWrapper);
    }
}
