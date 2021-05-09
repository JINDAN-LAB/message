package com.jindan.jdy.controller.assay;

import com.jindan.jdy.common.pojo.AssayRawIngredientsForms;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.assay.AssayRawIngredientsFormsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 报表发货报表的结果集API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月20日
*
*/
@Api(tags = "检测中心报表原辅料统计表")
@RestController
@RequestMapping("/assayRawIngredientsForms")
public class AssayRawIngredientsFormsController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    AssayRawIngredientsFormsService assayEntrustBillsService;

    @ApiOperation(value = "检测委托账单查询", notes = "参数:检测委托账单查询")
    @PostMapping("/seleteAssayRawIngredientsForms")
    public ResultVo seleteAssayRawIngredientsForms(@ApiParam(name = "departmentSuggestDto", required = false)
                                              @RequestBody AssayRawIngredientsForms departmentSuggestDto ){
        if(departmentSuggestDto  == null){
            if(redisUtil.get("seleteAssayPutReportFormsResult") == null){
                List<AssayRawIngredientsForms> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
                redisUtil.set("seleteAssayPutReportFormsResult",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seleteAssayPutReportFormsResult"));
            }
        }else{
            List<AssayRawIngredientsForms> list = assayEntrustBillsService.seleteDepartmentSubfacility(departmentSuggestDto);
            return  ResultVo.success(list);
        }
    }

    @ApiOperation("更新检测委托账单")
    @PostMapping("updateAssayEntrustBills")
    public ResultVo updateDepartmentFacility(@ApiParam(name = "userPermission", required = true)
                                             @RequestBody  AssayRawIngredientsForms userPermission){
        boolean b = assayEntrustBillsService.updateById(userPermission);
        if(b){
            redisUtil.del("seleteAssayPutReportFormsResult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增检测委托账单")
    @PostMapping("addDepartmentFacility")
    public ResultVo addDepartmentFacility( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody AssayRawIngredientsForms userPermission){
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