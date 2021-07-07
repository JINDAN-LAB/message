package com.jindan.jdy.controller.risk;


import com.jindan.jdy.common.pojo.RiskEmergencyDisposalCard;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskEmergencyDisposalCardService;
import com.jindan.jdy.service.user.JdyUserService;
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
 * @since 2021-06-17
 */

@Slf4j
@RestController
@RequestMapping("/riskEmergencyDisposalCard")
@Api(tags ="责任清单--应急处置卡")
public class RiskEmergencyDisposalCardController {

    @Autowired
    private RiskEmergencyDisposalCardService riskEmergencyDisposalCardService;

    @Autowired
    private JdyUserService jdyUserService;

    @ApiOperation(value = "新增或修改应急处置卡",notes = "参数：应急处置卡实体类")
    @PostMapping("/addOrUpdateRiskEmergencyDisposalCard")
    public ResultVo addOrUpdateRiskEmergencyDisposalCard(@ApiParam(name = "riskEmergencyDisposalCard", required = true)
                                                             @RequestBody RiskEmergencyDisposalCard riskEmergencyDisposalCard){

        boolean yesOrNot = false;
        String rlId = riskEmergencyDisposalCard.getEdcId();

        if (rlId == null || rlId.equals("")){
            riskEmergencyDisposalCard.setInsertTime(new Date());
            boolean save = riskEmergencyDisposalCardService.save(riskEmergencyDisposalCard);
            if(save){
                yesOrNot = true;
            }
        }else {
            boolean update = riskEmergencyDisposalCardService.updateById(riskEmergencyDisposalCard);
            if(update){
                yesOrNot = true;
            }
        }
        if (yesOrNot){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}
