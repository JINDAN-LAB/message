package com.jindan.jdy.controller.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import com.jindan.jdy.common.pojo.AssayFacilityRecord;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.assay.AssayEntrustBillsService;
import com.jindan.jdy.service.assay.AssayFacilityRecordService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
/**
*
* <p>说明： 设备使用记录API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月20日
*
*/
@Api(tags = "检测设备使用记录")
@RestController
@RequestMapping("/assayFacilityRecord")
public class AssayFacilityRecordController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AssayFacilityRecordService assayEntrustBillsService;

    @ApiOperation(value = "检测委托账单查询", notes = "参数:检测委托账单查询")
    @PostMapping("/seleteAssayFacilityRecord")
    public ResultVo seleteAssayFacilityRecord(@ApiParam(name = "departmentSuggestDto", required = false)
                                     @RequestBody AssayFacilityRecordDto departmentSuggestDto ){
        if(departmentSuggestDto  == null){
            if(redisUtil.get("departmentSuggestDto") == null){
                Page<AssayFacilityRecord> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
                redisUtil.set("departmentSuggestDto",list);
                return  ResultVo.success(list);
            }else{

                return  ResultVo.success(redisUtil.get("departmentSuggestDto"));
            }
        }else{
            Page<AssayFacilityRecord> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新检测委托账单")
    @PostMapping("updateAssayEntrustBills")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody AssayFacilityRecord userPermission){
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
                                           @RequestBody AssayFacilityRecord userPermission){
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