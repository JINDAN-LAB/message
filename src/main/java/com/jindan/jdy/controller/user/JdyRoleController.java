package com.jindan.jdy.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyDepartments;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.service.user.JdyRoleService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.JdyUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@Api(tags = "角色管理")
@RestController
@RequestMapping("/jdyRole")
public class JdyRoleController{

    @Autowired
    JdyRoleService jdyRoleService;


    @ApiOperation(value = "角色管理", notes = "参数:角色信息")
    @PostMapping("/seletejdyRole")
    public ResultVo seleteDepartment(@ApiParam(value = "jdyRole", required = false)
                                     @RequestBody JdyRole jdyRole){
        List<JdyRole> list = jdyRoleService.seletelist(jdyRole);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新角色信息")
    @PostMapping("/updatejdyRole")
    public ResultVo updatefacility(@ApiParam(value = "jdyRole", required = true)
                                   @RequestBody JdyRole jdyRole){
        boolean b = jdyRoleService.updateById(jdyRole);
        if(b){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增角色信息")
    @PostMapping("/addjdyRole")
    public ResultVo addjdyRole( @ApiParam(name = "jdyRole", required = true)
                               @RequestBody JdyRole jdyRole){
        boolean save = jdyRoleService.save(jdyRole);
        if(save){
            return ResultVo.success(jdyRole);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增角色返回解决Id")
    @PostMapping("/addjdyRoleID")
    public ResultVo addjdyRoleID( @ApiParam(name = "jdyRole", required = true)
                                @RequestBody JdyRole jdyRole){
        JdyRole id = jdyRoleService.saveJdyRole(jdyRole);
        if(id != null && ! " ".equals(id)){
            return ResultVo.success(id);
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除角色信息")
    @DeleteMapping("/deletejdyRole/{id}")
    public ResultVo deletefacility(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = jdyRoleService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}