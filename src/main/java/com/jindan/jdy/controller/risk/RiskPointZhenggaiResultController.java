package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.common.pojo.RiskPointZhenggaiResult;
import com.jindan.jdy.service.risk.RiskPointContentResultService;
import com.jindan.jdy.service.risk.RiskPointPersonsService;
import com.jindan.jdy.service.risk.RiskPointZhenggaiResultService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 整改返回结果API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags ="整改返回结果")
@RestController
@RequestMapping("/riskPointZhenggaiResult")
public class RiskPointZhenggaiResultController{

    @Autowired
    RiskPointZhenggaiResultService riskPointMakeoverService;

    @Autowired
    RiskPointContentResultService riskPointContentResultService;

    @ApiOperation(value = "整改返回结果查询", notes = "参数:整改返回结果查询")
    @PostMapping("/seleteRiskPointZhenggaiResult")
    public ResultVo seleteRiskPointZhenggaiResult(@ApiParam(name = "riskPointContent", required = false)
                                                  @RequestBody RiskPointZhenggaiResult riskPointContent){
        List<RiskPointZhenggaiResult> list = riskPointMakeoverService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新整改返回结果")
    @PostMapping("updateRiskPointZhenggaiResult")
    public ResultVo updateRiskPointZhenggaiResult(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPointZhenggaiResult userPermission){
        boolean b = riskPointMakeoverService.updateById(userPermission);
        RiskPointContentResult riskPointContentResult = new RiskPointContentResult();
        riskPointContentResult.setStatus(userPermission.getStatus());
        riskPointContentResult.setRisPcr(userPermission.getZid());
        riskPointContentResultService.updateById(riskPointContentResult);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增整改返回结果")
    @PostMapping("addRiskPointZhenggaiResult")
    public ResultVo addRiskPointZhenggaiResult( @ApiParam(name = "userPermission", required = true)
                                               @RequestBody  RiskPointZhenggaiResult userPermission){
        boolean save = riskPointMakeoverService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除整改返回结果")
    @DeleteMapping("deleteRiskPointZhenggaiResult/{id}")
    public ResultVo deleteRiskPointZhenggaiResult(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = riskPointMakeoverService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}