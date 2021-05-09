package com.jindan.jdy.controller.department;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdySspDto;
import com.jindan.jdy.common.pojo.JdyPacking;
import com.jindan.jdy.common.pojo.JdySsp;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.JdyPackingService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.department.JdySspService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 领料单API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "领料单")
@RestController
@RequestMapping("/jdyPacking")
public class JdyPackingController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JdyPackingService jdyPackingService;

    @ApiOperation(value = "领料单查询管理", notes = "参数:领料单查询管理")
    @PostMapping("/seleteJdyPacking")
    public ResultVo seleteJdyPacking(@ApiParam(value = "jdyPacking", required = false)
                                 @RequestBody JdyPacking jdyPacking){
        System.out.println("--------------");
        System.out.println(jdyPacking);
        System.out.println("===================");
        List<JdyPacking> list = jdyPackingService.seletelist(jdyPacking);

        return  ResultVo.success(list);
    }

    @ApiOperation("更新角色信息")
    @PostMapping("/updateJdyPacking")
    public ResultVo updateJdyPacking(@ApiParam(value = "jdySsp", required = true)
                                   @RequestBody JdyPacking jdySsp){
        boolean b = jdyPackingService.updateById(jdySsp);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增随手拍信息")
    @PostMapping("/addJdyPacking")
    public ResultVo addJdyPacking( @ApiParam(name = "jdySsp", required = true)
                                @RequestBody JdyPacking jdySsp){
        System.out.println("---------------");
        System.out.println(jdySsp);
        boolean save = jdyPackingService.save(jdySsp);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("随手拍信息")
    @DeleteMapping("/deleteJdyPacking/{id}")
    public ResultVo deleteJdyPacking(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = jdyPackingService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}