package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskControlSituationDto;
import com.jindan.jdy.common.dto.RiskNameAndPersonnelDto;
import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.jindan.jdy.common.pojo.RiskPoint;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskControlSituationService;
import com.jindan.jdy.service.risk.RiskPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-06-11
 */
@Slf4j
@RestController
@RequestMapping("/riskControlSituation")
@Api(tags ="风险分级管控")
public class RiskControlSituationController {

    @Autowired
    private RiskControlSituationService riskControlSituationService;

    @Autowired
    RiskPointService riskPointService;

    @ApiOperation(value = "巡检人",notes = "参数：风险名称和巡检人实体类")
    @PostMapping("/selectInspectionPersonnelList")
    public ResultVo selectInspectionPersonnelList(@ApiParam(name = "riskControlSituationDto", required = false)
                                                      @RequestBody RiskNameAndPersonnelDto riskNameAndPersonnelDto){
        List<RiskControlSituation> riskControlSituationsList = riskControlSituationService.selectListRiskControlSituation(riskNameAndPersonnelDto);
        return ResultVo.success(riskControlSituationsList);
    }

    @ApiOperation(value = "风险分级管控",notes = "参数：风险名称和巡检人实体类")
    @PostMapping("/selectRiskControlSituationByPage")
    public ResultVo selectRiskControlSituationByPage(@ApiParam(name = "riskControlSituationDto", required = false)
                                                   @RequestBody RiskNameAndPersonnelDto riskNameAndPersonnelDto){

        Page<RiskPoint> listRiskPoint = riskPointService.selectListWapperByPage(riskNameAndPersonnelDto);
        List<RiskControlSituationDto> pageRiskControlSituationDto = new ArrayList<>();
        for (RiskPoint riskPoint : listRiskPoint.getRecords()) {
            String rid = riskPoint.getRid();
            riskNameAndPersonnelDto.setRiskNameId(rid);
            List<RiskControlSituation> riskControlSituationsList = riskControlSituationService.selectListRiskControlSituation(riskNameAndPersonnelDto);
            RiskControlSituationDto dtoRiskControlSituationDto = new RiskControlSituationDto();
            dtoRiskControlSituationDto.setRiskPoint(riskPoint);
            dtoRiskControlSituationDto.setListRiskControlSituation(riskControlSituationsList);
            pageRiskControlSituationDto.add(dtoRiskControlSituationDto);
        }
        return ResultVo.success(pageRiskControlSituationDto);
    }
}
