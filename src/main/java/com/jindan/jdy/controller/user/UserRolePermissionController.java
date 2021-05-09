package com.jindan.jdy.controller.user;

import com.jindan.jdy.common.dto.JdyRoleDto;
import com.jindan.jdy.common.dto.UserPermissionDto;
import com.jindan.jdy.common.pojo.UserRolePermission;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.UserRolePermissionService;
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
@Api(tags = "角色关联权限")
@RestController
@RequestMapping("/userRolePermission")
public class UserRolePermissionController{

    @Autowired
    UserRolePermissionService userRolePermissionService;

    @ApiOperation(value = "用户角色权限管理", notes = "参数:权限信息")
    @PostMapping("/seleteUserRolePermission")
    public ResultVo seleteDepartment(@ApiParam(name = "userRolePermission", required = false)
                                     @RequestBody UserRolePermission userRolePermission ){
         List<UserPermissionDto> list = userRolePermissionService.seleteUserRolelist(userRolePermission);
        return  ResultVo.success(list) ;
    }

    @ApiOperation("更新用户角色权限信息")
    @PostMapping("updateUserRolePermission")
    public ResultVo updatefacility(@ApiParam(name = "userRolePermission", required = true)
                                   @RequestBody UserRolePermission userRolePermission){
        boolean b = userRolePermissionService.updateById(userRolePermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增用户角色权限信息")
    @PostMapping("addUserRolePermission")
    public ResultVo addsubset( @ApiParam(name = "userRolePermission", required = true)
                               @RequestBody  UserRolePermission userRolePermission){
        boolean save = userRolePermissionService.save(userRolePermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("ceshi")
    @PostMapping("addRolePermission")
    public ResultVo addRolePermission(@ApiParam(name = "jdyRoleDto", required = true)
                               @RequestBody JdyRoleDto jdyRoleDto){
        int i=0;
        for (  i = 0; i <jdyRoleDto.getUserPermissions().size() ; i++) {
            UserRolePermission jdyRole = new UserRolePermission();
            jdyRole.setRoleId(jdyRoleDto.getRoleId());
            jdyRole.setPermissionId(jdyRoleDto.getUserPermissions().get(i).getPermissionId());
            boolean save = userRolePermissionService.save(jdyRole);
        }
        if(i == jdyRoleDto.getUserPermissions().size()){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除用户角色权限信息")
    @DeleteMapping("deleteUserRolePermission")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "用户角色权限ID", required = true) @RequestBody List<String>  id){
        int i =0;
        for (i = 0; i <id.size() ; i++){
            boolean b = userRolePermissionService.removeById(id.get(i));
            if(b){
              i++;
            }
        }
        if(i == id.size()){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("单个删除角色对应权限")
    @PostMapping("deleteUserRolePermissionOne/{id}")
    public ResultVo deleteUserRolePermissionOne(@ApiParam(name = "id", value = "用户角色权限ID", required = true) @PathVariable String id){

        boolean b = userRolePermissionService.removeById(id);
        if(b){
           return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("集合删除角色对应ID")
    @PostMapping("deleteUserRolePermission")
    public ResultVo deleteUserRolePermissione(@ApiParam(name = "userRolePermsion", required = true)
                                                    @RequestBody List<UserRolePermission> userRolePermission){

        int j = 0;
        for (int i = 0; i < userRolePermission.size(); i++) {
            boolean b = userRolePermissionService.deleteRolePermission(userRolePermission.get(i));
            if(b){
                j++;
            }
        }
        if(j == userRolePermission.size() && j > 0){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}