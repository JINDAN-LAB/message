package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskMajorHazardSourcesDto;
import com.jindan.jdy.common.pojo.RiskMajorHazardSources;
import com.jindan.jdy.mapper.RiskMajorHazardSourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-20
 */
@Service
public class RiskMajorHazardSourcesServiceImpl extends ServiceImpl<RiskMajorHazardSourcesMapper, RiskMajorHazardSources> implements RiskMajorHazardSourcesService {

    @Autowired
    private RiskMajorHazardSourcesMapper riskMajorHazardSourcesMapper;

    @Override
    public Page<RiskMajorHazardSources> selectRiskMHSByPage(RiskMajorHazardSourcesDto riskMajorHazardSourcesDto) {

        if (riskMajorHazardSourcesDto.getCurrentPage() <= 0){
            riskMajorHazardSourcesDto.setCurrentPage(1);
        }
        Page<RiskMajorHazardSources> page = new Page<>(riskMajorHazardSourcesDto.getCurrentPage(),riskMajorHazardSourcesDto.getPageSize());

        QueryWrapper<RiskMajorHazardSources> queryWrapper = new QueryWrapper<>();
        if (riskMajorHazardSourcesDto.getNameOfHazardSource() != null && !riskMajorHazardSourcesDto.getNameOfHazardSource().equals("")){
            queryWrapper.eq("name_of_hazard_source",riskMajorHazardSourcesDto.getNameOfHazardSource());
        }
        if (riskMajorHazardSourcesDto.getRegistrationNumber() != null && !riskMajorHazardSourcesDto.getRegistrationNumber().equals("")){
            queryWrapper.eq("registration_number",riskMajorHazardSourcesDto.getRegistrationNumber());
        }
        if (riskMajorHazardSourcesDto.getHazardLevel() != null && !riskMajorHazardSourcesDto.getHazardLevel().equals("")){
            queryWrapper.eq("hazard_level",riskMajorHazardSourcesDto.getHazardLevel());
        }

        return (Page<RiskMajorHazardSources>) riskMajorHazardSourcesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public RiskMajorHazardSources selectRiskMHS(RiskMajorHazardSourcesDto riskMajorHazardSourcesDto) {
        QueryWrapper<RiskMajorHazardSources> queryWrapper = new QueryWrapper<>();
        if (riskMajorHazardSourcesDto.getRmhsId() != null && !riskMajorHazardSourcesDto.getRmhsId().equals("")){
            queryWrapper.eq("rmhs_id",riskMajorHazardSourcesDto.getRmhsId());
        }
        return riskMajorHazardSourcesMapper.selectOne(queryWrapper);
    }

}
