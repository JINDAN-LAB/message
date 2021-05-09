package com.jindan.jdy.controller.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointProjectDto;
import com.jindan.jdy.common.dto.WarehouseAccessDto;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.common.pojo.WarehouseAccess;
import com.jindan.jdy.service.keypoint.KeyPointProjectService;
import com.jindan.jdy.service.stock.WarehouseAccessService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 仓库管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags =  "重构仓库进出库管理")
@RestController
@RequestMapping("/warehouseAccess")
public class WarehouseAccessController{


    @Autowired
    WarehouseAccessService warehouseAccessService;

    @ApiOperation(value = "进出库查询", notes = "参数:进出库查询")
    @PostMapping("/seleteWarehouseAccess")
    public ResultVo seleteDepartment( @ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody WarehouseAccessDto departmentSuggestDto ){
        List<KeyPointProject> list = warehouseAccessService.seleteDepartmentFacility(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updateWarehouseAccess")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody WarehouseAccess userPermission){
        boolean b = warehouseAccessService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addWarehouseAccess")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody  WarehouseAccess userPermission){
        boolean save = warehouseAccessService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deleteWarehouseAccess/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = warehouseAccessService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}