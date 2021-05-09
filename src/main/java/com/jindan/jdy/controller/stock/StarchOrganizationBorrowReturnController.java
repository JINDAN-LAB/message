package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationBorrowReturnDto;
import com.jindan.jdy.common.pojo.StarchOrganizationBorrowReturn;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchOrganizationBorrowReturnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
*
* <p>说明： 资产借用与归还API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api( tags = "资产借用与归还")
@RestController
@RequestMapping("/starchOrganizationBorrowReturn")
public class StarchOrganizationBorrowReturnController{

    @Autowired
    private StarchOrganizationBorrowReturnService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("资产借用与归还信息")
    @PostMapping("/seletRelevanceStarchOrganizationBorrowReturn")
    public ResultVo seletRelevanceStarchOrganizationBorrowReturn(@ApiParam(value = "jdyFlowCatalog", required = true)
                                                    @RequestBody StarchOrganizationBorrowReturnDto jdyFlowCatalog){
        PageInfo<StarchOrganizationBorrowReturnDto> list  = stockDepositoryService.queryRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("查询资产借用与归还")
    @PostMapping("/seletStarchManageCheck")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationBorrowReturnDto jdyFlowCatalog){
        Page<StarchOrganizationBorrowReturn> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新资产借用")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchOrganizationBorrowReturnDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation("更新表头信息归还")
    @PostMapping("/updateBiaotouStarchManageCheck")
    public ResultVo updateBiaotouStarchManageCheck(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchOrganizationBorrowReturnDto  warehouseDepository){
        boolean index = stockDepositoryService.updateStarchGuihuanMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加资产借用与归还", notes = "参数:增加资产借用与归还")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationBorrowReturnDto departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("删除资产借用与归还")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removePointPracticableById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


     

}