package com.jindan.jdy.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.service.user.JdyRoleService;
import com.jindan.jdy.service.user.UserPermissionService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags= "权限管理")
@RestController
@RequestMapping("/userPermission")
public class UserPermissionController{

    @Autowired
    UserPermissionService userPermissionService;

    @ApiOperation(value = "权限管理", notes = "参数:权限信息")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment( @ApiParam(name = "userPermission", required = false)
                                      @RequestBody UserPermission userPermission ){
        List<UserPermission> list = userPermissionService.seletelist(userPermission);
        return  ResultVo.success(list) ;
    }

    @ApiOperation("更新权限信息")
    @PostMapping("updateUserPermission")
    public ResultVo updatefacility(@ApiParam(name = "userPermission", required = true)
                                   @RequestBody UserPermission userPermission){
        boolean b = userPermissionService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增权限信息")
    @PostMapping("addUserPermission")
    public ResultVo addsubset( @ApiParam(name = "userPermission", required = true)
                               @RequestBody  UserPermission userPermission){
        boolean save = userPermissionService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除权限信息")
    @DeleteMapping("deleteUserPermission/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = userPermissionService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}