package com.jindan.jdy.controller.department;

import com.jindan.jdy.common.pojo.JdyPacking;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.JdyPackingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 领料单API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@Slf4j
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

        log.info("jdyPacking的值为："+jdyPacking);
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

        log.info("jdySsp的值为："+jdySsp);
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