package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskNameAndPersonnelDto;
import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.jindan.jdy.mapper.RiskControlSituationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-11
 */
@Service
public class RiskControlSituationServiceImpl extends ServiceImpl<RiskControlSituationMapper, RiskControlSituation> implements RiskControlSituationService {

    @Autowired
    private RiskControlSituationMapper riskControlSituationMapper;

    @Override
    public List<RiskControlSituation> selectListRiskControlSituation(RiskNameAndPersonnelDto riskNameAndPersonnelDto) {
        QueryWrapper<RiskControlSituation> queryWrapper = new QueryWrapper<>();
        if (riskNameAndPersonnelDto.getRiskNameId() != null && !riskNameAndPersonnelDto.getRiskNameId().equals("")){
            queryWrapper.eq("risk_name_id",riskNameAndPersonnelDto.getRiskNameId());
        }
        if (riskNameAndPersonnelDto.getInspectionPersonnel() != null && !riskNameAndPersonnelDto.getInspectionPersonnel().equals("")){
            queryWrapper.eq("inspection_personnel",riskNameAndPersonnelDto.getInspectionPersonnel());
        }
        return riskControlSituationMapper.selectList(queryWrapper);
    }

}
