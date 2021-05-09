package com.jindan.jdy.controller.purchase;

import com.jindan.jdy.common.pojo.JdyCommodityArrive;
import com.jindan.jdy.common.pojo.JdyCommodityCatalog;
import com.jindan.jdy.service.purchase.JdyCommodityArriveService;
import com.jindan.jdy.service.purchase.JdyCommodityCatalogService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 仓库目录信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2021年1月25日
*
*/
@Api(tags ="仓库目录信息")
@RestController
@RequestMapping("/jdyCommodityCatalog")
public class JdyCommodityCatalogController{

    @Autowired
    JdyCommodityCatalogService jdyCommodityCatalogService;

    @ApiOperation(value = "到货单表体信息查询", notes = "参数:到货单表体信息信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyCommodityCatalog jdyPurchaseDto){
        List<JdyCommodityCatalog> list = jdyCommodityCatalogService.seletelist(jdyPurchaseDto);
        return ResultVo.success(list);
    }

    @ApiOperation("更新到货单表体信息")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                      @RequestBody JdyCommodityCatalog JdyPut){
        boolean save = jdyCommodityCatalogService.updateById(JdyPut);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增到货单表体信息")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                              @RequestBody JdyCommodityCatalog  jdyPurchaseDto){
        boolean save1 = jdyCommodityCatalogService.insertSave(jdyPurchaseDto);
        if(save1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除到货单表体信息")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        boolean remove = jdyCommodityCatalogService.removeById(commodityId);
        if(remove){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}