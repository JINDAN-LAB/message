package com.jindan.jdy.controller.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.MaintainFacilityDto;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.pojo.MaintainFacility;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.MaintainFacilityService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.department.MaintainReportService;
import com.jindan.jdy.service.user.JdyRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;


@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "设备维修信息")
@RestController
@RequestMapping("/maintainFacility")
public class MaintainFacilityController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MaintainFacilityService maintainFacilityService;

    @Autowired
    MaintainReportService maintainReportService;

    @ApiOperation(value = "设备维修查询", notes = "参数:角色信息")
    @PostMapping("/seleteMaintainFacility")
    public ResultVo seleteDepartment( @ApiParam(name = "maintainFacility", required = false)
                                      @RequestBody MaintainFacilityDto maintainFacilityDto ){
        PageHelper.startPage(maintainFacilityDto.getCurrentPage(), maintainFacilityDto.getPageSize());
        List<MaintainFacility> list = maintainFacilityService.seletelist(maintainFacilityDto);
        PageInfo<JdyUserFile> pageInfo = new PageInfo(list, 5);
        return  ResultVo.success(pageInfo) ;
    }

    @ApiOperation("更新维修信息")
    @PostMapping("updateMaintainFacility")
    public ResultVo updatefacility(@ApiParam(name = "maintainFacility", required = true)
                                   @RequestBody MaintainFacility maintainFacility){
        boolean b = maintainFacilityService.updateById(maintainFacility);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增维修信息")
    @PostMapping("addMaintainFacility")
    public ResultVo addsubset(@ApiParam(name = "maintainFacility", required = true)
                              @RequestBody MaintainFacility maintainFacility){
        boolean save = maintainFacilityService.save(maintainFacility);
        maintainReportService.removeById(maintainFacility.getDepartFacilityId());
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除维修信息")
    @DeleteMapping("deleteMaintainFacility/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){
        boolean b = maintainFacilityService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}