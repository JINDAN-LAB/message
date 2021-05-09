package com.jindan.jdy.controller.flow;

import com.jindan.jdy.common.dto.JdyAppletFoodSafetyDto;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.foodsafety.JdyAppletFoodSafetyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "activiti 流程管理")
@RestController
@RequestMapping("/jdyFlow")
public class JdyProcessManager {

    @Autowired
    JdyAppletFoodSafetyService jdyAppletFoodSafetyService;

    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value = "根据用户权限获取展示信息", notes = "参数:根据用户权限获取展示信息")
    @GetMapping("/seleteDrtment/{jdyAppletFoodSafetyDto}")
    public ResultVo seleteDrtment(@ApiParam(name = "jdyAppletFoodSafetyDto", value = "权限ID", required = true) @PathVariable String  jdyAppletFoodSafetyDto){
        System.out.println("=====================");
        System.out.println(jdyAppletFoodSafetyDto);
        List<JdyAppletFoodSafetyDto> list = jdyAppletFoodSafetyService.seletePersonList(jdyAppletFoodSafetyDto);
        return   ResultVo.success(list);
    }



}
