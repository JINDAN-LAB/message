package com.jindan.jdy.controller.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskGridControlDto;
import com.jindan.jdy.common.dto.RiskNameAndPersonnelDto;
import com.jindan.jdy.common.dto.RiskPersonnelHierarchyGridDto;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.jindan.jdy.common.pojo.RiskPoint;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskControlSituationService;
import com.jindan.jdy.service.risk.RiskPointService;
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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/riskControlSituation")
@Api(tags ="风险分级管控")
public class RiskPersonnelHierarchyGridController {

    @Autowired
    private JdyUserService jdyUserService;

    @Autowired
    private RiskControlSituationService riskControlSituationService;

    @Autowired
    private RiskPointService riskPointService;

    @ApiOperation(value = "风险分级管控",notes = "参数：风险名称和巡检人实体类")
    @PostMapping("/selectRiskHierarchyGridByPage")
    public ResultVo selectRiskHierarchyGridByPage(@ApiParam(name = "riskControlSituationDto", required = false)
                                                     @RequestBody RiskNameAndPersonnelDto riskNameAndPersonnelDto){

        Page<RiskPoint> listRiskPoint = riskPointService.selectListWapperByPage(riskNameAndPersonnelDto);
        List<RiskPersonnelHierarchyGridDto> pageRiskPersonnelHierarchyGridDto = new ArrayList<>();

        int i = 0;
        /*RiskPoint riskPoint = new RiskPoint();
        int i = 0;
        while (i<listRiskPoint.getSize()){

            riskPoint = listRiskPoint.getRecords(i);
            String jdyUserName =
        }*/

        for (RiskPoint riskPoint : listRiskPoint.getRecords()) {
            while (i<listRiskPoint.getSize()){

                i++;
            }
            String jdyUserName = riskPoint.getRiskPerson();

            String rid = riskPoint.getRid();
            String riskPerson = riskPoint.getRiskPerson();

            log.info("rid和riskPerson的值分别为："+rid+","+riskPerson);

            //riskNameAndPersonnelDto.setRiskNameId(rid);
            riskNameAndPersonnelDto.setInspectionPersonnel(riskPerson);
            List<RiskControlSituation> riskControlSituationsList = riskControlSituationService.selectListRiskControlSituation(riskNameAndPersonnelDto);

            log.info("riskControlSituationsList的size值为："+ riskControlSituationsList.size());

            JdyUser jdyUser = new JdyUser();
            jdyUser.setUsername(riskPerson);
            List<JdyUser>  jdyUserList = jdyUserService.seleListUsers(jdyUser);
            String department = null;
            String post = null;
            if (jdyUserList.size()>0){
                department = jdyUserList.get(0).getDepartments();
                post = jdyUserList.get(0).getPower();
            }


            RiskPersonnelHierarchyGridDto riskPersonnelHierarchyGridDto = new RiskPersonnelHierarchyGridDto();
            riskPersonnelHierarchyGridDto.setJdyUserName(riskPoint.getRiskPerson());
            riskPersonnelHierarchyGridDto.setDepartment(department);
            riskPersonnelHierarchyGridDto.setPost(post);
            String positionLevel = null;
            if (riskControlSituationsList.size()>0){
                positionLevel = riskControlSituationsList.get(0).getMainControlLevel();
            }
            riskPersonnelHierarchyGridDto.setPositionLevel(positionLevel);
            if (!post.equals("员工")){
                riskPersonnelHierarchyGridDto.setIsPersonInCharge("是");
            }else {
                riskPersonnelHierarchyGridDto.setIsPersonInCharge("否");
            }

            //riskPersonnelHierarchyGridDto.setRiskControlSituation(riskControlSituationsList);
            pageRiskPersonnelHierarchyGridDto.add(riskPersonnelHierarchyGridDto);
        }
        return ResultVo.success(pageRiskPersonnelHierarchyGridDto);
    }
}
