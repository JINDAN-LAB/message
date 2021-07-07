package com.jindan.jdy.controller.risk;


import com.jindan.jdy.common.dto.RiskRenewalHistoryDto;
import com.jindan.jdy.common.dto.RiskSpecialCertificateAccountDto;
import com.jindan.jdy.common.pojo.RiskRenewalHistory;
import com.jindan.jdy.common.pojo.RiskSpecialCertificateAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskRenewalHistoryService;
import com.jindan.jdy.service.risk.RiskSpecialCertificateAccountService;
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
@RequestMapping("/riskRenewalHistory")
@Api(tags ="更新特种证书台账证书")
public class RiskRenewalHistoryController {

    @Autowired
    private RiskSpecialCertificateAccountService riskSpecialCertificateAccountService;

    @Autowired
    private RiskRenewalHistoryService riskRenewalHistoryService;

    @ApiOperation(value = "更新特种证书台账",notes = "参数：特种证书记录实体类")
    @PostMapping("/updateCertificate")
    public ResultVo updateCertificate(@ApiParam(name = "riskRenewalHistoryDto", required = true)
                                                        @RequestBody RiskRenewalHistoryDto riskRenewalHistoryDto){

        boolean save = false;
        boolean update = false;
        RiskSpecialCertificateAccountDto riskSpecialCertificateAccountDto = new RiskSpecialCertificateAccountDto();
        riskSpecialCertificateAccountDto.setRscaId(riskRenewalHistoryDto.getRiskSpecialCertificateAccountId());

        RiskSpecialCertificateAccount riskSpecialCertificateAccountResult = riskSpecialCertificateAccountService.selectRiskSpecialCertificateAccount(riskSpecialCertificateAccountDto);

        RiskRenewalHistory riskRenewalHistory = new RiskRenewalHistory();
        riskRenewalHistory.setRiskSpecialCertificateAccountId(riskSpecialCertificateAccountResult.getRscaId());   //特种证书表id
        riskRenewalHistory.setCertificateNo(riskSpecialCertificateAccountResult.getCertificateNo());              //证书编号
        riskRenewalHistory.setTermOfValidity(riskSpecialCertificateAccountResult.getTermOfValidity());            //有效期
        riskRenewalHistory.setReviewPeriod(riskSpecialCertificateAccountResult.getReviewPeriod());                //复审期限
        riskRenewalHistory.setRenewalDate(riskRenewalHistoryDto.getRenewalDate());                                //换证日期
        riskRenewalHistory.setInsertTime(new Date());                                                             //创建日期
        save = riskRenewalHistoryService.save(riskRenewalHistory);

        RiskSpecialCertificateAccount riskSpecialCertificateAccount = new RiskSpecialCertificateAccount();
        riskSpecialCertificateAccount.setRscaId(riskRenewalHistoryDto.getRiskSpecialCertificateAccountId());       //特种证书表id
        riskSpecialCertificateAccount.setCertificateNo(riskRenewalHistoryDto.getCertificateNo());                  //证书编号
        riskSpecialCertificateAccount.setTermOfValidity(riskRenewalHistoryDto.getTermOfValidity());                //有效期
        riskSpecialCertificateAccount.setReviewPeriod(riskRenewalHistoryDto.getReviewPeriod());                    //复审期限
        update = riskSpecialCertificateAccountService.updateCertificate(riskSpecialCertificateAccount);

        if (save && update){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
