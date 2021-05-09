package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.dto.WarehouseCheckTitleDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.pojo.WarehouseCheckTitle;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.service.stock.WarehouseCheckService;
import com.jindan.jdy.service.stock.WarehouseCheckTitleService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.ArrayList;
import java.util.List;

/**
*
* <p>说明： 规则API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "盘点点表头信息")
@RestController
@RequestMapping("/warehouseCheckTitle")
public class WarehouseCheckTitleController{

    @Autowired
    WarehouseCheckTitleService warehouseCheckTitleService;

    @Autowired
    WarehouseCheckService warehouseCheckService;

    @ApiOperation(value = "查询表头查询", notes = "参数:查询表头查询")
    @PostMapping("/seletetitleAccess")
    public ResultVo seletetitleAccess( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseCheckTitle departmentSuggestDto ){
        List<WarehouseCheckTitle> list = warehouseCheckTitleService.seleteTile(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation(value = "查询盘点表头标题", notes = "参数:查询")
    @PostMapping("/seleteWarehouseTitleAccess")
    public ResultVo seleteWarehouseTitleAccess( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseCheckTitle departmentSuggestDto ){
        List<WarehouseCheckTitleDto> list = warehouseCheckTitleService.seletelistDepartmentSuggestDto(departmentSuggestDto);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody WarehouseCheckTitle userPermission){
        boolean b = warehouseCheckTitleService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                    @RequestBody  WarehouseCheckTitle userPermission){
        boolean save = warehouseCheckTitleService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增信息")
    @PostMapping("addAllWarehouseAccess")
    public ResultVo addAllWarehouseAccess( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody  WarehouseCheckTitleDto userPermission){
        String s = CommonUtils.GetGUID();
        WarehouseCheckTitle warehouseCheckTitle = new WarehouseCheckTitle();
        BeanUtils.copyProperties(userPermission,warehouseCheckTitle);
        warehouseCheckTitle.setCheckUuid(s);
        List<WarehouseCheck> warehouseChecks = new ArrayList<>();
        boolean save = warehouseCheckTitleService.save(warehouseCheckTitle);
        for (int i = 0; i < userPermission.getCheckList().size(); i++) {
            userPermission.getCheckList().get(i).setCheckUuid(s);
            WarehouseCheck warehouseCheckTit = new WarehouseCheck();
            BeanUtils.copyProperties(userPermission.getCheckList().get(i),warehouseCheckTit);
            warehouseChecks.add(warehouseCheckTit);
        }
        boolean b = warehouseCheckService.saveBatch(warehouseChecks);
        if(b && save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseCheckTitleService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除表头和表体")
    @DeleteMapping("deleteAllhouseAccess/{checkUuid}")
    public ResultVo deleteAllhouseAccess(@ApiParam(name = "checkUuid", value = "权限ID", required = true) @PathVariable String  checkUuid){

        boolean b = warehouseCheckTitleService.removeAllAccess(checkUuid);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("跟新表头和表体信息")
    @PostMapping("updateAllWarehouseAccess")
    public ResultVo updateAllWarehouseAccess( @ApiParam(name = "userPermission", required = true)
                                           @RequestBody  WarehouseCheckTitleDto userPermission){
        String s = CommonUtils.GetGUID();
        WarehouseCheckTitle warehouseCheckTitle = new WarehouseCheckTitle();
        BeanUtils.copyProperties(userPermission,warehouseCheckTitle);
        userPermission.setCheckUuid(s);
        List<WarehouseCheck> warehouseChecks = new ArrayList<>();
        boolean b1 = warehouseCheckTitleService.updateById(warehouseCheckTitle);
        for (int i = 0; i < userPermission.getCheckList().size(); i++) {
            userPermission.getCheckList().get(i).setCheckUuid(s);
            WarehouseCheck warehouseCheckTit = new WarehouseCheck();
            BeanUtils.copyProperties(userPermission.getCheckList().get(i),warehouseCheckTit);
            warehouseChecks.add(warehouseCheckTit);
        }
        boolean b = warehouseCheckService.updateBatchById(warehouseChecks);
        if(b && b1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}