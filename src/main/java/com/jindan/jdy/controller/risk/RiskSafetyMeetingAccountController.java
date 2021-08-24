package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskSafetyMeetingAccountDto;
import com.jindan.jdy.common.pojo.RiskSafetyMeetingAccount;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskSafetyMeetingAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangfang
 * @since 2021-08-17
 */
@Slf4j
@RestController
@RequestMapping("/riskSafetyMeetingAccount")
@Api(tags ="安全会议台账")
public class RiskSafetyMeetingAccountController {

    @Autowired
    private RiskSafetyMeetingAccountService riskSafetyMeetingAccountService;

    @ApiOperation(value = "分页查询安全会议台账",notes = "参数：安全会议台账实体类")
    @PostMapping("/selectRiskSMAByPage")
    public ResultVo selectRiskSMAByPage(@ApiParam(name = "riskSafetyMeetingAccountDto",required =false)
                                        @RequestBody RiskSafetyMeetingAccountDto riskSafetyMeetingAccountDto){

        log.info("riskSafetyMeetingAccountDto.getPageSize()的值为："+riskSafetyMeetingAccountDto.getPageSize());
        Page<RiskSafetyMeetingAccount> pageList = riskSafetyMeetingAccountService.selectRiskSMAByPage(riskSafetyMeetingAccountDto);
        return ResultVo.success(pageList);
    }

    @ApiOperation("批量删除安全会议台账")
    @PostMapping("/deleteRiskSMA")
    public ResultVo deleteRiskSMA(@ApiParam(name = "list", value = "权限ID", required = true)
                                  @RequestBody List<String> list){

        log.info("参数list.get(0)的值为："+list.get(0));
        boolean listDelete = riskSafetyMeetingAccountService.removeByIds(list);
        if(listDelete){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
