package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskMajorHazardSourcesDto;
import com.jindan.jdy.common.pojo.RiskMajorHazardSources;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskMajorHazardSourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-08-20
 */
@Slf4j
@RestController
@RequestMapping("/riskMajorHazardSources")
@Api(tags ="重大危险源台账模块")
public class RiskMajorHazardSourcesController {

    @Autowired
    private RiskMajorHazardSourcesService riskMajorHazardSourcesService;

    @ApiOperation(value = "新增重大危险源台账",notes = "参数：重大危险源台账实体类")
    @PostMapping("/addRiskMHS")
    public ResultVo addRiskMHS(@ApiParam(name = "riskMajorHazardSources", required = true)
                               @RequestBody RiskMajorHazardSources riskMajorHazardSources){

        riskMajorHazardSources.setInsertTime(new Date());
        boolean save = riskMajorHazardSourcesService.save(riskMajorHazardSources);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询重大危险源台账",notes = "参数：重大危险源台账实体类")
    @PostMapping("/selectRiskMHSByPage")
    public ResultVo selectRiskMHSByPage(@ApiParam(name = "riskMajorHazardSourcesDto",required =false)
                                        @RequestBody RiskMajorHazardSourcesDto riskMajorHazardSourcesDto){

        log.info("riskMajorHazardSourcesDto.getPageSize()的值为："+riskMajorHazardSourcesDto.getPageSize());
        Page<RiskMajorHazardSources> pageList = riskMajorHazardSourcesService.selectRiskMHSByPage(riskMajorHazardSourcesDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条重大危险源台账",notes = "参数：重大危险源台账实体类")
    @PostMapping("/selectRiskMHS")
    public ResultVo selectRiskMHS(@ApiParam(name = "riskMajorHazardSourcesDto",required =false)
                                  @RequestBody RiskMajorHazardSourcesDto riskMajorHazardSourcesDto){

        log.info("riskMajorHazardSourcesDto.getPageSize()的值为："+riskMajorHazardSourcesDto.getPageSize());
        RiskMajorHazardSources riskMajorHazardSources = riskMajorHazardSourcesService.selectRiskMHS(riskMajorHazardSourcesDto);
        return ResultVo.success(riskMajorHazardSources);
    }

    @ApiOperation(value = "更新重大危险源台账",notes = "参数：重大危险源实体类")
    @PostMapping("/updateRiskMHS")
    public ResultVo updateRiskMHS(@ApiParam(name = "riskMajorHazardSources", required = true)
                                  @RequestBody  RiskMajorHazardSources riskMajorHazardSources){

        boolean update = riskMajorHazardSourcesService.updateById(riskMajorHazardSources);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
