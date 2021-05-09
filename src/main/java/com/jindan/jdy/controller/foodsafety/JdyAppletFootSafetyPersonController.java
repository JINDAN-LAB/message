package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblemsReult;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyProblemsReultService;
import com.jindan.jdy.service.foodsafety.JdyAppletFootSafetyPersonService;
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
* <p>说明： 食品安全人员管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月29日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "食品安全人员管理")
@RestController
@RequestMapping("/jdyAppletFootSafetyPerson")
public class JdyAppletFootSafetyPersonController{

    @Autowired
    JdyAppletFootSafetyPersonService jdyAppletFootSafetyPersonService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "查询食品安全人员管理", notes = "参数:查询食品安全人员管理")
    @PostMapping("/seletejdyAppletFootSafetyPerson")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFootSafetyPerson jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFootSafetyPerson> list = jdyAppletFootSafetyPersonService.seleteList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("seletejdyAppletFootSafetyPerson") == null){
                List<JdyAppletFootSafetyPerson> list = jdyAppletFootSafetyPersonService.seleteList(jdyAppletFoodSafetyDto);
                redisUtil.set("seletejdyAppletFootSafetyPerson",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seletejdyAppletFootSafetyPerson"));
            }
        }
    }

    @ApiOperation("更新食品安全人员管理")
    @PostMapping("updatejdyAppletFootSafetyPerson")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFootSafetyPerson userPermission){
        boolean b = jdyAppletFootSafetyPersonService.updateById(userPermission);
        if(b){
            redisUtil.del("seletejdyAppletFootSafetyPerson");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("添加食品安全人员管理")
    @PostMapping("addjdyAppletFootSafetyPerson")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFootSafetyPerson users){
        boolean save = jdyAppletFootSafetyPersonService.save(users);
        if(save){
            redisUtil.del("seletejdyAppletFootSafetyPerson");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全人员管理")
    @DeleteMapping("deletejdyAppletFootSafetyPerson/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFootSafetyPersonService.removeById(id);
        if(b){
            redisUtil.del("seletejdyAppletFootSafetyPerson");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}