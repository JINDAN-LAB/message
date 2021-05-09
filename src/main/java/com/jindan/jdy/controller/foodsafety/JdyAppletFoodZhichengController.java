package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodZhicheng;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodZhichengService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 食品安全人员职称API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月29日
*
*/
@Api(tags = "食品安全人员职称")
@RestController
@RequestMapping("/jdyAppletFoodZhicheng")
public class JdyAppletFoodZhichengController{

    @Autowired
    JdyAppletFoodZhichengService jdyAppletFoodSafetyProblemsReultService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "食品安全问题分类", notes = "参数:食品安全问题分类")
    @PostMapping("/seletejdyAppletFoodSafetyProblemsReult")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodZhicheng jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodZhicheng> list = jdyAppletFoodSafetyProblemsReultService.seleteList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("jdyAppletFoodZhicheng") == null){
                List<JdyAppletFoodZhicheng> list = jdyAppletFoodSafetyProblemsReultService.seleteList(jdyAppletFoodSafetyDto);
                redisUtil.set("jdyAppletFoodZhicheng",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("jdyAppletFoodZhicheng"));
            }
        }
    }

    @ApiOperation("更新食品安全问题分类")
    @PostMapping("updatejdyAppletFoodSafetyProblemsReult")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodZhicheng userPermission){
        boolean b = jdyAppletFoodSafetyProblemsReultService.updateById(userPermission);
        if(b){
            redisUtil.del("jdyAppletFoodZhicheng");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增食品安全问题分类")
    @PostMapping("addjdyAppletFoodSafetyProblemsReult")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodZhicheng users){

        boolean save = jdyAppletFoodSafetyProblemsReultService.save(users);
        if(save){
            redisUtil.del("jdyAppletFoodZhicheng");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全问题分类")
    @DeleteMapping("deletejdyAppletFoodSafetyProblemsReult/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyProblemsReultService.removeById(id);
        if(b){
            redisUtil.del("jdyAppletFoodZhicheng");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}