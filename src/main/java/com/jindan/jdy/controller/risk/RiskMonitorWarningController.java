package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskMonitorWarningDto;
import com.jindan.jdy.common.pojo.RiskMonitorWarning;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskMonitorWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  风险监控管理
 * </p>
 *
 * @author liangfang
 * @since 2021-06-07
 */
@RestController
@RequestMapping("/riskMonitorWarning")
@Api(tags ="风险监控管理")
public class RiskMonitorWarningController {

    @Autowired
    public RiskMonitorWarningService riskMonitorWarningService;

    @ApiOperation(value = "查询风险监控管理信息", notes = "参数:风险监控管理实体")
    @PostMapping("/selectRiskMonitorWarning")
    public ResultVo selectRiskMonitor(@ApiParam(name = "riskMonitorDto", required = false)
                                      @RequestBody RiskMonitorWarningDto riskMonitorWarningDto ){
        Page<RiskMonitorWarning> list = riskMonitorWarningService.seleteRiskMonitorWarning(riskMonitorWarningDto);
        return ResultVo.success(list);
    }

}
