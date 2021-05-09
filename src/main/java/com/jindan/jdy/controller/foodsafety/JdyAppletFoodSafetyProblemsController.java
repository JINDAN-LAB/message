package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyProblems;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyProblemsService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 食品安全小程序API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月27日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "食品安全问题")
@RestController
@RequestMapping("/jdyAppletFoodSafetyProblems")
public class JdyAppletFoodSafetyProblemsController{

    @Autowired
    JdyAppletFoodSafetyProblemsService jdyAppletFoodSafetyProblemsService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "食品安全问题", notes = "参数:食品安全问题")
    @PostMapping("/seletejdyAppletFoodSafetyProblems")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyProblems jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodSafetyProblems> list = jdyAppletFoodSafetyProblemsService.seleteAlllist(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("seletejdyAppletFoodSafetyProblems") == null){
                List<JdyAppletFoodSafetyProblems> list = jdyAppletFoodSafetyProblemsService.seleteAlllist(jdyAppletFoodSafetyDto);
                redisUtil.set("seletejdyAppletFoodSafetyProblems",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seletejdyAppletFoodSafetyProblems"));
            }
        }
    }

    @ApiOperation("更新食品安全问题")
    @PostMapping("updatejdyAppletFoodSafetyProblems")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodSafetyProblems userPermission){
        boolean b = jdyAppletFoodSafetyProblemsService.updateById(userPermission);
        if(b){
            redisUtil.del("seletejdyAppletFoodSafetyProblems");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



    @ApiOperation("新增食品安全问题")
    @PostMapping("addjdyAppletFoodSafetyProblems")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodSafetyProblems users){
        boolean save = jdyAppletFoodSafetyProblemsService.save(users);
        if(save){
            redisUtil.del("seletejdyAppletFoodSafetyProblems");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全问题")
    @DeleteMapping("deletejdyAppletFoodSafetyProblems/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyProblemsService.removeById(id);
        if(b){
            redisUtil.del("seletejdyAppletFoodSafetyProblems");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}