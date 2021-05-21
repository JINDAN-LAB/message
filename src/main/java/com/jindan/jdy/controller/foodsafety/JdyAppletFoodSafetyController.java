package com.jindan.jdy.controller.foodsafety;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
*
* <p>说明： 食品安全小程序API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年7月27日
*
*/

@Slf4j
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "食品安全")
@RestController
@RequestMapping("/jdyAppletFoodSafety")
public class JdyAppletFoodSafetyController{

    @Autowired
    JdyAppletFoodSafetyService jdyAppletFoodSafetyService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "根据用户权限获取展示信息", notes = "参数:根据用户权限获取展示信息")
    @GetMapping("/seleteDrtment/{jdyAppletFoodSafetyDto}")
    public ResultVo seleteDrtment(@ApiParam(name = "jdyAppletFoodSafetyDto", value = "权限ID", required = true)
                                      @PathVariable String  jdyAppletFoodSafetyDto){
        log.info("======“根据用户权限获取展示信息接口”开始执行======");
        log.info("jdyAppletFoodSafetyDto的值为："+jdyAppletFoodSafetyDto);
        List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seletePersonList(jdyAppletFoodSafetyDto);
        return   ResultVo.success(list);
    }

    @ApiOperation(value = "小程序根据用户权限获取展示信息", notes = "参数:小程序根据用户权限获取展示信息")
    @GetMapping("/seleteXiaoDrtment/{jdyAppletFoodSafetyDto}")
    public ResultVo seleteXiaoDrtment(@ApiParam(name = "jdyAppletFoodSafetyDto", value = "权限ID", required = true) @PathVariable String  jdyAppletFoodSafetyDto){

        List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seletePersonList(jdyAppletFoodSafetyDto);
        List<JdyAppletFoodSafetyDto> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j <list.get(i).getChildsList().size() ; j++) {
                if(list.get(i).getChildsList().get(j) != null){
                    list1.add(list.get(i).getChildsList().get(j));
                }
            }
            list1.add(list.get(i));
        }
      return   ResultVo.success(list1);
    }

    @ApiOperation(value = "车间履职信息", notes = "参数:车间履职信息")
    @GetMapping("/seleteChejianDrtment/{pwd}")
    public ResultVo seleteChejianDrtment(@ApiParam(name = "pwd", value = "用户账号", required = true) @PathVariable String  pwd){
        List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seleChejianProblems(pwd);
        return   ResultVo.success(list);
    }


    @ApiOperation(value = "获取所有车间信息", notes = "参数:获取所有车间信息")
    @PostMapping("/seleteAllChejianDrtment/")
    public ResultVo seleteAllChejianDrtment(@ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                            @RequestBody JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto){
        List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seleteAllChejianDrtment(jdyAppletFoodSafetyDto);
        return   ResultVo.success(list);
    }

    @ApiOperation(value = "食品安全项目目录", notes = "参数:食品安全项目目录")
    @PostMapping("/seletekeyPoJdyAppletFoodSafety")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto){
        if(jdyAppletFoodSafetyDto != null){
            List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seleteList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
        }else{
            if( redisUtil.get("seletekeyPoJdyAppletFoodSafety") == null){
                List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seleteList(jdyAppletFoodSafetyDto);
                redisUtil.set("seletekeyPoJdyAppletFoodSafety",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("seletekeyPoJdyAppletFoodSafety"));
            }
        }
    }


    @ApiOperation(value = "获取所有食品安全项目目录", notes = "参数:获取所有食品安全项目目录")
    @PostMapping("/seleteAllPoJdyAppletFoodSafety")
    public ResultVo seleteAllPoJdyAppletFoodSafety( @ApiParam(name = "jdyAppletFoodSafetyDto", required = false)
                                      @RequestBody JdyAppletFoodSafetyDto jdyAppletFoodSafetyDto){
            List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seleteAllList(jdyAppletFoodSafetyDto);
            return  ResultVo.success(list);
    }


    @ApiOperation("更新食品安全")
    @PostMapping("updatekeyPoJdyAppletFoodSafety")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyAppletFoodSafety userPermission){
        boolean b = jdyAppletFoodSafetyService.updateById(userPermission);
        if(b){
            redisUtil.del("seletekeyPoJdyAppletFoodSafety");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增食品安全")
    @PostMapping("addkeyPoJdyAppletFoodSafety")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "users", required = true)
                                                   @RequestBody  JdyAppletFoodSafety users){
        boolean save = jdyAppletFoodSafetyService.insertsave(users);
        if(save){
            redisUtil.del("seletekeyPoJdyAppletFoodSafety");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除食品安全")
    @DeleteMapping("deletekeyPoJdyAppletFoodSafety/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyAppletFoodSafetyService.removeById(id);
        if(b){
            redisUtil.del("seletekeyPoJdyAppletFoodSafety");
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}