package com.jindan.jdy.controller.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointScheduleDto;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.common.pojo.KeyPointSchedule;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.enumerate.KeyStatus;
import com.jindan.jdy.service.keypoint.KeyPointProjectService;
import com.jindan.jdy.service.keypoint.KeyPointScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags ="重点项目进度完成情况")
@RestController
@RequestMapping("/keyPointSchedule")
public class KeyPointScheduleController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    KeyPointScheduleService keyPointScheduleService;

    @Autowired
    KeyPointProjectService keyPointProjectService;

    @ApiOperation(value = "重点项查询", notes = "参数:重点项查询")
    @PostMapping("/seleteKeyPointSchedule")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody KeyPointScheduleDto departmentSuggestDto ){

           Page<KeyPointSchedule> list = keyPointScheduleService.seleteDepartmentFacility(departmentSuggestDto);
           return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateKeyPointSchedule")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody KeyPointSchedule userPermission){
        boolean b = keyPointScheduleService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addKeyPointSchedule")
    public ResultVo addKeyPointSchedule( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody  KeyPointSchedule userPermission){
        boolean save = keyPointScheduleService.save(userPermission);
        KeyPointProject keyPointProject =new KeyPointProject();
        keyPointProject.setId(userPermission.getParentId());
        keyPointProject.setStatus(KeyStatus.XULUOSHI.getStatus().toString());
        keyPointProjectService.updateById(keyPointProject);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteKeyPointSchedule/{id}")
    public ResultVo deleteKeyPointSchedule(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = keyPointScheduleService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}