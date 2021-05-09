package com.jindan.jdy.controller.user;

import com.jindan.jdy.common.dto.JdyCompanyDto;
import com.jindan.jdy.common.pojo.JdyDepartments;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.user.JdyDepartmentsService;
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
@Api(tags = "部门管理")
@RestController
@RequestMapping("/jdyDepartments")
public class JdyDepartmentsController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JdyDepartmentsService jdyDepartmentsService;

    @ApiOperation(value = "部门管理", notes = "参数:部门信息")
    @PostMapping("/seleteDepartment")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyDepartments", required = false)
                                     @RequestBody JdyDepartments  jdyDepartments){
        if(jdyDepartments.getId() != null){
            if(redisUtil.get(jdyDepartments.getId()) == null){
                List<JdyDepartments> list = jdyDepartmentsService.seletelist(jdyDepartments);
                redisUtil.set(jdyDepartments.getId(),list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get(jdyDepartments.getId()));
            }
        }else{
            List<JdyDepartments> list = jdyDepartmentsService.seletelist(jdyDepartments);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation(value = "部门管理", notes = "参数:部门信息")
    @PostMapping("/seleteCompanyDepartment")
    public ResultVo seleteCompanyDepartment(@ApiParam(name = "jdyDepartments", required = false)
                                     @RequestBody JdyCompanyDto jdyDepartments){
            List<JdyCompanyDto> list = jdyDepartmentsService.seleteJdyCompanylist(jdyDepartments);
            return  ResultVo.success(list);
    }


    @ApiOperation("更新部门信息")
    @PostMapping("updateDepartments")
    public ResultVo updatefacility(@ApiParam(name = "jdyDepartments", required = true)
                                   @RequestBody JdyDepartments  jdyDepartments){
        boolean b = jdyDepartmentsService.updateById(jdyDepartments);
        if(b){
            redisUtil.del(jdyDepartments.getId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增部门信息")
    @PostMapping("addDepartments")
    public ResultVo addsubset( @ApiParam(name = "jdyDepartments", required = true)
                        @RequestBody JdyDepartments  jdyDepartments){
        boolean save = jdyDepartmentsService.save(jdyDepartments);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除部门信息")
    @DeleteMapping("deleteDepartments/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "根据部门ID", required = true) @PathVariable String  id){
        boolean b = jdyDepartmentsService.removeById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}