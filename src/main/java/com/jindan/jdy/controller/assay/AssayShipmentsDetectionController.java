package com.jindan.jdy.controller.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayShipmentsDetectionDto;
import com.jindan.jdy.common.pojo.AssayShipmentsDetection;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.assay.AssayShipmentsDetectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
*
* <p>说明： 产品发货信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月20日
*
*/
@Api(tags = "检测产品发货信息")
@RestController
@RequestMapping("/assayShipmentsDetection")
public class AssayShipmentsDetectionController{


    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AssayShipmentsDetectionService assayEntrustBillsService;

    @ApiOperation(value = "检测委托账单查询", notes = "参数:检测委托账单查询")
    @PostMapping("/seleteAssayEntrustBills")
    public ResultVo seleteDepartment(@ApiParam(name = "departmentSuggestDto", required = false)
                                     @RequestBody AssayShipmentsDetectionDto departmentSuggestDto ){
        if(departmentSuggestDto  == null){
            if(redisUtil.get("seleteAssayEntrustBills") == null){
                Page<AssayShipmentsDetection> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
                redisUtil.set("seleteAssayEntrustBills",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seleteAssayEntrustBills"));
            }
        }else{
            Page<AssayShipmentsDetection> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新检测委托账单")
    @PostMapping("updateAssayEntrustBills")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody AssayShipmentsDetection userPermission){
        boolean b = assayEntrustBillsService.updateById(userPermission);
        if(b){
            redisUtil.del("seleteAssayEntrustBills");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增检测委托账单")
    @PostMapping("addDepartmentFacility")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody AssayShipmentsDetection userPermission){
        boolean save = assayEntrustBillsService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除检测委托账单")
    @DeleteMapping("deleteDepartmentFacility/{id}")
    public ResultVo deleteDepartmentFacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = assayEntrustBillsService.removeById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}