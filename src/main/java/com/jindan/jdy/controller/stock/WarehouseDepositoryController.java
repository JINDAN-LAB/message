package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gexin.rp.sdk.base.uitls.Base64Util;
import com.jindan.jdy.common.dto.JdyFlowCatalogDto;
import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.dto.WarehouseDepositoryDto;
import com.jindan.jdy.common.pojo.*;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.purchase.JdyCommodityService;
import com.jindan.jdy.service.purchase.JdyPurchaseService;
import com.jindan.jdy.service.stock.WarehouseAccessService;
import com.jindan.jdy.service.stock.WarehouseDepositoryService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.stock.WarehouseSpecsService;
import com.jindan.jdy.service.sys.JdyFlowCatalogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
@Api(tags =  "一级分类")
@RestController
@RequestMapping("/warehouseDepository")
public class WarehouseDepositoryController{

    @Autowired
    WarehouseDepositoryService warehouseDepositoryService;

    @Autowired
    JdyPurchaseService jdyPurchaseService;

    @Autowired
    WarehouseSpecsService warehouseSpecsService;

    @Autowired
    JdyFlowCatalogService jdyFlowCatalogService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("流程目录")
    @PostMapping("/seleteJdyFlowCatalog")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody JdyFlowCatalog jdyFlowCatalog){
        if(jdyFlowCatalog == null){
            if( redisUtil.hasKey("seletStarchManageCheck")){
                return  ResultVo.success(redisUtil.get("seletStarchManageCheck"));
            }
        }
        List<JdyFlowCatalogDto> list  = jdyFlowCatalogService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }


    @ApiOperation("获取仓库的分类")
    @PostMapping("/seleteFenleiJdyFlowCatalog")
    public ResultVo seleteFenleiJdyFlowCatalog(){
            List<WarehouseDepositoryDto> list  = warehouseDepositoryService.queryFenleiCatList();
            return  ResultVo.success(list);
    }

    @ApiOperation("更根据目录ID获取仓库的信息")
    @PostMapping("/seleteFenleiIDJdyFlowCatalog")
    public ResultVo seleteFenleiJdyFlowCatalog(WarehouseDepository warehouseDepository){
            List<WarehouseSpecs> list  = warehouseDepositoryService.queryFenleiIDCatList(warehouseDepository);
            redisUtil.set("seleteFenleiIDJdyFlowCatalog",list);
            return  ResultVo.success(list);
    }



    @ApiOperation(value = "仓库目录一级", notes = "参数:仓库目录")
    @PostMapping("/seleteCatalog")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody WarehouseDepositoryDto departmentSuggestDto){
        List<WarehouseDepositoryDto> list = warehouseDepositoryService.seleteWarehouseDepositoryDto(departmentSuggestDto);
        return  ResultVo.success(list);
    }



    @ApiOperation(value = "一级查询", notes = "参数:一级查询")
    @PostMapping("/seleteWarehouseAccess")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseDepositoryDto departmentSuggestDto ){
        List<WarehouseDepository> list = warehouseDepositoryService.seleteWarehouseDepository(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody  WarehouseDepository userPermission){
        boolean b = warehouseDepositoryService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody   WarehouseDepository userPermission){
        boolean save = warehouseDepositoryService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseDepositoryService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}