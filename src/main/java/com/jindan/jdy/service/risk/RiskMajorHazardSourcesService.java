package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskMajorHazardSourcesDto;
import com.jindan.jdy.common.pojo.RiskMajorHazardSources;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-20
 */
public interface RiskMajorHazardSourcesService extends IService<RiskMajorHazardSources> {

    Page<RiskMajorHazardSources> selectRiskMHSByPage(RiskMajorHazardSourcesDto riskMajorHazardSourcesDto);

    RiskMajorHazardSources selectRiskMHS(RiskMajorHazardSourcesDto riskMajorHazardSourcesDto);

}
