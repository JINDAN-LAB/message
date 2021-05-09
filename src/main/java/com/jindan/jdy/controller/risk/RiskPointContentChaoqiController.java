package com.jindan.jdy.controller.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskPointContentChaoqiDto;
import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointContentChaoqi;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.service.risk.RiskPointContentChaoqiService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointContentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 风险控制任务超期API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月22日
*
*/
@Api(tags = "风险控制任务超期")
@RestController
@RequestMapping("/riskPointContentChaoqi")
public class RiskPointContentChaoqiController{

    @Autowired
    RiskPointContentChaoqiService riskPointContentChaoqiService;

    @ApiOperation(value = "风险控制点内容查询", notes = "参数:风险控制点内容查询")
    @PostMapping("/seleteRiskPointContent")
    public ResultVo seleteRiskPointContent(@ApiParam(name = "riskPointContent", required = false)
                                           @RequestBody RiskPointContentChaoqiDto riskPointContent ){
        Page<RiskPointContentChaoqi> list = riskPointContentChaoqiService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新超期巡检")
    @PostMapping("updateRiskPointContent")
    public ResultVo updateRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                           @RequestBody RiskPointContentChaoqi userPermission){
        boolean b = riskPointContentChaoqiService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增超期巡检")
    @PostMapping("addRiskPointContent")
    public ResultVo addRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                        @RequestBody  RiskPointContentChaoqi userPermission){
        boolean save = riskPointContentChaoqiService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除超期巡检")
    @DeleteMapping("deleteRiskPointContenty/{id}")
    public ResultVo deleteRiskPointContent(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String id){
        boolean b = riskPointContentChaoqiService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}