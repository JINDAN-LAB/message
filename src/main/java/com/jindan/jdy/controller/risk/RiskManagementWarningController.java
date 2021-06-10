package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskManagementWarningDto;
import com.jindan.jdy.common.pojo.RiskManagementWarning;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskManagementWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-06-07
 */
@Slf4j
@RestController
@RequestMapping("/riskManagementWarning")
@Api(tags ="风险管理预警")
public class RiskManagementWarningController {

    @Autowired
    private RiskManagementWarningService riskManagementWarningService;

    @ApiOperation(value = "查询风险管理预警信息",notes = "参数：风险管理预警实体类")
    @PostMapping("/selectRiskManagementWarning")
    public ResultVo selectRiskManagementWarning(@ApiParam(name = "riskManagementWarningDto",required =false)
                                                @RequestBody RiskManagementWarningDto riskManagementWarningDto){

        log.info("riskManagementWarningDto.getPageSize()的值为："+riskManagementWarningDto.getPageSize());
        Page<RiskManagementWarning> pageList = riskManagementWarningService.selectRiskManagementWarning(riskManagementWarningDto);
        return ResultVo.success(pageList);
    }
}
