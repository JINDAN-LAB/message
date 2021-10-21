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
@Api(tags ="人员分级管控")
public class RiskPersonnelHierarchyGridController {

    @Autowired
    private JdyUserService jdyUserService;

    @Autowired
    private RiskControlSituationService riskControlSituationService;

    @Autowired
    private RiskPointService riskPointService;

    @ApiOperation(value = "人员分级管控",notes = "参数：风险名称和巡检人实体类")
    @PostMapping("/selectRiskHierarchyGridByPage")
    public ResultVo selectRiskHierarchyGridByPage(@ApiParam(name = "riskControlSituationDto", required = false)
                                                     @RequestBody RiskNameAndPersonnelDto riskNameAndPersonnelDto){

        RiskPoint riskPointContent = new RiskPoint();
        riskPointContent.setRiskName(riskNameAndPersonnelDto.getRiskName());
        riskPointContent.setRiskName(riskNameAndPersonnelDto.getInspectionPersonnel());
        List<RiskPoint> listRiskPoint = riskPointService.seleListWapper(riskPointContent);
        List<RiskPersonnelHierarchyGridDto> pageRiskPersonnelHierarchyGridDto = new ArrayList<>();

        /**
         * 设想一
         */
        /*for (int i = 0 ;i < listRiskPoint.size();i++){
            //int signum = 0;
            List<Integer> signum = new ArrayList<>();
            RiskPoint riskPoint = listRiskPoint.get(i);
            String name = riskPoint.getRiskName();
            for (int j = 0;j < listRiskPoint.size();j++){
                if (name.equals(listRiskPoint.get(j).getRiskName())){
                    signum.add(j);
                }
            }

            if (signum.get(i) != i){

                String riskName = riskPoint.getRiskName();

                String rid = riskPoint.getRid();
                String riskPerson = riskPoint.getRiskPerson();

                log.info("rid和riskPerson的值分别为："+rid+","+riskPerson);

                riskNameAndPersonnelDto.setRiskNameId(rid);
                //riskNameAndPersonnelDto.setInspectionPersonnel(riskPerson);
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
                riskPersonnelHierarchyGridDto.setJdyUserName(riskPerson);
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

                List<RiskGridControlDto> riskGridControlDtoList = new ArrayList<>();
                RiskGridControlDto riskGridControlDto = new RiskGridControlDto();
                riskGridControlDto.setRiskName(riskName);

                String dutyCycle = null;
                String taskType = null;
                if (riskControlSituationsList.size()>0){
                    dutyCycle = riskControlSituationsList.get(0).getDutyCycle();
                    taskType = riskControlSituationsList.get(0).getTaskType();
                }

                riskGridControlDto.setDutyCycle(dutyCycle);
                riskGridControlDto.setTaskType(taskType);
                riskGridControlDtoList.add(riskGridControlDto);

                riskPersonnelHierarchyGridDto.setRiskGridControlDto(riskGridControlDtoList);
                pageRiskPersonnelHierarchyGridDto.add(riskPersonnelHierarchyGridDto);

            }
        }*/

        /**
         * 设想二
         */
        for (int i = 0 ;i < listRiskPoint.size();i++){
            //int signum = 0;
            List<Integer> signumlist = new ArrayList<>();
            RiskPoint riskPoint = listRiskPoint.get(i);
            String riskName = riskPoint.getRiskName();
            for (int j = 0;j < listRiskPoint.size();j++){
                if (riskName.equals(listRiskPoint.get(j).getRiskName())){
                    signumlist.add(j);
                }

                //

                String rid = riskPoint.getRid();
                String riskPerson = riskPoint.getRiskPerson();

                log.info("rid和riskPerson的值分别为："+rid+","+riskPerson);

                riskNameAndPersonnelDto.setRiskNameId(rid);
                //riskNameAndPersonnelDto.setInspectionPersonnel(riskPerson);
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
                riskPersonnelHierarchyGridDto.setJdyUserName(riskPerson);
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

                //

                int signum = signumlist.get(j);

                List<RiskGridControlDto> riskGridControlDtoList = new ArrayList<>();
                if (riskName.equals(listRiskPoint.get(signum).getRiskName())){


                    RiskGridControlDto riskGridControlDto = new RiskGridControlDto();
                    riskGridControlDto.setRiskName(riskName);

                    String dutyCycle = null;
                    String taskType = null;
                    if (riskControlSituationsList.size()>0){
                        dutyCycle = riskControlSituationsList.get(0).getDutyCycle();
                        taskType = riskControlSituationsList.get(0).getTaskType();
                    }

                    riskGridControlDto.setDutyCycle(dutyCycle);
                    riskGridControlDto.setTaskType(taskType);
                    riskGridControlDtoList.add(riskGridControlDto);

                    riskPersonnelHierarchyGridDto.setRiskGridControlDto(riskGridControlDtoList);
                    pageRiskPersonnelHierarchyGridDto.add(riskPersonnelHierarchyGridDto);
                }

                //
            }
            /*List<RiskGridControlDto> riskGridControlDtoList = new ArrayList<>();
            if (i == signum.get(i)){


                RiskGridControlDto riskGridControlDto = new RiskGridControlDto();
                riskGridControlDto.setRiskName(riskName);

                String dutyCycle = null;
                String taskType = null;
                if (riskControlSituationsList.size()>0){
                    dutyCycle = riskControlSituationsList.get(0).getDutyCycle();
                    taskType = riskControlSituationsList.get(0).getTaskType();
                }

                riskGridControlDto.setDutyCycle(dutyCycle);
                riskGridControlDto.setTaskType(taskType);
                riskGridControlDtoList.add(riskGridControlDto);

            }*/

        }


        /**
         * 设想三
         *//*
        for (int i = 0 ;i < listRiskPoint.size();i++){
            //int signum = 0;
            List<Integer> signum = new ArrayList<>();

            for (int j = 0;j < listRiskPoint.size();j++){
                RiskPoint riskPointj = listRiskPoint.get(j);
                String name = riskPointj.getRiskName();
                if (name.equals(listRiskPoint.get(i).getRiskName())){
                    signum.add(j);
                }
            }

        }

        *//**
         * 设想四
         *//*
        for (int i = 0 ;i < listRiskPoint.size();i++){
            //int signum = 0;
            List<Integer> signum = new ArrayList<>();
            RiskPoint riskPointi = listRiskPoint.get(i);
            String name = riskPointi.getRiskName();
            for (int j = 0;j <= i;j++){
                if (name.equals(listRiskPoint.get(j).getRiskName())){
                    signum.add(j);
                }
            }

            for (int j = i+1;j < listRiskPoint.size();j++){
                if (riskPointi.equals(listRiskPoint.get(j))){
                    signum.add(j);
                }
            }

        }*/

        /*for (RiskPoint riskPoint : listRiskPoint.getRecords()) {

            String riskName = riskPoint.getRiskName();

            String rid = riskPoint.getRid();
            String riskPerson = riskPoint.getRiskPerson();

            log.info("rid和riskPerson的值分别为："+rid+","+riskPerson);

            riskNameAndPersonnelDto.setRiskNameId(rid);
            //riskNameAndPersonnelDto.setInspectionPersonnel(riskPerson);
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
            riskPersonnelHierarchyGridDto.setJdyUserName(riskPerson);
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

            List<RiskGridControlDto> riskGridControlDtoList = new ArrayList<>();
            RiskGridControlDto riskGridControlDto = new RiskGridControlDto();
            riskGridControlDto.setRiskName(riskName);

            String dutyCycle = null;
            String taskType = null;
            if (riskControlSituationsList.size()>0){
                dutyCycle = riskControlSituationsList.get(0).getDutyCycle();
                taskType = riskControlSituationsList.get(0).getTaskType();
            }

            riskGridControlDto.setDutyCycle(dutyCycle);
            riskGridControlDto.setTaskType(taskType);
            riskGridControlDtoList.add(riskGridControlDto);

            riskPersonnelHierarchyGridDto.setRiskGridControlDto(riskGridControlDtoList);
            pageRiskPersonnelHierarchyGridDto.add(riskPersonnelHierarchyGridDto);
        }*/
        return ResultVo.success(pageRiskPersonnelHierarchyGridDto);
    }
}
