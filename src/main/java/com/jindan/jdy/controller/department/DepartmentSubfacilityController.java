package com.jindan.jdy.controller.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.dto.DepartmentSubfacilityDto;
import com.jindan.jdy.common.dto.DepartmentSuggestDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.DepartmentFacilityService;
import com.jindan.jdy.service.department.DepartmentSubfacilityService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.department.DepartmentSuggestService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "子设备信息")
@RestController
@RequestMapping("/departmentSubfacility")
public class DepartmentSubfacilityController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DepartmentSubfacilityService departmentSubfacilityService;

    @ApiOperation(value = "部门设备查询", notes = "参数:部门设备查询条件")
    @PostMapping("/seleteUserPermission")
    public ResultVo seleteDepartment(@ApiParam(name = "departmentSuggestDto", required = false)
                                     @RequestBody DepartmentSubfacilityDto departmentSuggestDto ){
        if(departmentSuggestDto.getDepartFacilityId() != null){
            if(redisUtil.get(departmentSuggestDto.getDepartFacilityId()) == null){
                Page<DepartmentSubfacility> list = departmentSubfacilityService.seleteDepartmentSubfacility(departmentSuggestDto);
                redisUtil.set(departmentSuggestDto.getDepartFacilityId(),list);
                return  ResultVo.success(list);
             }else{
                return  ResultVo.success(redisUtil.get(departmentSuggestDto.getDepartFacilityId()));
           }
         }else{
            Page<DepartmentSubfacility> list = departmentSubfacilityService.seleteDepartmentSubfacility(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateDepartmentFacility")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody DepartmentSubfacility userPermission){
        boolean b = departmentSubfacilityService.updateById(userPermission);
        if(b){
            redisUtil.del(userPermission.getDepartFacilityId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增建议信息")
    @PostMapping("addDepartmentFacility")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody  DepartmentSubfacility userPermission){
        boolean save = departmentSubfacilityService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除建议信息")
    @DeleteMapping("deleteDepartmentFacility/{id}")
    public ResultVo deleteDepartmentFacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = departmentSubfacilityService.removeById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}