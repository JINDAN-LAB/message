package com.jindan.jdy.controller.purchase;

import com.jindan.jdy.common.pojo.JdyDetailsGoods;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.purchase.JdyDetailsGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 仓库基本信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2021年1月11日
*
*/
@Api(tags ="仓库基本信息")
@RestController
@RequestMapping("/jdyDetailsGoods")
public class JdyDetailsGoodsController{

    @Autowired
    JdyDetailsGoodsService jdyDetailsGoodsService;

    @ApiOperation(value = "到货单表体信息查询", notes = "参数:到货单表体信息信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyDetailsGoods jdyPurchaseDto){
        List<JdyDetailsGoods> list = jdyDetailsGoodsService.seletelist(jdyPurchaseDto);
        return ResultVo.success(list);
    }

    @ApiOperation("更新到货单表体信息")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                      @RequestBody JdyDetailsGoods JdyPut){
        boolean save = jdyDetailsGoodsService.updateById(JdyPut);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增到货单表体信息")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                              @RequestBody JdyDetailsGoods  jdyPurchaseDto){
        boolean save1 = jdyDetailsGoodsService.insertSave(jdyPurchaseDto);
        if(save1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除到货单表体信息")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        boolean remove = jdyDetailsGoodsService.removeById(commodityId);
        if(remove){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}