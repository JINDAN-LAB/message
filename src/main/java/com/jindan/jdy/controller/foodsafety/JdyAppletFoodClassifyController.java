package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodClassify;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodClassifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 食品安全问题分类API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月29日
*
*/

@Api(tags = "食品安全问题分类")
@RestController
@RequestMapping("/jdyAppletFoodClassify")
public class JdyAppletFoodClassifyController{

    @Autowired
    JdyAppletFoodClassifyService jdyAppletFoodSafetyProblemsReultService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "食品安全问题分类", notes = "参数:食品安全问题分类")
    @PostMapping("/seletejdyAppletFoodSafetyProblemsReult")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodClassify jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodClassify> list = jdyAppletFoodSafetyProblemsReultService.seleteList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("jdyAppletFoodClassify") == null){
                List<JdyAppletFoodClassify> list = jdyAppletFoodSafetyProblemsReultService.seleteList(jdyAppletFoodSafetyDto);
                redisUtil.set("jdyAppletFoodClassify",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("jdyAppletFoodClassify"));
            }
        }
    }

    @ApiOperation("更新食品安全问题分类")
    @PostMapping("updatejdyAppletFoodSafetyProblemsReult")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodClassify userPermission){
        boolean b = jdyAppletFoodSafetyProblemsReultService.updateById(userPermission);
        if(b){
            redisUtil.del("jdyAppletFoodClassify");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增食品安全问题分类")
    @PostMapping("addjdyAppletFoodSafetyProblemsReult")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodClassify users){

        boolean save = jdyAppletFoodSafetyProblemsReultService.save(users);
        if(save){
            redisUtil.del("seletejdyAppletFoodSafetyProblemsReult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全问题分类")
    @DeleteMapping("deletejdyAppletFoodSafetyProblemsReult/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyProblemsReultService.removeById(id);
        if(b){
            redisUtil.del("seletejdyAppletFoodSafetyProblemsReult");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}