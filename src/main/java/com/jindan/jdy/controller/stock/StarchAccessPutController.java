package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.pojo.StarchAccessPut;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchAccessPutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 领用和退库关系表API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "关系库-领用和退库关系表")
@RestController
@RequestMapping("/starchAccessPut")
public class StarchAccessPutController{

        @Autowired
        private StarchAccessPutService stockDepositoryService;

        @Autowired
        RedisUtil redisUtil;

        @ApiOperation("查询资产维修登记")
        @PostMapping("/seleteJdyFlowCatalog")
        public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                             @RequestBody StarchAccessPut jdyFlowCatalog){
            List<StarchAccessPut> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
            return  ResultVo.success(list);
        }

        @ApiOperation("更资产维修登记")
        @PostMapping("/updateStarchMaintainRegister")
        public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                             @RequestBody StarchAccessPut warehouseDepository){
            boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
            redisUtil.set("StarchAccessPut",index);
            return  ResultVo.success(index);
        }

        @ApiOperation(value = "增加资产维修登记", notes = "参数:增加资产维修登记")
        @PostMapping("/addJdyFlowCatalog")
        public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody StarchAccessPut departmentSuggestDto){
            boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
            return  ResultVo.success(list);
        }

        @ApiOperation("删除信息")
        @DeleteMapping("deleteStockDepository/{id}")
        public ResultVo deletekeyStarchAccessPut(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
            boolean b = stockDepositoryService.removeById(id);
            if(b){
                return ResultVo.success();
            }
            return ResultVo.failed();
        }

}