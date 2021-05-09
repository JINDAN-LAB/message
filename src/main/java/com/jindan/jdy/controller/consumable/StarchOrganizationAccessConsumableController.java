package com.jindan.jdy.controller.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.consumable.StarchOrganizationAccessConsumableService;
import com.jindan.jdy.service.consumable.StarchOrganizationPutConsumableChuruService;
import com.jindan.jdy.service.consumable.StarchOrganizationPutConsumableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
*
* <p>说明： 耗材管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "耗材管理出入库页面管理")
@RestController
@RequestMapping("/starchOrganizationAccessConsumable")
public class StarchOrganizationAccessConsumableController{

    @Autowired
    private StarchOrganizationAccessConsumableService starchOrganizationAccessConsumableService;

    @Autowired
    private StarchOrganizationPutConsumableChuruService starchOrganizationPutConsumableChuruService;

    @Autowired
    private StarchOrganizationPutConsumableService starchOrganizationPutConsumableService;

    @ApiOperation("耗材出库与入库信息")
    @PostMapping("/seletStarchConsumable")
    public ResultVo seletRelevanceStarchManageCheck(@ApiParam(value = "jdyFlatalog", required = true)
                                                    @RequestBody StarchOrganizationAccessConsumableDto jdyFlatalog){
        PageInfo<StarchOrganizationAccessConsumableDto> list  = starchOrganizationAccessConsumableService.queryRelevanceCatList(jdyFlatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新耗材")
    @PostMapping("/updateStarchConsumable")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchOrganizationAccessConsumableDto warehouseDepository){
        boolean index = starchOrganizationAccessConsumableService.updateStarchMaintainRegister(warehouseDepository);
        if(index){
            return  ResultVo.success();
        }
        return  ResultVo.failed();
     }


    @ApiOperation(value = "增减确认耗材", notes = "参数:增减确认耗材")
    @PostMapping("/addStarchQuerenConsumable")
    public ResultVo addStarchQuerenConsumable(@ApiParam(name = "departmggestDto", required = false)
                                              @RequestBody StarchOrganizationAccessConsumableDto departmggestDto){
        System.out.println("=================");
        System.out.println(departmggestDto);
        boolean list  = starchOrganizationAccessConsumableService.addJdyQuerenFlowCatalog(departmggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation(value = "增加耗材", notes = "参数:增加耗材")
    @PostMapping("/addStarchConsumable")
    public ResultVo seleteCatalog(@ApiParam(name = "departmggestDto", required = false)
                                  @RequestBody StarchOrganizationAccessConsumableDto departmggestDto){
        System.out.println("=================");
        System.out.println(departmggestDto);
        boolean list  = starchOrganizationAccessConsumableService.addJdyFlowCatalog(departmggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除耗材")
    @DeleteMapping("deleteStarchConsumable/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = starchOrganizationAccessConsumableService.removeDetailsById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}