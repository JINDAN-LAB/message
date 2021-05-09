package com.jindan.jdy.controller.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdySupplier;
import com.jindan.jdy.service.sys.JdySupplierService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "供应商")
@RestController
@RequestMapping("/jdySupplier")
public class JdySupplierController{

    @Autowired
    JdySupplierService jdySupplierService;

    @ApiOperation(value = "供应商查询", notes = "参数:供应商")
    @PostMapping("/seleteJdySupplier")
    public ResultVo seleteJdySupplier(@ApiParam(name = "jdySupplier", required = false)
                                      @RequestBody JdySupplier jdySupplier ){
        List<JdySupplier> list = jdySupplierService.seletelist(jdySupplier);
        return  ResultVo.success(list) ;
    }

    @ApiOperation("供应商更新")
    @PostMapping("updateJdySupplier")
    public ResultVo updateJdySupplier(@ApiParam(name = "jdySupplier", required = true)
                                   @RequestBody JdySupplier jdySupplier){
        boolean b = jdySupplierService.updateById(jdySupplier);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增供应商信息")
    @PostMapping("addJdySupplier")
    public ResultVo addJdySupplier( @ApiParam(name = "jdySupplier", required = true)
                               @RequestBody JdySupplier jdySupplier){
        boolean save = jdySupplierService.save(jdySupplier);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除供应商信息")
    @DeleteMapping("deleteJdySupplier/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "供应商ID", required = true) @PathVariable String  id){
        boolean b = jdySupplierService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}