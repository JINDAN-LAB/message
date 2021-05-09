package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.pojo.RiskPointMakeover;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointMakeoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 风险控制转让API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags ="风险控制转让")
@RestController
@RequestMapping("/riskPointMakeover")
public class RiskPointMakeoverController{

    @Autowired
    RiskPointMakeoverService riskPointMakeoverService;

    
    @ApiOperation(value = "风险控制转让查询", notes = "参数:风险控制转让查询")
    @PostMapping("/seleteRiskPointMakeover")
    public ResultVo seleteRiskPointMakeover(@ApiParam(name = "riskPointContent", required = false)
                                                 @RequestBody RiskPointMakeover riskPointContent){
        List<RiskPointMakeover> list = riskPointMakeoverService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新风险控制转让")
    @PostMapping("updateRiskPointMakeover")
    public ResultVo updateRiskPointMakeover(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPointMakeover userPermission){
        boolean b = riskPointMakeoverService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增风险控制转让")
    @PostMapping("addRiskPointMakeover")
    public ResultVo addRiskPointMakeover( @ApiParam(name = "userPermission", required = true)
                                         @RequestBody  RiskPointMakeover userPermission){
        boolean save = riskPointMakeoverService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除风险控制转让")
    @DeleteMapping("deleteRiskPointMakeover/{id}")
    public ResultVo deleteRiskPointMakeover(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = riskPointMakeoverService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}