package com.jindan.jdy.controller.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableChuruDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.consumable.StarchOrganizationPutConsumableChuruService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.stock.StarchOrganizationAccessService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
/**
*
* <p>说明： 耗材资产出入库内容API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年9月14日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Slf4j
@Api(tags = "耗材资产出入库内容")
@RestController
@RequestMapping("/starchOrganizationPutConsumableChuru")
public class StarchOrganizationPutConsumableChuruController{

    @Autowired
    private StarchOrganizationAccessService stockDepositoryService;

    @Autowired
    private StarchOrganizationPutConsumableChuruService starchOrganizationPutConsumableChuruService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation("资产领用与退库关联资产信息")
    @PostMapping("/seletRelevanceStarchManageCheck")
    public ResultVo seletRelevanceStarchManageCheck(@ApiParam(value = "jdyFlatalog", required = true)
                                                    @RequestBody StarchOrganizationAccessDto jdyFlatalog){
        PageInfo<StarchOrganizationAccessDto> list  = stockDepositoryService.queryRelevanceCatList(jdyFlatalog);
        return  ResultVo.success(list);
    }

//    @ApiOperation("查询盘点管理")
//    @PostMapping("/seletStarchManageCheck")
//    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
//                                         @RequestBody StarchOrganizationAccessDto jdyFlowCatalog){
//        Page<StarchOrganizationAccess> list  = stockDepositoryService.queryCatList(jdyFlowCatalog);
//        return  ResultVo.success(list);
//    }

    @ApiOperation("更新资产领用")
    @PostMapping("/updateStarchManageCheck")
    public ResultVo updateStarchMaintainRegister(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchOrganizationAccessDto warehouseDepository){
        boolean index = stockDepositoryService.updateStarchMaintainRegister(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
        if(index){
            return  ResultVo.success();
        }
        return  ResultVo.failed();
    }

    @ApiOperation("新增修改部门耗材接口")
    @PostMapping("/updateSinglesStarchManageCheck")
    public ResultVo updateSinglesStarchManageCheck(@ApiParam(value = "warehouseDepository", required = true)
                                                 @RequestBody StarchOrganizationPutConsumableChuru warehouseDepository){
        boolean index = stockDepositoryService.updateSinglesStarchManageCheck(warehouseDepository);
        redisUtil.set("seleteFenleiIDJdyFlowCatalog",index);
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


    @ApiOperation("出入库耗材资产信息")
    @PostMapping("/selearchConsumable")
    public ResultVo seletRelevanceStarchManageCheck(@ApiParam(value = "jdyFlatalog", required = true)
                                                    @RequestBody StarchOrganizationPutConsumableChuruDto jdyFlatalog){
        PageInfo<StarchOrganizationPutConsumableChuru> list  = starchOrganizationPutConsumableChuruService.queryRelevanceCatList(jdyFlatalog);
        return  ResultVo.success(list);
    }



}