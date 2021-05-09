package com.jindan.jdy.controller.purchase;

import com.jindan.jdy.common.dto.JdyPutTypeDto;
import com.jindan.jdy.common.pojo.JdyPutType;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.purchase.JdyPutTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api("采购申请类型")
@RestController
@RequestMapping("/jdyPutType")
public class JdyPutTypeController{

    @Autowired
    JdyPutTypeService jdyPutService;

//  排序汇总
    @ApiOperation(value = "查询排序", notes = "参数:查询排序")
    @GetMapping("/seleteOrders")
    public ResultVo seleteOrders(){
        List<JdyPutTypeDto> list = jdyPutService.seletelistOrder();
        return ResultVo.success(list);
    }

    @ApiOperation(value = "采购查询", notes = "参数:采购信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyPutType  jdyPurchaseDto){
        List<JdyPutType> list = jdyPutService.seletelist(jdyPurchaseDto);
        return ResultVo.success(list);
    }

    @ApiOperation("更新采购表头信息")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                      @RequestBody JdyPutType JdyPut){
        boolean save = jdyPutService.updateById(JdyPut);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增采购信息")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                              @RequestBody JdyPutType  jdyPurchaseDto){
        boolean save1 = jdyPutService.insertSave(jdyPurchaseDto);
        if(save1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除采购商品信息")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        boolean remove = jdyPutService.removeById(commodityId);
        if(remove){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }







}