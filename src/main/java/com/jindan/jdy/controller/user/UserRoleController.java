package com.jindan.jdy.controller.user;

import com.jindan.jdy.common.dto.UserRoleDto;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.UserRole;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "用户关联角色")
@RestController
@RequestMapping("/userRole")
public class UserRoleController{

    @Autowired
    UserRoleService userRoleService;


    @ApiOperation(value = "用户权限管理", notes = "参数:权限信息")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment( @ApiParam(name = "userPermission", required = false)
                                      @RequestBody UserRole userRole ){
        List<UserRoleDto> list = userRoleService.seleteAllUserRolelist(userRole);

        return  ResultVo.success(list) ;
    }

    @ApiOperation("更新权限信息")
    @PostMapping("updateUserRole")
    public ResultVo updatefacility(@ApiParam(name = "userRole", required = true)
                                   @RequestBody UserRole userRole){
        boolean b = userRoleService.updateById(userRole);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增权限信息")
    @PostMapping("addUserRole")
    public ResultVo addsubset( @ApiParam(name = "userPermission", required = true)
                               @RequestBody  UserRole userPermission){
        boolean save = userRoleService.save(userPermission);
        if(save){
            return ResultVo.success(userPermission);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除权限信息")
    @DeleteMapping("deleteUserRole/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "用户权限ID", required = true) @PathVariable String  id){
        boolean b = userRoleService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("根据角色查权限")
    @PostMapping("seleteRolePermission")
    public ResultVo seleteRolePermission(@ApiParam(name = "userRole", required = true)
                                   @RequestBody JdyRole userRole){
       List<UserPermission> list = userRoleService.seleteRolePermission(userRole);
        return ResultVo.success(list);
    }


}