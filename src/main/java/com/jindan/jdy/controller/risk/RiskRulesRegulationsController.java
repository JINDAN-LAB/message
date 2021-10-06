package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskRulesRegulationsDto;
import com.jindan.jdy.common.pojo.RiskRulesRegulations;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskRulesRegulationsService;
import com.jindan.jdy.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-08-31
 */
@Slf4j
@RestController
@RequestMapping("/riskRulesRegulations")
@Api(tags ="规章制度")
public class RiskRulesRegulationsController {

    @Autowired
    private RiskRulesRegulationsService riskRulesRegulationsService;

    @ApiOperation(value = "新增规章制度",notes = "参数：规章制度实体类")
    @PostMapping("/addRiskRulesRegulations")
    public ResultVo addRiskRulesRegulations(@ApiParam(name = "riskRulesRegulations", required = true)
                                                @RequestParam("file") MultipartFile file,
                                            @RequestParam("systemName") String systemName,
                                            @RequestParam("developmentDate") String developmentDate,
                                            @RequestParam("versionNumber") String versionNumber,
                                            @RequestParam("scopeOfUse") String scopeOfUse) throws Exception {

        log.info("file.getOriginalFilename()的值为："+file.getOriginalFilename());
        log.info("systemName的值为："+systemName);

        String url = riskRulesRegulationsService.updateFileUrl(file);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        RiskRulesRegulations riskRulesRegulations = new RiskRulesRegulations();
        riskRulesRegulations.setSystemName(systemName);
        riskRulesRegulations.setDevelopmentDate(DateUtils.parse(simpleDateFormat.format(developmentDate)+" 00:00:00"));
        riskRulesRegulations.setVersionNumber(versionNumber);
        riskRulesRegulations.setScopeOfUse(scopeOfUse);
        riskRulesRegulations.setEnclosure(url);
        riskRulesRegulations.setState("生效");

        boolean save = riskRulesRegulationsService.save(riskRulesRegulations);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询规章制度",notes = "参数：规章制度实体类")
    @PostMapping("/selectRiskRulesByPage")
    public ResultVo selectRiskRulesByPage(@ApiParam(name = "riskRulesRegulationsDto",required =false)
                                        @RequestBody RiskRulesRegulationsDto riskRulesRegulationsDto){

        log.info("riskRulesRegulationsDto.getPageSize()的值为："+riskRulesRegulationsDto.getPageSize());
        Page<RiskRulesRegulations> pageList = riskRulesRegulationsService.selectRiskRulesByPage(riskRulesRegulationsDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条规章制度",notes = "参数：规章制度实体类")
    @PostMapping("/selectRiskRules")
    public ResultVo selectRiskRules(@ApiParam(name = "riskRulesRegulationsDto",required =false)
                                  @RequestBody RiskRulesRegulationsDto riskRulesRegulationsDto){

        log.info("riskRulesRegulationsDto.getPageSize()的值为："+riskRulesRegulationsDto.getPageSize());
        RiskRulesRegulations riskRulesRegulations = riskRulesRegulationsService.selectRiskRules(riskRulesRegulationsDto);
        return ResultVo.success(riskRulesRegulations);
    }

    @ApiOperation(value = "更新规章制度",notes = "参数：规章制度实体类")
    @PostMapping("/updateRiskRules")
    public ResultVo updateRiskRules(@ApiParam(name = "riskRulesRegulations", required = true)
                                      @RequestParam("file") MultipartFile file,
                                  @RequestParam("systemName") String systemName,
                                  @RequestParam("developmentDate") String developmentDate,
                                  @RequestParam("versionNumber") String versionNumber,
                                  @RequestParam("scopeOfUse") String scopeOfUse,
                                  @RequestParam("state") String state){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RiskRulesRegulations riskRulesRegulations = new RiskRulesRegulations();
        riskRulesRegulations.setSystemName(systemName);
        riskRulesRegulations.setDevelopmentDate(DateUtils.parse(simpleDateFormat.format(developmentDate)+" 00:00:00"));
        riskRulesRegulations.setVersionNumber(versionNumber);
        riskRulesRegulations.setScopeOfUse(scopeOfUse);
        riskRulesRegulations.setState(state);

        boolean update = riskRulesRegulationsService.updateById(riskRulesRegulations);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除规章制度")
    @PostMapping("/deleteRiskRules")
    public ResultVo deleteRiskRules(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskRulesRegulationsService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
