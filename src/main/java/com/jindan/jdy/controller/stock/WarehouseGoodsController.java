package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.pojo.WarehouseDepository;
import com.jindan.jdy.common.pojo.WarehouseGoods;
import com.jindan.jdy.service.stock.WarehouseDepositoryService;
import com.jindan.jdy.service.stock.WarehouseGoodsService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 仓库管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags =  "二级分类")
@RestController
@RequestMapping("/warehouseGoods")
public class WarehouseGoodsController{

    @Autowired
    WarehouseGoodsService warehouseGoodsService;

    @ApiOperation(value = "重点项查询", notes = "参数:重点项查询")
    @PostMapping("/seleteWarehouseAccess")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseGoods departmentSuggestDto ){
        List<WarehouseGoods> list = warehouseGoodsService.seleteWarehouseGoodsService(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody WarehouseGoods userPermission){
        boolean b = warehouseGoodsService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody   WarehouseGoods userPermission){
        boolean save = warehouseGoodsService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseGoodsService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}