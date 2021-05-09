package com.jindan.jdy.controller.zxing;

import com.jindan.jdy.common.pojo.ZxingCatalogue;
import com.jindan.jdy.common.pojo.ZxingErweimakg;
import com.jindan.jdy.common.pojo.ZxingMethod;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.zxing.ZxingErweimakgService;
import com.jindan.jdy.service.zxing.ZxingMethodService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 自定义验证二维码域名目录API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "二维码使用说明")
@RestController
@RequestMapping("/zxingMethod")
public class ZxingMethodController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingMethodService zxingMethodService;

    @ApiOperation(value = "使用方法信息", notes = "参数:方法信息")
    @PostMapping("/seleteZxingMethod")
    public ResultVo seleteZxingMethod( @ApiParam(name = "zxingMethod", required = false)
                                      @RequestBody  ZxingMethod zxingMethod ){
        if( redisUtil.get("zxingMethod") == null){
            List<ZxingMethod> page1 = zxingMethodService.seletelist(zxingMethod);
            redisUtil.set("zxingMethod",page1);
            return  ResultVo.success(page1);
        }else{
            return  ResultVo.success(redisUtil.get("zxingMethod"));
        }
    }

    @ApiOperation("使用方法修改")
    @PostMapping("updateZxingMethod")
    public ResultVo updateZxingMethod(@ApiParam(name = "zxingMethod", required = true)
                                   @RequestBody ZxingMethod zxingMethod ){
        boolean b = zxingMethodService.updateById(zxingMethod);
        if(b){
            redisUtil.del("zxingMethod");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("使用方法添加")
    @PostMapping("addZxingMethod")
    public ResultVo addZxingMethod( @ApiParam(name = "zxingMethod", required = true)
                               @RequestBody ZxingMethod zxingMethod){
        boolean save = zxingMethodService.save(zxingMethod);
        if(save){
            redisUtil.del("zxingMethod");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除使用方法")
    @DeleteMapping("deleteZxingMethod/{id}")
    public ResultVo deleteZxingMethod(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = zxingMethodService.removeById(id);
        if(b){
            redisUtil.del("zxingMethod");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }





}