package com.jindan.jdy.controller.zxing;

import com.jindan.jdy.common.pojo.ZxingErweimakg;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.zxing.ZxingErweimakgService;
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
@Api(tags = "净重、毛重管理")
@RestController
@RequestMapping("/zxingErweimakg")
public class        ZxingErweimakgController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ZxingErweimakgService zxingErweimakgService;

    @ApiOperation(value = "净重信息", notes = "参数:净重信息")
    @PostMapping("/seleteZxingSuttle")
    public ResultVo seleteDepartment( @ApiParam(name = "zxingErweimakg", required = false)
                                      @RequestBody ZxingErweimakg zxingErweimakg ){
        if( redisUtil.get("seleteZxingSuttle") == null){
            zxingErweimakg.setPid("1");
            List<ZxingErweimakg> page1 = zxingErweimakgService.seletelist(zxingErweimakg);
            redisUtil.set("seleteZxingSuttle",page1);
            return  ResultVo.success(page1);
         }else{
            return  ResultVo.success(redisUtil.get("seleteZxingSuttle"));
        }

    }

    @ApiOperation("净重修改")
    @PostMapping("updateZxingErweimakg")
    public ResultVo updatefacility(@ApiParam(name = "zxingErwei", required = true)
                                   @RequestBody ZxingErweimakg  zxingErwei ){
        boolean b = zxingErweimakgService.updateById(zxingErwei);
        if(b){
            redisUtil.del("seleteZxingSuttle");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增净重")
    @PostMapping("addZxingErweimakg")
    public ResultVo addsubset( @ApiParam(name = "zxingErwei", required = true)
                               @RequestBody ZxingErweimakg  zxingErwei){
        zxingErwei.setPid("1");
        boolean save = zxingErweimakgService.save(zxingErwei);
        if(save){
            redisUtil.del("seleteZxingSuttle");
            redisUtil.del("seleteZxingSuttle2");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除净重")
    @DeleteMapping("deleteZxingErweimakg/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = zxingErweimakgService.removeById(id);
        if(b){
            redisUtil.del("seleteZxingSuttle");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation(value = "毛重信息", notes = "参数:毛重信息")
    @PostMapping("/seletekg")
    public ResultVo seletekg( @ApiParam(name = "zxingErweimakg", required = false)
                                      @RequestBody ZxingErweimakg zxingErweimakg ){
        if( redisUtil.get("seleteZxingSuttle2") == null){
            zxingErweimakg.setPid("2");
            List<ZxingErweimakg> page1 = zxingErweimakgService.seletelist(zxingErweimakg);
            redisUtil.set("seleteZxingSuttle2",page1);
            return  ResultVo.success(page1);
        }else{
            return  ResultVo.success(redisUtil.get("seleteZxingSuttle2"));
        }
    }

    @ApiOperation("毛重修改")
    @PostMapping("updatekg")
    public ResultVo updatekg(@ApiParam(name = "zxingErwei", required = true)
                                   @RequestBody ZxingErweimakg  zxingErwei ){
        boolean b = zxingErweimakgService.updateById(zxingErwei);
        if(b){
            redisUtil.del("seleteZxingSuttle2");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("毛重净重")
    @PostMapping("addkg")
    public ResultVo addkg( @ApiParam(name = "zxingErwei", required = true)
                               @RequestBody ZxingErweimakg  zxingErwei){
        zxingErwei.setPid("2");
        boolean save = zxingErweimakgService.save(zxingErwei);
        if(save){
            redisUtil.del("seleteZxingSuttle2");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除毛重")
    @DeleteMapping("deletekg/{id}")
    public ResultVo deletekg(@ApiParam(name = "id", value = "ID", required = true) @PathVariable String  id){
        boolean b = zxingErweimakgService.removeById(id);
        if(b){
            redisUtil.del("seleteZxingSuttle");
            redisUtil.del("seleteZxingSuttle2");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}