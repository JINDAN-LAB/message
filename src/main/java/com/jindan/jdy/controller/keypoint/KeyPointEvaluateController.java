package com.jindan.jdy.controller.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointPracticableDto;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import com.jindan.jdy.common.pojo.KeyPointEvaluate;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.enumerate.KeyStatus;
import com.jindan.jdy.service.keypoint.KeyPointEvaluateService;
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
* <p>说明： 重点工作评价API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Api(tags = "重点工作评价")
@RestController
@RequestMapping("/keyPointEvaluate")
public class KeyPointEvaluateController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    KeyPointEvaluateService keyPointEvaluateService;

    @ApiOperation(value = "重点评价查询", notes = "参数:重点项查询")
    @PostMapping("/seletekeyPointPracticableService")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody KeyPointEvaluate departmentSuggestDto){

          List<KeyPointEvaluate> list = keyPointEvaluateService.seleteDepartmentFacility(departmentSuggestDto);
          return  ResultVo.success(list);

    }

    @ApiOperation("更新评价信息")
    @PostMapping("updatekeyPointPracticableService")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody KeyPointEvaluate userPermission){
        boolean b = keyPointEvaluateService.updateById(userPermission);
        if(b){
            redisUtil.get(userPermission.getProintId()+"cable");
            redisUtil.del(userPermission.getProintId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增评价信息")
    @PostMapping("addKeyPointEvaluate")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  KeyPointEvaluate users){
        boolean save = keyPointEvaluateService.save(users);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除评价信息")
    @DeleteMapping("deletekeyPointPracticableService/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = keyPointEvaluateService.removeById(id);
        if(b){
            redisUtil.get(id+"cable");
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}