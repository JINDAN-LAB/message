package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.RiskNameAndPersonnelDto;
import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-11
 */
public interface RiskControlSituationService extends IService<RiskControlSituation> {

    List<RiskControlSituation> selectListRiskControlSituation(RiskNameAndPersonnelDto riskNameAndPersonnelDto);

}
