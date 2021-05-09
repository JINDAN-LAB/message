package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.dto.RiskPointDtoDetails;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointPersonsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 风险控制人API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags = "风险控制人")
@RestController
@RequestMapping("/riskPointPersons")
public class RiskPointPersonsController{

    @Autowired
    RiskPointPersonsService riskPointMakeoverService;

    @ApiOperation(value = "风险控制人查询", notes = "参数:风险控制人查询")
    @PostMapping("/seleteRiskPointPersons")
    public ResultVo seleteRiskPointPersons(@ApiParam(name = "riskPointContent", required = false)
                                           @RequestBody RiskPointPersons riskPointContent){
        List<RiskPointPersons> list = riskPointMakeoverService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "风险控制人包含任务查询", notes = "参数:风险控制人包含任务查询")
    @PostMapping("/seleteRiskBaohanPointPersons")
    public ResultVo seleteRiskPointBaohanrenwuPersons(@ApiParam(name = "riskPointContent", required = false)
                                                      @RequestBody RiskPointPersons riskPointContent) throws Exception {
        List<RiskPointDtoDetails> list = riskPointMakeoverService.seleteChaoqiPersons(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "判断超期控制人包含任务查询", notes = "参数:判断超期控制人包含任务查询")
    @PostMapping("/seleteChaoqiPersons")
    public ResultVo seleteChaoqiPersons(@ApiParam(name = "riskPointContent", required = false)
                                        @RequestBody RiskPointPersons riskPointContent) throws Exception {
        List<RiskPointDtoDetails> list = riskPointMakeoverService.seleListBaohanrenwuWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新风险控制人")
    @PostMapping("updateRiskPointPersons")
    public ResultVo updateRiskPointPersons(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPointPersons userPermission){
        boolean b = riskPointMakeoverService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增风险控制人")
    @PostMapping("addRiskPointPersons")
    public ResultVo addRiskPointPersons( @ApiParam(name = "userPermission", required = true)
                                               @RequestBody  RiskPointPersons userPermission){
        boolean save = riskPointMakeoverService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除风险控制人")
    @DeleteMapping("deleteRiskPointPersons/{id}")
    public ResultVo deleteRiskPointPersons(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = riskPointMakeoverService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}