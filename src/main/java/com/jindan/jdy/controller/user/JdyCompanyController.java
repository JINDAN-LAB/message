package com.jindan.jdy.controller.user;

import com.jindan.jdy.common.pojo.JdyCompany;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.JdyCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 资产子设备信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年10月04日
*
*/
@Api(tags = "公司信息管理")
@RestController
@RequestMapping("/jdyCompany")
public class JdyCompanyController{

    @Autowired
    JdyCompanyService jdyCompanyService;

    @ApiOperation(value = "查询公司信息管理", notes = "参数:查询公司信息管理")
    @PostMapping("/seleteDepartment")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyDepartments", required = false)
                                     @RequestBody JdyCompany jdyDepartments){
        List<JdyCompany> list = jdyCompanyService.seletelist(jdyDepartments);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新公司信息管理")
    @PostMapping("updateDepartments")
    public ResultVo updatefacility(@ApiParam(name = "jdyDepartments", required = true)
                                   @RequestBody JdyCompany  jdyDepartments){
        boolean b = jdyCompanyService.updateById(jdyDepartments);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增公司信息管理")
    @PostMapping("addDepartments")
    public ResultVo addsubset( @ApiParam(name = "jdyDepartments", required = true)
                               @RequestBody JdyCompany  jdyDepartments){
        boolean save = jdyCompanyService.save(jdyDepartments);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除公司信息管理")
    @DeleteMapping("deleteDepartments/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "根据部门ID", required = true) @PathVariable String id){
        boolean b = jdyCompanyService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}