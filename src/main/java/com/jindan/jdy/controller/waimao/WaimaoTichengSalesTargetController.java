package com.jindan.jdy.controller.waimao;


import com.jindan.jdy.common.pojo.WaimaoTichengSalesTarget;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.waimao.WaimaoTichengSalesTargetService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  外贸提成销售目标
 * </p>
 *
 * @author liangfang
 * @since 2021-08-15
 */
@RestController
@RequestMapping("/waimaoTichengSalesTarget")
public class WaimaoTichengSalesTargetController {

    @Autowired
    WaimaoTichengSalesTargetService waimaoTichengSalesTargetService;

    @ApiOperation(value = "查询外贸提成销售目标", notes = "参数:业务员名称")
    @PostMapping("/list")
    public ResultVo list(String name){
        List<WaimaoTichengSalesTarget> list = waimaoTichengSalesTargetService.list(name);
        return ResultVo.success(list);
    }

    @ApiOperation(value = "新增外贸提成销售目标", notes = "参数:销售目标对象")
    @PostMapping("/add")
    public ResultVo addSaleTarget(@RequestBody WaimaoTichengSalesTarget waimaoTichengSalesTarget){
        waimaoTichengSalesTarget.setInsertTime(new Date());
        boolean flag = waimaoTichengSalesTargetService.save(waimaoTichengSalesTarget);
        if(flag){
            return ResultVo.success(waimaoTichengSalesTarget);
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "删除外贸提成销售目标", notes = "参数:销售目标id")
    @DeleteMapping("/del/{id}")
    public ResultVo delSaleTarget(@ApiParam(value = "id", name = "销售目标表ID", required = true) @PathVariable String  id){
        boolean flag = waimaoTichengSalesTargetService.removeById(id);
        if(flag){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "更新外贸提成销售目标", notes = "参数:销售目标参数")
    @PostMapping("/update")
    public ResultVo updateSaleTarget(@ApiParam(value = "waimaoTichengSalesTarget", required = true)
               @RequestBody WaimaoTichengSalesTarget waimaoTichengSalesTarget){
        boolean flag = waimaoTichengSalesTargetService.updateById(waimaoTichengSalesTarget);
        if(flag){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}
