package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.pojo.JdyAppletFoodSafetyMajordomo;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyMajordomoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 食品安全总监管理API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月29日
*
*/
@Api(tags = "食品安全总监管理")
@RestController
@RequestMapping("/jdyAppletFoodSafetyMajordomo")
public class JdyAppletFoodSafetyMajordomoController{

    @Autowired
    JdyAppletFoodSafetyMajordomoService jdyAppletFoodSafetyService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "获取所有总监目录", notes = "参数:获取所有总监目录")
    @PostMapping("/seleteMajordomo")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyMajordomo jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodSafetyMajordomo> list = jdyAppletFoodSafetyService.seleteList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("jdyAppletFoodSafetyMajordomo") == null){
                List<JdyAppletFoodSafetyMajordomo> list = jdyAppletFoodSafetyService.seleteList(jdyAppletFoodSafetyDto);
                redisUtil.set("jdyAppletFoodSafetyMajordomo",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("jdyAppletFoodSafetyMajordomo"));
            }
        }
    }

    @ApiOperation("更新总监目录")
    @PostMapping("updateMajordomo")
    public ResultVo updateMajordomo(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodSafetyMajordomo userPermission){
        boolean b = jdyAppletFoodSafetyService.updateById(userPermission);
        if(b){
            redisUtil.del("jdyAppletFoodSafetyMajordomo");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增总监目录")
    @PostMapping("addMajordomo")
    public ResultVo addMajordomoService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodSafetyMajordomo users){
        boolean save = jdyAppletFoodSafetyService.insertsave(users);
        if(save){
            redisUtil.del("jdyAppletFoodSafetyMajordomo");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全")
    @DeleteMapping("deletekeyPoJdyAppletFoodSafety/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyService.removeById(id);
        if(b){
            redisUtil.del("jdyAppletFoodSafetyMajordomo");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}