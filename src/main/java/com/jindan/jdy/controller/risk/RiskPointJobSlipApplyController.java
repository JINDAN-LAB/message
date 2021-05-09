package com.jindan.jdy.controller.risk;

import com.jindan.jdy.common.dto.RiskPointJobSlipDetailsDto;
import com.jindan.jdy.common.dto.RiskPointJobSlipDto;
import com.jindan.jdy.common.pojo.RiskPointJobSlip;
import com.jindan.jdy.common.pojo.RiskPointJobSlipApply;
import com.jindan.jdy.service.risk.RiskPointContentResultService;
import com.jindan.jdy.service.risk.RiskPointJobSlipApplyService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointJobSlipService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 作业票批准API接口层</P>
* @version: V1.0
* @author: kong
* @time    2021年1月9日
*
*/
@Api(tags = "作业票批准")
@RestController
@RequestMapping("/riskPointJobSlipApply")
public class RiskPointJobSlipApplyController{

    @Autowired
    RiskPointJobSlipApplyService riskPointJobSlipService;

    @ApiOperation(value = "作业票查询", notes = "参数:作业票查询")
    @PostMapping("/seletejieguoRiskPoint")
    public ResultVo seletejieguoRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                          @RequestBody RiskPointJobSlipApply riskPointContent){
        List<RiskPointJobSlipApply> list = riskPointJobSlipService.seleList(riskPointContent);
        return  ResultVo.success(list);
    }

    @ApiOperation("管理员修改更新作业票")
    @PostMapping("updateRiskPointContent")
    public ResultVo updateRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                           @RequestBody RiskPointJobSlipApply userPermission){
        boolean b = riskPointJobSlipService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增作业票")
    @PostMapping("addRiskPointContent")
    public ResultVo addRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                        @RequestBody  RiskPointJobSlipApply userPermission){
       boolean index =   riskPointJobSlipService.seleDetails(userPermission);
        if(index){
            if(userPermission.getPtypes().equals("临时用电")){
                userPermission.setZstatus("电工审查");
            }else{
                userPermission.setZstatus("监护审查");
            }
        }else{
            userPermission.setZstatus("现场检查");
        }
        boolean save = riskPointJobSlipService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("作业票详情信息")
    @PostMapping("SeleteDetailsContent")
    public ResultVo SeleteDetailsContent(@ApiParam(name = "userPermission", required = true)
                                      @RequestBody RiskPointJobSlipApply userPermission){
        List<RiskPointJobSlipDto>  riskPointJobSlips =  riskPointJobSlipService.seleteDetailsContent(userPermission);
          if(riskPointJobSlips.size() > 0){
              return ResultVo.success(riskPointJobSlips);
          }else{
              return ResultVo.failed();
          }
    }

    @ApiOperation("修改正常业务批准")
    @PostMapping("updateNormalRiskPointContent")
    public ResultVo updateNormalRiskPointContent(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPointJobSlipApply userPermission){
//        查询是否符合条件
        boolean index =false;
        if(userPermission.getPtypes().equals("临时用电")){
            switch (userPermission.getZstatus()){
                case "现场检查":
                    index =   riskPointJobSlipService.seleDetails(userPermission);
                    if(index){
                        userPermission.setZstatus("电工审查");
                        riskPointJobSlipService.updateById(userPermission);
                    }else{
                        return ResultVo.failed();
                    }
                    break;
                case "电工审查" :
                    index = riskPointJobSlipService.seleDetails(userPermission);
                    if(index){
                        userPermission.setZstatus("批准作业");
                        riskPointJobSlipService.updateById(userPermission);
                    }else{
                        return ResultVo.failed();
                    }
                    break;
            }
        }else{
           switch (userPermission.getZstatus()){
               case "现场检查":
                    index =   riskPointJobSlipService.seleDetails(userPermission);
                  if(index){
                      userPermission.setZstatus("监护审查");
                      riskPointJobSlipService.updateById(userPermission);
                  }else{
                      return ResultVo.failed();
                  }
               break;
               case "监护审查" :
                     index =   riskPointJobSlipService.seleDetails(userPermission);
                   if(index){
                       userPermission.setZstatus("车间审查");
                       riskPointJobSlipService.updateById(userPermission);
                   }else{
                       return ResultVo.failed();
                   }
                   break;
               case  "车间审查":
                     index =   riskPointJobSlipService.seleDetails(userPermission);
                   if(index){
                       userPermission.setZstatus("安环审查");
                       riskPointJobSlipService.updateById(userPermission);
                   }else{
                       return ResultVo.failed();
                   }
                   break;
               case  "安环审查":
                     index =   riskPointJobSlipService.seleDetails(userPermission);
                   if(index){
                       userPermission.setZstatus("批准作业");
                       riskPointJobSlipService.updateById(userPermission);
                   }else{
                       return ResultVo.failed();
                   }
                   break;
           }
        }
        boolean b = riskPointJobSlipService.updateById(userPermission);
        if(b){
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


    @ApiOperation(value = "小程序作业是否已被整改", notes = "参数:小程序作业是否已被整改")
    @PostMapping("/seleteZhenggaiRiskPoint")
    public ResultVo seleteZhenggaiRiskPoint(@ApiParam(name = "riskPointContent", required = false)
                                          @RequestBody RiskPointJobSlipApply riskPointContent){
        List<RiskPointJobSlipDetailsDto> list = riskPointJobSlipService.seleteZhenggaiRiskPoint(riskPointContent);
        return  ResultVo.success(list);
    }


}