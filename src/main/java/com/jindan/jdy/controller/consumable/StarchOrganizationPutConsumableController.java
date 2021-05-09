package com.jindan.jdy.controller.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.consumable.StarchOrganizationPutConsumableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
*
* <p>说明： 耗材资产API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年9月14日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "耗材资产管理")
@RestController
@RequestMapping("/starchOrganizationPutConsumable")
public class StarchOrganizationPutConsumableController{

    @Autowired
    private StarchOrganizationPutConsumableService starchOrganizationPutConsumableService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("查询盘点管理")
    @PostMapping("/seletStarchOrganizationPutConsumable")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationPutConsumableDto jdyFlowCatalog){
        PageInfo<StarchOrganizationPutConsumableDto> starchOrganizationPutDtoPageInfo = starchOrganizationPutConsumableService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(starchOrganizationPutDtoPageInfo);
    }

    @ApiOperation("更新盘点管理")
    @PostMapping("/updateStarchOrganizationPutConsumable")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                  @RequestBody StarchOrganizationPutConsumable warehouseDepository){
        boolean index = starchOrganizationPutConsumableService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加盘点管理", notes = "参数:增加盘点管理")
    @PostMapping("/addStarchOrganizationPutConsumable")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationPutConsumable departmentSuggestDto){
        boolean list  = starchOrganizationPutConsumableService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("删除盘点管理")
    @DeleteMapping("deleteStarchOrganizationPutConsumable/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = starchOrganizationPutConsumableService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}