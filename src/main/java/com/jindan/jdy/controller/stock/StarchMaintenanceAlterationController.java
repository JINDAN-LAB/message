package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.dto.StarchMaintenanceAlterationDto;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchMaintainRegisterService;
import com.jindan.jdy.service.stock.StarchMaintenanceAlterationService;
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
* <p>说明： 资产维保信息变更API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产维保信息变更")
@RestController
@RequestMapping("/starchMaintenanceAlteration")
public class StarchMaintenanceAlterationController{

    @Autowired
    private StarchMaintenanceAlterationService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("查询资产维保信息变更")
    @PostMapping("/seletetarchMaintenanceAlteration")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchMaintenanceAlterationDto jdyFlowCatalog){
        PageInfo<StarchMaintenanceAlterationDto> list = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新资产维保信息变更")
    @PostMapping("/updatetarchMaintenanceAlteration")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                      @RequestBody StarchMaintenanceAlterationDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加资产维保信息变更", notes = "参数:增加资产维修登记")
    @PostMapping("/addtarchMaintenanceAlteration")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchMaintenanceAlterationDto departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除资产维保信息变更")
    @DeleteMapping("deletetarchMaintenanceAlteration/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeDetailsById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}