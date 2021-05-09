package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtypeAsset;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.stock.StarchOrganizationPutSubtypeAssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 资产子设备信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年10月04日
*
*/
@Api(tags = "资产子设备信息")
@RestController
@RequestMapping("/starchOrganizationPutSubtypeAsset")
public class StarchOrganizationPutSubtypeAssetController{

    @Autowired
    StarchOrganizationPutSubtypeAssetService starchOrganizationPutSubtypeService;

    @ApiOperation("查询资产子设备信息")
    @PostMapping("/seletSubDetails")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutSubtypeAsset jdyFlowCatalog){

        List<StarchOrganizationPutSubtypeAsset> starchOrganizationPutDtoPageInfo = starchOrganizationPutSubtypeService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }


    @ApiOperation("更新资产子设备信息")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                  @RequestBody StarchOrganizationPutSubtypeAsset warehouseDepository){
        boolean index = starchOrganizationPutSubtypeService.updateStarchMaintainRegister(warehouseDepository);
        return  ResultVo.success(index);
    }


    @ApiOperation(value = "增加资产子设备信息", notes = "参数:增加资产子设备信息")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationPutSubtypeAsset departmentSuggestDto){
        boolean list  = starchOrganizationPutSubtypeService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除资产子设备信息")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = starchOrganizationPutSubtypeService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}