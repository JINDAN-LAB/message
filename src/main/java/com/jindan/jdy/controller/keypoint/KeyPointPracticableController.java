package com.jindan.jdy.controller.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.dto.KeyPointPracticableDto;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.jindan.jdy.common.pojo.KeyPointEvaluate;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.enumerate.KeyStatus;
import com.jindan.jdy.service.department.DepartmentFacilityService;
import com.jindan.jdy.service.keypoint.KeyPointPracticableService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.keypoint.KeyPointProjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 重点项目API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "重点项目落实信息")
@RestController
@RequestMapping("/keyPointPracticable")
public class KeyPointPracticableController{

    @Autowired
    KeyPointPracticableService keyPointPracticableService;

    @Autowired
    KeyPointProjectService keyPointProjectService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "重点查询", notes = "参数:重点项查询")
    @PostMapping("/seletekeyPointPracticableService")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody KeyPointPracticableDto departmentSuggestDto ){

        Page<KeyPointPracticable> list = keyPointPracticableService.seleteDepartmentFacility(departmentSuggestDto);
        return  ResultVo.success(list);
  }

    @ApiOperation("更新建议信息")
    @PostMapping("updatekeyPointPracticableService")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody KeyPointPracticable userPermission){
        boolean b = keyPointPracticableService.updateById(userPermission);
        if(b){
            redisUtil.get(userPermission.getParentId()+"pra");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增落实信息")
    @PostMapping("addkeyPointPracticableService")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                           @RequestBody  KeyPointPracticable users){
        boolean save = keyPointPracticableService.save(users);
        KeyPointProject keyPointProject =new KeyPointProject();
        keyPointProject.setId(users.getParentId());
        keyPointProject.setStatus(KeyStatus.XUJINDU.getStatus().toString());
        keyPointProjectService.updateById(keyPointProject);
        if(save){
            redisUtil.get(users.getParentId()+"pra");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deletekeyPointPracticableService/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = keyPointPracticableService.removeById(id);
        if(b){
            redisUtil.get(id+"pra");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}