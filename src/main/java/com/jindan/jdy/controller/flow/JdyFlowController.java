package com.jindan.jdy.controller.flow;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.JdyFlowDto;
import com.jindan.jdy.common.dto.KeyPointPracticableDto;
import com.jindan.jdy.common.pojo.JdyFlow;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
import com.jindan.jdy.service.flow.JdyFlowService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.keypoint.KeyPointPracticableService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 规则API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年4月20日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "领导审批查询")
@RestController
@RequestMapping("/jdyFlow")
public class JdyFlowController{

    @Autowired
    JdyFlowService jdyFlowService;

    @ApiOperation(value = "审批查询获取人员信息", notes = "参数:审批查询获取人员信息")
    @PostMapping("/seleteflowService")
    public ResultVo seleteflowService(@ApiParam(name = "departmentSuggestDto", required = false)
                                      @RequestBody JdyFlowDto departmentSuggestDto ){
        List<JdyFlowDto> list = jdyFlowService.seleteJdyFlowperson(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation(value = "审批查询", notes = "参数:审批查询")
    @PostMapping("/seletekeyPointPracticableService")
    public ResultVo seleteDepartment(@ApiParam(name = "departmentSuggestDto", required = false)
                                     @RequestBody JdyFlowDto departmentSuggestDto ){
        List<JdyFlow> list = jdyFlowService.seleteJdyFlowDto(departmentSuggestDto);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新建议信息")
    @PostMapping("updatekeyPointPracticableService")
    public ResultVo updatekeyPointPracticableService(@ApiParam(name = "userPermission", required = true)
                                                     @RequestBody JdyFlow userPermission){
        boolean b = jdyFlowService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增信息")
    @PostMapping("addkeyPointPracticableService")
    public ResultVo addkeyPointPracticableService( @ApiParam(name = "userPermission", required = true)
                                                   @RequestBody  JdyFlow userPermission){
        boolean save = jdyFlowService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除信息")
    @DeleteMapping("deletekeyPointPracticableService/{id}")
    public ResultVo deletekeyPointPracticableService(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = jdyFlowService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }









}