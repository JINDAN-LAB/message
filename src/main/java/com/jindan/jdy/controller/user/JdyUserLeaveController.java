package com.jindan.jdy.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyUserLeaveDto;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.pojo.JdyUserLeave;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.user.JdyUserLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 人员请假登记API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月18日
*
*/
@Api(tags = "人员请假登记")
@RestController
@RequestMapping("/jdyUserLeave")
public class JdyUserLeaveController{
    
    @Autowired
    JdyUserLeaveService jdyUserLeaveService;

    @ApiOperation(value = "请假登记新增", notes = "参数:请假登记新增")
    @PostMapping("/seletejdyUserfile")
    public ResultVo seleteDepartment( @ApiParam(name = "jdyUserFileDto", required = false)
                                      @RequestBody JdyUserLeaveDto jdyUserFileDto ){
        if(jdyUserFileDto.getCurrentPage() <= 0   || jdyUserFileDto.getPageSize()  <= 0){
            jdyUserFileDto.setCurrentPage(1);
        }
        PageHelper.startPage(jdyUserFileDto.getCurrentPage(), 200);
        List<JdyUserLeave> list = jdyUserLeaveService.seletelist(jdyUserFileDto);
        PageInfo<JdyUserFile> pageInfo = new PageInfo(list, 5);
        return  ResultVo.success(pageInfo) ;
    }

    @ApiOperation("更新请假登记")
    @PostMapping("updatejdyRole")
    public ResultVo updatefacility(@ApiParam(name = "jdyUserFile", required = true)
                                   @RequestBody JdyUserLeave jdyUserFile){
        boolean b = jdyUserLeaveService.updateById(jdyUserFile);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增请假登记")
    @PostMapping("addjdyRole")
    public ResultVo addsubset( @ApiParam(name = "jdyUserFile", required = true)
                               @RequestBody  JdyUserLeave jdyUserFile){
        boolean save = jdyUserLeaveService.save(jdyUserFile);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除请假登记")
    @DeleteMapping("deletejdyRole/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){
        boolean b = jdyUserLeaveService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}