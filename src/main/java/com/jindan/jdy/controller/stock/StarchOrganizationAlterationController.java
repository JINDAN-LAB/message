package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.dto.StarchOrganizationAlterationDto;
import com.jindan.jdy.common.dto.StarchOrganizationBorrowReturnDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAccess;
import com.jindan.jdy.common.pojo.StarchOrganizationAlteration;
import com.jindan.jdy.common.pojo.StarchOrganizationBorrowReturn;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchOrganizationAccessService;
import com.jindan.jdy.service.stock.StarchOrganizationAlterationService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
/**
*
* <p>说明： 资产信息变更API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产信息变更")
@RestController
@RequestMapping("/starchOrganizationAlteration")
public class StarchOrganizationAlterationController{

    @Autowired
    private StarchOrganizationAlterationService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("查询盘点管理")
    @PostMapping("/seletStarchOrganizationAlteration")
    public ResultVo seleteStarchOrganizationAlteration(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationAlterationDto jdyFlowCatalog){
        PageInfo<StarchOrganizationAlterationDto> list  = stockDepositoryService.queryRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }
    

    @ApiOperation("查询盘点管理")
    @PostMapping("/seletStarchManageCheck")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchOrganizationAlterationDto jdyFlowCatalog){
        Page<StarchOrganizationAlteration> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新盘点管理")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister( @ApiParam(value = "warehouseDepository", required = true)
                                                      @RequestBody StarchOrganizationAlteration warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加盘点管理", notes = "参数:增加盘点管理")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchOrganizationAlteration departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除盘点管理")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}