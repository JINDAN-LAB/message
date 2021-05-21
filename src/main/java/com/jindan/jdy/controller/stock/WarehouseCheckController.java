package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.stock.WarehouseCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 盘点单API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "盘点单内容")
@RestController
@RequestMapping("/warehouseCheck")
public class WarehouseCheckController{

    @Autowired
    WarehouseCheckService warehouseCheckService;

    @ApiOperation(value = "盘点单查询", notes = "参数:盘点单查询")
    @PostMapping("/seleteWarehouseAccess")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseCheckDto departmentSuggestDto ){
        Page<WarehouseCheck> list = warehouseCheckService.seletelist(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody WarehouseCheck userPermission){
        boolean b = warehouseCheckService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                    @RequestBody  WarehouseCheck userPermission){
        boolean save = warehouseCheckService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增多次增加信息")
    @PostMapping("addlistWarehouseAccess")
    public ResultVo zhengchangaddlistWarehouseAccess( @ApiParam(name = "userPermission", required = true)
                                            @RequestBody List<WarehouseCheck> userPermission){
        log.info("======“新增多次增加信息接口”开始执行======");
        log.info("userPermission的值为："+userPermission);
        boolean save = warehouseCheckService.saveBatch(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseCheckService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}