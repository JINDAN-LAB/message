package com.jindan.jdy.controller.stock;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtype;
import com.jindan.jdy.service.stock.StarchOrganizationPutSubtypeService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 资产子标题分类API接口层</P>
* @version: V1.0
* @author: kong
* @time  2020年10月04日
*
*/
@Api(tags = "资产子标题分类")
@RestController
@RequestMapping("/starchOrganizationPutSubtype")
public class StarchOrganizationPutSubtypeController{

    @Autowired
    StarchOrganizationPutSubtypeService starchOrganizationPutSubtypeService;

    @ApiOperation("查询资产子标题分类")
    @PostMapping("/seletSubDetails")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutSubtype jdyFlowCatalog){
        List<StarchOrganizationPutSubtype> starchOrganizationPutDtoPageInfo = starchOrganizationPutSubtypeService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("更新资产子标题分类")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                  @RequestBody StarchOrganizationPutSubtype warehouseDepository){
        boolean index = starchOrganizationPutSubtypeService.updateStarchMaintainRegister(warehouseDepository);
        return  ResultVo.success(index);
    }


    @ApiOperation(value = "增加资产子标题分类", notes = "参数:增加资产子标题分类")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationPutSubtype departmentSuggestDto){
        boolean list  = starchOrganizationPutSubtypeService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除资产子标题分类")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = starchOrganizationPutSubtypeService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}