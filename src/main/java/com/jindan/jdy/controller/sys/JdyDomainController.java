package com.jindan.jdy.controller.sys;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyClassroomDto;
import com.jindan.jdy.common.pojo.JdyClassroom;
import com.jindan.jdy.common.pojo.JdyDomain;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.service.sys.JdyClassroomService;
import com.jindan.jdy.service.sys.JdyDomainService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@Api(tags = "二维码验证域名和材料")
@RestController
@RequestMapping("/jdyDomain")
public class JdyDomainController{

    @Autowired
    JdyDomainService jdyDomainService;

    @ApiOperation(value = "域名管理", notes = "参数:域名信息")
    @PostMapping("/seleteJdyDomain")
    public ResultVo seletejdyClassroom(@ApiParam(value = "jdyDomain", required = false)
                                       @RequestBody JdyDomain jdyDomain){
        List<JdyDomain> list = jdyDomainService.list(null);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新域名信息")
    @PostMapping("/updateJdyDomain")
    public ResultVo updatejdyClassroom(@ApiParam(value = "jdyDomain", required = true)
                                       @RequestBody JdyDomain jdyDomain){
        boolean b = jdyDomainService.updateById(jdyDomain);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增域名信息")
    @PostMapping("/addJdyDomain")
    public ResultVo addjdyClassroom( @ApiParam(name = "jdyDomain", required = true)
                                     @RequestBody JdyDomain jdyDomain){
        boolean save = jdyDomainService.saveJdyDomain(jdyDomain);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("域名信息")
    @DeleteMapping("/deleteJdyDomain/{id}")
    public ResultVo deletejdyClassroom(@ApiParam(value = "id", name = "ID", required = true) @PathVariable String  id){
        boolean b = jdyDomainService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}