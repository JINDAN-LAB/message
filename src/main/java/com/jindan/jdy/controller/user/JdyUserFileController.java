package com.jindan.jdy.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyUserFileDto;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.JdyUserFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "文件管理")
@RestController
@RequestMapping("/jdyUserFile")
public class JdyUserFileController{

    @Autowired
    JdyUserFileService jdyUserFileService;

    @ApiOperation(value = "查询文件管理", notes = "参数:文件信息")
    @PostMapping("/seletejdyUserfile")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyUserFileDto", required = false)
                                      @RequestBody JdyUserFileDto jdyUserFileDto ){
        if(jdyUserFileDto.getCurrentPage() <= 0   || jdyUserFileDto.getPageSize()  <= 0){
            jdyUserFileDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyUserFileDto.getCurrentPage(), 200);
        List<JdyUserFile> list = jdyUserFileService.seletelist(jdyUserFileDto);
        PageInfo<JdyUserFile> pageInfo = new PageInfo(list, 5);
        return  ResultVo.success(pageInfo) ;
    }

    @ApiOperation("更新档案信息")
    @PostMapping("updatejdyRole")
    public ResultVo updatefacility(@ApiParam(name = "jdyUserFile", required = true)
                                   @RequestBody JdyUserFile jdyUserFile){
        boolean b = jdyUserFileService.updateById(jdyUserFile);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增文档信息")
    @PostMapping("addjdyRole")
    public ResultVo addsubset( @ApiParam(name = "jdyUserFile", required = true)
                               @RequestBody  JdyUserFile jdyUserFile){
        boolean save = jdyUserFileService.save(jdyUserFile);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除文档信息")
    @DeleteMapping("deletejdyRole/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){
        boolean b = jdyUserFileService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}