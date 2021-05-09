package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.pojo.RiskPointJobSlipDetails;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointJobSlipDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 作业票内容API接口层</P>
* @version: V1.0
* @author: kong
* @time    2021年1月9日
*
*/
@Api(tags = "作业票内容")
@RestController
@RequestMapping("/riskPointJobSlipDetails")
public class RiskPointJobSlipDetailsController{

    @Autowired
    RiskPointJobSlipDetailsService riskPointJobSlipService;

    @ApiOperation(value = "作业票查询", notes = "参数:作业票查询")
    @PostMapping("/seletejieguoRiskPoint")
    public ResultVo seletejieguoRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                          @RequestBody RiskPointJobSlipDetails riskPointContent){
        List<RiskPointJobSlipDetails> list = riskPointJobSlipService.seleList(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新作业票")
    @PostMapping("updateRiskPointContent")
    public ResultVo updateRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                           @RequestBody RiskPointJobSlipDetails userPermission){
        boolean b = riskPointJobSlipService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增作业票")
    @PostMapping("addRiskPointContent")
    public ResultVo addRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                        @RequestBody  RiskPointJobSlipDetails userPermission){
        boolean save = riskPointJobSlipService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除作业票")
    @DeleteMapping("deleteRiskPointContenty/{id}")
    public ResultVo deleteRiskPointContent(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = riskPointJobSlipService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}