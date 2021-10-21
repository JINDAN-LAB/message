package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskNameAndPersonnelDto;
import com.jindan.jdy.common.pojo.RiskPoint;

/**
 * @Description:(风险控制服务层)
 * @version: V1.0
 * @author:
 *
 */
public interface RiskPersonnelHierarchyGridService {

    Page<RiskPoint> selectListWapperByPage(RiskNameAndPersonnelDto riskNameAndPersonnelDto);
}
