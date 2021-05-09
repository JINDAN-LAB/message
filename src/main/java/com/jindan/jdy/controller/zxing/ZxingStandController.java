package com.jindan.jdy.controller.zxing;

import com.jindan.jdy.common.pojo.ZxingStand;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.zxing.ZxingStandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "二维码执行标准")
@RestController
@RequestMapping("/zxingStand")
public class ZxingStandController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingStandService zxingStandService;


    @ApiOperation(value = "执行标准信息", notes = "参数:方法信息")
    @PostMapping("/seleteZxingStand")
    public ResultVo seleteZxingStand( @ApiParam(name = "zxingStand", required = false)
                                       @RequestBody ZxingStand zxingStand ){
        if( redisUtil.get("zxingStand") == null){
            List<ZxingStand> page1 = zxingStandService.seletelist(zxingStand);
            redisUtil.set("zxingStand",page1);
            return  ResultVo.success(page1);
        }else{
            return  ResultVo.success(redisUtil.get("zxingStand"));
        }

    }

    @ApiOperation("使用标准修改")
    @PostMapping("updateZxingStand")
    public ResultVo updateZxingStand(@ApiParam(name = "zxingMethod", required = true)
                                      @RequestBody ZxingStand zxingStand){
        boolean b = zxingStandService.updateById(zxingStand);
        if(b){
            redisUtil.del("zxingStand");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("使用标准添加")
    @PostMapping("addZxingStand")
    public ResultVo addZxingStand( @ApiParam(name = "zxingStand", required = true)
                                    @RequestBody ZxingStand zxingStand){
        boolean save = zxingStandService.save(zxingStand);
        if(save){
            redisUtil.del("zxingStand");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除标准")
    @DeleteMapping("deleteZxingStand/{id}")
    public ResultVo deleteZxingStand(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = zxingStandService.removeById(id);
        if(b){
            redisUtil.del("zxingStand");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}