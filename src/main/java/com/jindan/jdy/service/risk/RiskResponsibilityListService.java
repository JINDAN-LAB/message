package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.DepartmentAndDutiesDto;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.dto.RiskResponsibilityListDto;
import com.jindan.jdy.common.pojo.RiskResponsibilityList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-17
 */

public interface RiskResponsibilityListService extends IService<RiskResponsibilityList> {

    RiskResponsibilityList insertSave(RiskResponsibilityList riskResponsibilityList);

    List<RiskResponsibilityList> selectRiskResponsibilityList(DepartmentAndDutiesDto departmentAndDutiesDto);

}
