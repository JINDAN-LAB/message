package com.jindan.jdy.controller.sys;

import com.jindan.jdy.common.pojo.JdyCommodityApplet;
import com.jindan.jdy.common.pojo.JdyDomain;
import com.jindan.jdy.service.sys.JdyCommodityAppletService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.sys.JdyDomainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 小程序商品API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Api(tags = "小程序商品")
@RestController
@RequestMapping("/jdyCommodityApplet")
public class JdyCommodityAppletController{


    @Autowired
    JdyCommodityAppletService jdyCommodityAppletService;

    @ApiOperation(value = "小程序商品查询", notes = "参数:小程序商品查询")
    @PostMapping("/seleteJdyCommodityApplet")
    public ResultVo seleteJdyCommodityApplet(@ApiParam(value = "jdyDomain", required = false)
                                       @RequestBody JdyCommodityApplet jdyDomain){
        List<JdyCommodityApplet> list = jdyCommodityAppletService.seleteAlllist(jdyDomain);
        return  ResultVo.success(list);
    }


    @ApiOperation("更新小程序商品")
    @PostMapping("/updateJdyCommodityApplet")
    public ResultVo updateJdyCommodityApplet(@ApiParam(value = "jdyDomain", required = true)
                                       @RequestBody JdyCommodityApplet jdyDomain){
        boolean b = jdyCommodityAppletService.updateById(jdyDomain);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增小程序商品")
    @PostMapping("/addJdyCommodityApplet")
    public ResultVo addJdyCommodityApplet( @ApiParam(name = "jdyDomain", required = true)
                                     @RequestBody JdyCommodityApplet jdyDomain){
        boolean save = jdyCommodityAppletService.saveJdyDomain(jdyDomain);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除小程序商品")
    @DeleteMapping("/deleteJdyCommodityApplet/{id}")
    public ResultVo deleteJdyCommodityApplet(@ApiParam(value = "id", name = "ID", required = true) @PathVariable String  id){
        boolean b = jdyCommodityAppletService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}