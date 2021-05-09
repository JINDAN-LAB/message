package com.jindan.jdy.controller.assay;

import com.jindan.jdy.common.pojo.AssayRawIngredientsFormsResult;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.assay.AssayRawIngredientsFormsResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 报表结果发货报表的结果集API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月20日
*
*/
@Api(tags ="检测中心报表原辅料统计表结果集合")
@RestController
@RequestMapping("/assayRawIngredientsFormsResult")
public class AssayRawIngredientsFormsResultController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AssayRawIngredientsFormsResultService assayEntrustBillsService;

    @ApiOperation(value = "检测委托账单查询", notes = "参数:检测委托账单查询")
    @PostMapping("/seleteAssayRawIngredientsFormsResult")
    public ResultVo seleteAssayRawIngredientsFormsResult(@ApiParam(name = "departmentSuggestDto", required = false)
                                                   @RequestBody AssayRawIngredientsFormsResult departmentSuggestDto ){
        if(departmentSuggestDto  == null){
            if(redisUtil.get("seleteAssayRawIngredientsFormsResult") == null){
                List<AssayRawIngredientsFormsResult> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
                redisUtil.set("seleteAssayRawIngredientsFormsResult",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seleteAssayRawIngredientsFormsResult"));
            }
        }else{
            List<AssayRawIngredientsFormsResult> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新检测委托账单")
    @PostMapping("updateAssayEntrustBills")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody  AssayRawIngredientsFormsResult userPermission){
        boolean b = assayEntrustBillsService.updateById(userPermission);
        if(b){
            redisUtil.del("seleteAssayRawIngredientsFormsResult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增检测委托账单")
    @PostMapping("addDepartmentFacility")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody AssayRawIngredientsFormsResult userPermission){
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