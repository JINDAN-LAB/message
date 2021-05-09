package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationProvokeDto;
import com.jindan.jdy.common.dto.StarchPropertyScrapDto;
import com.jindan.jdy.common.pojo.StarchOrganizationProvoke;
import com.jindan.jdy.common.pojo.StarchPropertyScrap;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchOrganizationProvokeService;
import com.jindan.jdy.service.stock.StarchPropertyScrapService;
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
* <p>说明： 清理报废API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产清理报废")
@RestController
@RequestMapping("/starchPropertyScrap")
public class StarchPropertyScrapController{

    @Autowired
    private StarchPropertyScrapService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("资产清理报废")
    @PostMapping("/seletRelevanceStarchOrganizationProvoke")
    public ResultVo seletRelevanceStarchOrganizationProvoke(@ApiParam(value = "jdyFlowCatalog", required = true)
                                                            @RequestBody StarchPropertyScrapDto jdyFlowCatalog){
        PageInfo<StarchPropertyScrapDto> list = stockDepositoryService.queryRelevanceCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("查询资产清理报废")
    @PostMapping("/seletStarchManageCheck")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody StarchPropertyScrapDto jdyFlowCatalog){
        Page<StarchPropertyScrap> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新资产清理报废")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchPropertyScrapDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        return  ResultVo.success(index);
    }

    @ApiOperation(value = "增加资产清理报废", notes = "参数:增加资产清理报废")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmentSuggestDto", required = false)
                                  @RequestBody StarchPropertyScrapDto departmentSuggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("删除资产清理报废")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeDetailsById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}