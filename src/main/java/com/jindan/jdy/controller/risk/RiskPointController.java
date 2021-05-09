package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.dto.RiskPointDto;
import com.jindan.jdy.common.pojo.RiskPoint;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.jindan.jdy.service.risk.RiskPointContentResultService;
import com.jindan.jdy.service.risk.RiskPointContentService;
import com.jindan.jdy.service.risk.RiskPointService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 风险控制API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags ="风险控制")
@RestController
@RequestMapping("/riskPoint")
public class RiskPointController{

    @Autowired
    RiskPointService riskPointService;

    @Autowired
    RiskPointContentService riskPointContentService;

    @ApiOperation(value = "风险控制查询", notes = "参数:风险控制点内容查询")
    @PostMapping("/seleteRiskPoint")
    public ResultVo seleteRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                    @RequestBody RiskPoint riskPointContent){
        List<RiskPoint> list = riskPointService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "包含查询风险控制查询", notes = "参数:风险控制点内容查询")
    @PostMapping("/seleteBaohanRiskPoint")
    public ResultVo seleteBaohanRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                    @RequestBody RiskPoint riskPointContent){
        List<RiskPointDto> list = riskPointService.seleListBaohanWapper(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新风险控制")
    @PostMapping("updateRiskPoint")
    public ResultVo updateRiskPoint(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPoint userPermission){
        boolean b = riskPointService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("更新风险控制")
    @PostMapping("updateRiskPointDto")
    public ResultVo updateRiskPointDto(@ApiParam(name = "userPermission", required = true)
                                    @RequestBody RiskPointDto userPermission){
        RiskPoint  riskPoint =new RiskPoint();
        BeanUtils.copyProperties(userPermission,riskPoint);
        boolean b = riskPointService.updateById(riskPoint);
        for (int i = 0; i < userPermission.getList().size(); i++) {
            if(userPermission.getList().get(i).getRiskIds() == null || userPermission.getList().get(i).getRiskIds().isEmpty() || " ".equals(userPermission.getList().get(i).getRiskIds())){
                riskPointContentService.save(userPermission.getList().get(i));
            }else{
                riskPointContentService.updateById(userPermission.getList().get(i));
            }
        }
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("包含风控内容新增风险控制")
    @PostMapping("addBaohanRiskPoint")
    public ResultVo addBaohanRiskPoint(@ApiParam(name = "userPermission", required = true)
                                       @RequestBody RiskPointDto userPermission){
        RiskPoint riskPoint =new RiskPoint();
        BeanUtils.copyProperties(userPermission,riskPoint);
        RiskPoint riskPoint1   =  riskPointService.insertSave(riskPoint);
        System.out.println(riskPoint1);
        if(riskPoint1.getRid() != null && riskPoint1.getRid() != ""){
            for (int i = 0; i <userPermission.getList().size() ; i++) {
                userPermission.getList().get(i).setParentId(riskPoint1.getRid());
            }
            riskPointContentService.saveBatch(userPermission.getList());
        }
       return ResultVo.success();
    }

    @ApiOperation("单独新增风险控制")
    @PostMapping("addRiskPoint")
    public ResultVo addRiskPoint( @ApiParam(name = "userPermission", required = true)
                                               @RequestBody  RiskPoint userPermission){
        boolean save = riskPointService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除风险控制")
    @DeleteMapping("deleteRiskPoint/{id}")
    public ResultVo deleteRiskPoint(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = riskPointService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}