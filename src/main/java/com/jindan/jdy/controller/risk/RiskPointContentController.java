package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 风险控制点内容API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags ="风险控制点内容")
@RestController
@RequestMapping("/riskPointContent")
public class RiskPointContentController{

    @Autowired
    RiskPointContentService riskPointContentService;

    @ApiOperation(value = "风险控制点内容查询", notes = "参数:风险控制点内容查询")
    @PostMapping("/seleteRiskPointContent")
    public ResultVo seleteRiskPointContent(@ApiParam(name = "riskPointContent", required = false)
                                     @RequestBody RiskPointContent riskPointContent ){
            List<RiskPointContent> list = riskPointContentService.seleListWapper(riskPointContent);
            return  ResultVo.success(list);
    }

    @ApiOperation(value = "包含查询扫一扫风险控制查询", notes = "参数:风险扫一扫控制点内容查询")
    @GetMapping("/seleteSaoMaBaohan/{shebeiID}/{uname}")
    public ResultVo seleteSaoMaBaohan(@PathVariable("shebeiID") String riskPointContent,@PathVariable("uname") String  uname){
        List<RiskPointContentDto> list = riskPointContentService.seleteSaoMaBaohan(riskPointContent,uname);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "包含风险控制点内容查询", notes = "参数:包含风险控制点内容查询")
    @PostMapping("/seleteBaohanRiskPointContent")
    public ResultVo seleteBaohanRiskPointContent(@ApiParam(name = "riskPointContent", required = false)
                                           @RequestBody RiskPointPersons riskPointContent ) throws Exception {
        List<RiskPointContent> list = riskPointContentService.seletPersonsContent(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "返回关联查询风险控制查询", notes = "参数:返回关联查询风险控制查询")
    @PostMapping("/seletejieguoRiskPoint")
    public ResultVo seletejieguoRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                          @RequestBody RiskPointContent riskPointContent){
        List<RiskPointContentDto> list = riskPointContentService.seleListJieGuoWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新风险控制点内容")
    @PostMapping("updateRiskPointContent")
    public ResultVo updateRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody RiskPointContent userPermission){
        boolean b = riskPointContentService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增风险控制点内容息")
    @PostMapping("addRiskPointContent")
    public ResultVo addRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                        @RequestBody  RiskPointContent userPermission){
        boolean save = riskPointContentService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除建议信息")
    @DeleteMapping("deleteRiskPointContenty/{id}")
    public ResultVo deleteRiskPointContent(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = riskPointContentService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}