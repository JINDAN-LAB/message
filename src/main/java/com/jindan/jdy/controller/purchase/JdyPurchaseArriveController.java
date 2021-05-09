package com.jindan.jdy.controller.purchase;

import com.jindan.jdy.common.pojo.JdyPurchaseArrive;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.purchase.JdyPurchaseArriveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 到货单表头信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月29日
*
*/
@Api(tags = "到货单表头信息")
@RestController
@RequestMapping("/jdyPurchaseArrive")
public class JdyPurchaseArriveController{

    @Autowired
    JdyPurchaseArriveService jdyPutService;


    @ApiOperation(value = "采购到货单表头信息", notes = "参数:采购到货单表头信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyPurchaseArrive jdyPurchaseDto){
        List<JdyPurchaseArrive> list = jdyPutService.seletelist(jdyPurchaseDto);
        return ResultVo.success(list);
    }
    

    @ApiOperation("更新到货单表头信息")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                      @RequestBody JdyPurchaseArrive JdyPut){
        boolean save = jdyPutService.updateById(JdyPut);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增到货单表头信息")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                              @RequestBody JdyPurchaseArrive  jdyPurchaseDto){
        boolean save1 = jdyPutService.insertSave(jdyPurchaseDto);
        if(save1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除到货单表头信息")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        boolean remove = jdyPutService.removeById(commodityId);
        if(remove){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}