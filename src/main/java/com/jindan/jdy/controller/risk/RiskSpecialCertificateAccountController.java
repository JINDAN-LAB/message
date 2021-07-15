package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskSpecialCertificateAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
@Slf4j
@RestController
@RequestMapping("/riskSpecialCertificateAccount")
@Api(tags ="特种证书台账")
public class RiskSpecialCertificateAccountController {

    @Autowired
    private RiskSpecialCertificateAccountService riskSpecialCertificateAccountService;

    @ApiOperation(value = "新增特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/addRiskSCA")
    public ResultVo addRiskSCA(@ApiParam(name = "riskSpecialCertificateAccount", required = true)
                                                  @RequestBody RiskSpecialCertificateAccount riskSpecialCertificateAccount){

        riskSpecialCertificateAccount.setInsertTime(new Date());
        boolean save = riskSpecialCertificateAccountService.save(riskSpecialCertificateAccount);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/selectRiskSCAByPage")
    public ResultVo selectRiskSCAByPage(@ApiParam(name = "riskSpecialCertificateAccountDto",required =false)
                                                       @RequestBody RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskSpecialCertificateAccountDto.getPageSize());
        Page<RiskSpecialCertificateAccount> pageList = riskSpecialCertificateAccountService.selectRiskSCAByPage(riskSpecialCertificateAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation(value = "查询一条特种证书台账",notes = "参数：特种证书台账实体类")
    @PostMapping("/selectRiskSCA")
    public ResultVo selectRiskSCA(@ApiParam(name = "riskSpecialCertificateAccountDto",required =false)
                                                              @RequestBody RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto){

        log.info("riskSpecialCertificateAccountDto.getPageSize()的值为："+riskSpecialCertificateAccountDto.getPageSize());
        RiskSpecialCertificateAccount riskSpecialCertificateAccount = riskSpecialCertificateAccountService.selectRiskSCA(riskSpecialCertificateAccountDto);
        return ResultVo.success(riskSpecialCertificateAccount);
    }

    @ApiOperation(value = "更新特种证书台账",notes = "参数：特种证书实体类")
    @PostMapping("/updateRiskSCA")
    public ResultVo updateRiskSCA(@ApiParam(name = "riskSpecialCertificateAccount", required = true)
                                                  @RequestBody  RiskSpecialCertificateAccount riskSpecialCertificateAccount){

        boolean update = riskSpecialCertificateAccountService.updateById(riskSpecialCertificateAccount);
        if(update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("批量删除特种证书台账")
    @PostMapping("/deleteRiskSCA")
    public ResultVo deleteRiskSCA(@ApiParam(name = "list", value = "权限ID", required = true)
                                        @RequestBody  List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskSpecialCertificateAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
