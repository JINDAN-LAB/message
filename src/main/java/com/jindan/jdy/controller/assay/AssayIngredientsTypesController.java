package com.jindan.jdy.controller.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.dto.AssayIngredientsTypesDto;
import com.jindan.jdy.common.pojo.AssayFacilityRecord;
import com.jindan.jdy.common.pojo.AssayIngredientsTypes;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.assay.AssayFacilityRecordService;
import com.jindan.jdy.service.assay.AssayIngredientsTypesService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
/**
*
* <p>说明： 检测-分类表API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@Api(tags = "检测-分类表")
@RestController
@RequestMapping("/assayIngredientsTypes")
public class AssayIngredientsTypesController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AssayIngredientsTypesService assayEntrustBillsService;

    @ApiOperation(value = "分类表查询", notes = "参数:分类表查询")
    @PostMapping("/seleteAssayIngredientsTypes")
    public ResultVo seleteAssayFacilityRecord(@ApiParam(name = "departmentSuggestDto", required = false)
                                              @RequestBody AssayIngredientsTypesDto departmentSuggestDto ){
        if(departmentSuggestDto  == null){
            if(redisUtil.get("AssayIngredientsTypes") == null){
                Page<AssayIngredientsTypes> list = assayEntrustBillsService.seleteAssayIngredientsTypes(departmentSuggestDto);
                redisUtil.set("AssayIngredientsTypes",list);
                return  ResultVo.success(list);
            }else{

                return  ResultVo.success(redisUtil.get("AssayIngredientsTypes"));
            }
        }else{
            Page<AssayIngredientsTypes> list = assayEntrustBillsService.seleteAssayIngredientsTypes(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新分类表")
    @PostMapping("updateAssayIngredientsTypes")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody AssayIngredientsTypes userPermission){
        boolean b = assayEntrustBillsService.updateDetailsById(userPermission);
        if(b){
            redisUtil.del("seleteAssayEntrustBills");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增分类表")
    @PostMapping("addAssayIngredientsTypes")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody AssayIngredientsTypes userPermission){
        boolean save = assayEntrustBillsService.saveInsert(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除分类表")
    @DeleteMapping("deleteAssayIngredientsTypes/{id}")
    public ResultVo deleteDepartmentFacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){

        boolean b = assayEntrustBillsService.removeDetailsById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}