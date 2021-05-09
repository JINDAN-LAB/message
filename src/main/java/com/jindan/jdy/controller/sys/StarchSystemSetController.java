package com.jindan.jdy.controller.sys;

import com.jindan.jdy.common.pojo.StarchSystemSet;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.sys.StarchSystemSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 系统-设置表API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@Api(tags = "系统-设置表")
@RestController
@RequestMapping("/starchSystemSet")
public class StarchSystemSetController{

    @Autowired
    StarchSystemSetService starchSystemSetService;

    @ApiOperation(value = "查询资产设置", notes = "参数:查询设备")
    @PostMapping("/seleteJdySupplier")
    public ResultVo seleteJdySupplier(@ApiParam(name = "starchSystemSet", required = false)
                                      @RequestBody StarchSystemSet starchSystemSet ){

        List<StarchSystemSet> list = starchSystemSetService.seletelist(starchSystemSet);
        return  ResultVo.success(list) ;
    }

    @ApiOperation("更新资产设置")
    @PostMapping("updateJdySupplier")
    public ResultVo updateJdySupplier(@ApiParam(name = "jdySupplier", required = true)
                                      @RequestBody StarchSystemSet jdySupplier){
        boolean b = starchSystemSetService.updateById(jdySupplier);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增资产设置")
    @PostMapping("addJdySupplier")
    public ResultVo addJdySupplier( @ApiParam(name = "jdySupplier", required = true)
                                    @RequestBody StarchSystemSet jdySupplier){
        boolean save = starchSystemSetService.save(jdySupplier);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除资产设置")
    @DeleteMapping("deleteJdySupplier/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "供应商ID", required = true) @PathVariable String  id){
        boolean b = starchSystemSetService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}