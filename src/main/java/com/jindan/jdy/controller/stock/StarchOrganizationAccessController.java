package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchManageCheckDto;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.pojo.StarchManageCheck;
import com.jindan.jdy.common.pojo.StarchOrganizationAccess;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.stock.StarchManageCheckService;
import com.jindan.jdy.service.stock.StarchOrganizationAccessService;
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
* <p>说明： 资产领用与退库API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年8月24日
*
*/

@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "资产领用与退库")
@RestController
@RequestMapping("/starchOrganizationAccess")
public class StarchOrganizationAccessController{

    @Autowired
    private StarchOrganizationAccessService stockDepositoryService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("资产领用与退库关联资产信息")
    @PostMapping("/seletRelevanceStarchManageCheck")
    public ResultVo seletRelevanceStarchManageCheck(@ApiParam(value = "jdyFlatalog", required = true)
                                                    @RequestBody StarchOrganizationAccessDto jdyFlatalog){
        PageInfo<StarchOrganizationAccessDto>  list  = stockDepositoryService.queryRelevanceCatList(jdyFlatalog);
        return  ResultVo.success(list);
    }

//  更新资产领用，确认领用
    @ApiOperation("修改状态接口")
    @PostMapping("/updateZhuangtaiStarchManageCheck")
    public ResultVo updateZhuangtaiStarchManageCheck(@ApiParam(value = "warehouseDepository", required = true)
                                                     @RequestBody StarchOrganizationAccessDto warehouseDepository){
        boolean index = stockDepositoryService.updateZhuangtaiStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        if(index){
            return  ResultVo.success();
        }
        return  ResultVo.failed();
    }

    @ApiOperation("更新资产领用与退库")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                  @RequestBody StarchOrganizationAccessDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        if(index){
            return  ResultVo.success();
        }
        return  ResultVo.failed();
    }


    @ApiOperation(value = "增加资产领用与退库", notes = "参数:增加资产领用与退库")
    @PostMapping("/addStarchManageCheck")
    public ResultVo seleteCatalog(@ApiParam(name = "departmggestDto", required = false)
                                  @RequestBody StarchOrganizationAccessDto departmggestDto){
        boolean list  = stockDepositoryService.addJdyFlowCatalog(departmggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("删除资产领用与退库关联资产")
    @DeleteMapping("deleteStarchManageCheck/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = stockDepositoryService.removeDetailsById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}