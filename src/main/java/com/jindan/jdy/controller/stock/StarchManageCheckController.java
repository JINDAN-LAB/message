package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.StarchMaintenanceAlterationDto;
import com.jindan.jdy.common.dto.StarchManageCheckDto;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import com.jindan.jdy.common.pojo.StarchManageCheck;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchMaintenanceAlterationService;
import com.jindan.jdy.service.stock.StarchManageCheckService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
/**
*
* <p>说明： 盘点管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "盘点管理")
@RestController
@RequestMapping("/starchManageCheck")
public class StarchManageCheckController{

    @Autowired
    private StarchManageCheckService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("查询盘点管理")
    @PostMapping("/seletStarchManageCheck")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchManageCheckDto jdyFlowCatalog){
        Page<StarchManageCheck> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新盘点管理")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                      @RequestBody StarchManageCheck warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加盘点管理", notes = "参数:增加盘点管理")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchManageCheck departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除盘点管理")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}