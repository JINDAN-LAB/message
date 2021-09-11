package com.jindan.jdy.controller.risk;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.*;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.RiskEmergencyDisposalCard;
import com.jindan.jdy.common.pojo.RiskManagementWarning;
import com.jindan.jdy.common.pojo.RiskResponsibilityList;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.common.vo.PageVO;
import com.jindan.jdy.service.risk.RiskEmergencyDisposalCardService;
import com.jindan.jdy.service.risk.RiskResponsibilityListService;
import com.jindan.jdy.service.user.JdyUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@RequestMapping("/riskResponsibilityList")
@Api(tags ="责任清单--安全生产责任")
public class RiskResponsibilityListController {

    @Autowired
    private RiskResponsibilityListService riskResponsibilityListService;

    @Autowired
    private RiskEmergencyDisposalCardService riskEmergencyDisposalCardService;

    @Autowired
    private JdyUserService jdyUserService;

    @ApiOperation(value = "新增安全责任制",notes = "参数：安全责任实体类")
    @PostMapping("/addOrUpdateRiskResponsibility")
    public ResultVo addOrUpdateRiskResponsibility(@ApiParam(name = "riskResponsibilityList", required = true)
                                              @RequestBody  List<RiskResponsibilityList> riskResponsibilityList){

        boolean yesOrNot = false;
        for (RiskResponsibilityList riskResponsibility : riskResponsibilityList) {

            String rlId = riskResponsibility.getRlId();

            if (rlId == null || rlId.equals("")){
                riskResponsibility.setInsertTime(new Date());
                boolean save = riskResponsibilityListService.save(riskResponsibility);
                if(save){
                    yesOrNot = true;
                }
            }else {
                boolean update = riskResponsibilityListService.updateById(riskResponsibility);
                if(update){
                    yesOrNot = true;
                }
            }
        }
        if (yesOrNot){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "分页查询安全责任制",notes = "参数：员工实体类")
    @PostMapping("/selectRiskResponsibilityListByPage")
    public ResultVo selectRiskResponsibilityListByPage(@ApiParam(name = "riskResponsibilityListDto",required =false)
                                                           @RequestBody JdyUserDto jdyUserDto){
        Page<JdyUser> listJdyUser = jdyUserService.selectJdyUserByPage(jdyUserDto);
        List<RiskResponsibilityListDto> pageRiskControlSituationDto = new ArrayList<>();
        DepartmentAndDutiesDto departmentAndDutiesDto = new DepartmentAndDutiesDto();
        for (JdyUser jdyUser : listJdyUser.getRecords()) {
            String userId = jdyUser.getUserId();
            departmentAndDutiesDto.setJdyUserId(userId);
            List<RiskResponsibilityList> riskResponsibilityLists = riskResponsibilityListService.selectRiskResponsibilityList(departmentAndDutiesDto);
            RiskEmergencyDisposalCard riskEmergencyDisposalCard = riskEmergencyDisposalCardService.selectRiskEmergencyDisposalCard(departmentAndDutiesDto);
            RiskResponsibilityListDto riskResponsibilityListDto = new RiskResponsibilityListDto();
            riskResponsibilityListDto.setJdyUser(jdyUser);
            riskResponsibilityListDto.setRiskResponsibilityLists(riskResponsibilityLists);
            riskResponsibilityListDto.setRiskEmergencyDisposalCard(riskEmergencyDisposalCard);
            pageRiskControlSituationDto.add(riskResponsibilityListDto);
        }
        log.info("pageRiskControlSituationDto.size()的值为："+pageRiskControlSituationDto.size());
        return ResultVo.success(pageRiskControlSituationDto);
    }

    @ApiOperation(value = "查询一个人的所有安全责任制",notes = "参数：安全责任制实体类")
    @PostMapping("/selectRiskResponsibilityList")
    public ResultVo selectRiskResponsibilityList(@ApiParam(name = "departmentAndDutiesDto",required =false)
                                  @RequestBody DepartmentAndDutiesDto departmentAndDutiesDto){

        log.info("departmentAndDutiesDto.getJdyUserId()的值为："+departmentAndDutiesDto.getJdyUserId());
        List<RiskResponsibilityList> riskResponsibilityLists = riskResponsibilityListService.selectRiskResponsibilityList(departmentAndDutiesDto);
        return ResultVo.success(riskResponsibilityLists);
    }

    @ApiOperation(value = "删除安全责任制",notes = "参数：id")
    @PostMapping("/deleteRiskResponsibility/{id}")
    public ResultVo deleteRiskResponsibility(@ApiParam(name = "id", value = "权限ID", required = true)
                                                 @PathVariable String  id){

        boolean b = riskResponsibilityListService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }
}
