package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.dto.RiskWarningDto;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import com.jindan.jdy.common.pojo.RiskPointZhenggaiResult;
import com.jindan.jdy.common.pojo.RiskWarning;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  风险预警管理
 * </p>
 *
 * @author liangfang
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/riskWarning")
@Api(tags ="风险预警管理")
public class RiskWarningController {

    @Autowired
    public RiskWarningService riskWarningService;

    @ApiOperation(value = "查询风险预警管理信息", notes = "参数:风险预警管理实体")
    @PostMapping("/selectRiskWarning")
    public ResultVo selectRiskWarning(@ApiParam(name = "riskWarningDto", required = false)
                                     @RequestBody RiskWarningDto riskWarningDto ){
        Page<RiskWarning> list = riskWarningService.seleteRiskWarning(riskWarningDto);
        return ResultVo.success(list);
    }

}
