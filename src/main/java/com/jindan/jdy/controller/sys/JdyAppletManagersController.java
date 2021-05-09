package com.jindan.jdy.controller.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.common.pojo.JdyAppletManagers;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.jindan.jdy.service.sys.JdyAppletManagersService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.sys.JdyClassroomService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 设备维修申报API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "小程序管理人员")
@RestController
@RequestMapping("/jdyAppletManagers")
public class JdyAppletManagersController{

    @Autowired
    JdyAppletManagersService jdyAppletManagersService;

    @ApiOperation(value = "查询小程序管理", notes = "参数:查询小程序信息")
    @PostMapping("/seletejdyClassroom")
    public ResultVo seletejdyClassroom(@ApiParam(value = "jdyClassroom", required = false)
                                       @RequestBody JdyAppletManagers jdyClassroom){
        List<JdyAppletManagers> list = jdyAppletManagersService.seletedslist(jdyClassroom);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新小程序管理信息")
    @PostMapping("/updatejdyClassroom")
    public ResultVo updatejdyClassroom(@ApiParam(value = "jdyClassroom", required = true)
                                       @RequestBody JdyAppletManagers jdyClassroom){
        boolean b = jdyAppletManagersService.updateById(jdyClassroom);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增小程序管理信息")
    @PostMapping("/addjdyClassroom")
    public ResultVo addjdyClassroom( @ApiParam(name = "jdyClassroom", required = true)
                                     @RequestBody JdyAppletManagers jdyClassroom){
            boolean save = jdyAppletManagersService.save(jdyClassroom);
            if(save){
                return ResultVo.success();
            }
        return ResultVo.failed();
    }

    @ApiOperation("删除小程序管理人员信息")
    @DeleteMapping("/deletejdyClassroom/{japid}")
    public ResultVo deletejdyClassroom(@ApiParam(value = "japid", name = "japid", required = true) @PathVariable String  japid){
        boolean b = jdyAppletManagersService.removeById(japid);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}