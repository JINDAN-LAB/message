package com.jindan.jdy.controller.zxing;

import com.jindan.jdy.common.pojo.ZxingVerify;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.zxing.ZxingVerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 流程控制API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "二维码验证信息")
@RestController
@RequestMapping("/zxingVerify")
public class ZxingVerifyController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingVerifyService zxingVerifyService;

    @ApiOperation(value = "二维码验证信息", notes = "参数:验证信息")
    @PostMapping("/seleteZxingVerify")
    public ResultVo seleteZxingVerify(@ApiParam(name = "zxingStand", required = false)
                                     @RequestBody ZxingVerify  zxingStand ){
        if( redisUtil.get(zxingStand.getVerifyId()) == null){
            List<ZxingVerify > page1 = zxingVerifyService.seletelist(zxingStand);
            redisUtil.set(zxingStand.getVerifyId(),page1);
            return  ResultVo.success(page1);
        }else{
            return  ResultVo.success(redisUtil.get(zxingStand.getVerifyId()));
        }
    }

    @ApiOperation("修改验证页面信息")
    @PostMapping("updateZxingVerify")
    public ResultVo updateZxingVerify(@ApiParam(name = "zxingMethod", required = true)
                                      @RequestBody ZxingVerify zxingStand){
        boolean b = zxingVerifyService.updateById(zxingStand);
        if(b){
            redisUtil.del(zxingStand.getVerifyId());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("添加验证页面信息")
    @PostMapping("addZxingVerify")
    public ResultVo addZxingStand( @ApiParam(name = "zxingVerify", required = true)
                                   @RequestBody ZxingVerify  zxingVerify){
        boolean save = zxingVerifyService.save(zxingVerify);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除验证页面")
    @DeleteMapping("deleteZxingVerify/{id}")
    public ResultVo deleteZxingStand(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = zxingVerifyService.removeById(id);
        if(b){
            redisUtil.del(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }




}