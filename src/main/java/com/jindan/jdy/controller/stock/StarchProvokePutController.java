package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.jindan.jdy.common.pojo.StarchProvokePut;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchMaintainRegisterService;
import com.jindan.jdy.service.stock.StarchProvokePutService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 资产调拨商品关系表API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "关系库-资产调拨商品关系表")
@RestController
@RequestMapping("/starchProvokePut")
public class StarchProvokePutController{

    @Autowired
    private StarchProvokePutService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("查询资产维修登记")
    @PostMapping("/seleteJdyFlowCatalog")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchProvokePut jdyFlowCatalog){
        List<StarchProvokePut> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("更资产维修登记")
    @PostMapping("/updateStarchMaintainRegister")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchProvokePut warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加资产维修登记", notes = "参数:增加资产维修登记")
    @PostMapping("/addJdyFlowCatalog")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchProvokePut departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteStockDepository/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}