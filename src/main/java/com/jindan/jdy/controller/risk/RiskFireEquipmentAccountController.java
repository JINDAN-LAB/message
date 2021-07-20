package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskFireEquipmentAccountDto;
import com.jindan.jdy.common.pojo.RiskFireEquipmentAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskFireEquipmentAccountService;
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
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-07-16
 */
@Slf4j
@RestController
@RequestMapping("/riskFireEquipmentAccount")
@Api(tags ="消防设备台账")
public class RiskFireEquipmentAccountController {

    @Autowired
    private RiskFireEquipmentAccountService riskFireEquipmentAccountService;

    @ApiOperation(value = "新增消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/addRiskFEA")
    public ResultVo addRiskFEA(@ApiParam(name = "riskFireEquipmentAccount", required = true)
                                   @RequestBody RiskFireEquipmentAccount riskFireEquipmentAccount){

        riskFireEquipmentAccount.setInsertTime(new Date());
        boolean save = riskFireEquipmentAccountService.save(riskFireEquipmentAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/selectRiskFEAByPage")
    public ResultVo selectRiskFEAByPage(@ApiParam(name = "riskFireEquipmentAccountDto",required =false)
                                            @RequestBody RiskFireEquipmentAccountDto riskFireEquipmentAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskFireEquipmentAccountDto.getPageSize());
        Page<RiskFireEquipmentAccount> pageList = riskFireEquipmentAccountService.selectRiskFEAByPage(riskFireEquipmentAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条消防设备台账",notes = "参数：消防设备台账实体类")
    @PostMapping("/selectRiskFEA")
    public ResultVo selectRiskFEA(@ApiParam(name = "riskFireEquipmentAccountDto",required =false)
                                  @RequestBody RiskFireEquipmentAccountDto riskFireEquipmentAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskFireEquipmentAccountDto.getPageSize());
        RiskFireEquipmentAccount riskFireEquipmentAccount = riskFireEquipmentAccountService.selectRiskFEA(riskFireEquipmentAccountDto);
        return ResultVo.success(riskFireEquipmentAccount);
    }

    @ApiOperation(value = "更新消防设备台账",notes = "参数：消防设备实体类")
    @PostMapping("/updateRiskFEA")
    public ResultVo updateRiskFEA(@ApiParam(name = "riskFireEquipmentAccount", required = true)
                                  @RequestBody  RiskFireEquipmentAccount riskFireEquipmentAccount){

        boolean update = riskFireEquipmentAccountService.updateById(riskFireEquipmentAccount);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除特种证书台账")
    @PostMapping("/deleteRiskFEA")
    public ResultVo deleteRiskFEA(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskFireEquipmentAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
