package com.jindan.jdy.controller.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.MaintainFacilityDto;
import com.jindan.jdy.common.dto.MaintainReportDto;
import com.jindan.jdy.common.pojo.JdySsp;
import com.jindan.jdy.common.pojo.JdyUserFile;
import com.jindan.jdy.common.pojo.MaintainFacility;
import com.jindan.jdy.common.pojo.MaintainReport;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.department.MaintainFacilityService;
import com.jindan.jdy.service.department.MaintainReportService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 设备维修申报API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Api(tags = "设备维修申报")
@RestController
@RequestMapping("/maintainReport")
public class MaintainReportController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    MaintainReportService maintainReportService;

    @ApiOperation(value = "设备申报查询", notes = "参数:角色信息")
    @PostMapping("/maintainReportService")
    public ResultVo maintainReportService( @ApiParam(name = "maintainFacilityDto", required = false)
                                      @RequestBody MaintainReportDto maintainFacilityDto ){
        if(maintainFacilityDto.getMpid() != null){
            if(redisUtil.get(maintainFacilityDto.getMpid()) == null){
                List<MaintainReport> list = maintainReportService.seletelist(maintainFacilityDto);
                redisUtil.set(maintainFacilityDto.getMpid(),list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get(maintainFacilityDto.getMpid()));
            }
        }else{
            List<MaintainReport> list = maintainReportService.seletelist(maintainFacilityDto);
            return  ResultVo.success(list) ;
        }
    }

    @ApiOperation(value = "设备申报与设备联查", notes = "参数:设备申报与设备联查")
    @PostMapping("/maintainAllReportService")
    public ResultVo maintainAllReportService( @ApiParam(name = "maintainFacilityDto", required = false)
                                           @RequestBody MaintainReportDto maintainFacilityDto ){
        if(redisUtil.get(maintainFacilityDto.getMpid()+"a123") == null){
            List<MaintainReport> list = maintainReportService.seleteAlllist(maintainFacilityDto);
            redisUtil.set(maintainFacilityDto.getMpid()+"a123",list);
            return  ResultVo.success(list);
        }else{
            return  ResultVo.success(redisUtil.get(maintainFacilityDto.getMpid()+"a123"));
        }
//        List<MaintainReport> list = maintainReportService.seleteAlllist(maintainFacilityDto);
//        return  ResultVo.success(list) ;
    }

    @ApiOperation("更新维修信息")
    @PostMapping("updateMaintainReport")
    public ResultVo updatefacility(@ApiParam(name = "maintainFacility", required = true)
                                   @RequestBody MaintainReport maintainFacility){
        boolean b = maintainReportService.updateById(maintainFacility);
        if(b){
            redisUtil.del(maintainFacility.getMpid()+"a123");
            redisUtil.del(maintainFacility.getMpid());
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增维修信息")
    @PostMapping("addMaintainReport")
    public ResultVo addsubset(@ApiParam(name = "maintainFacility", required = true)
                              @RequestBody MaintainReport maintainFacility){
        boolean save = maintainReportService.save(maintainFacility);
        if(save){
            redisUtil.del(maintainFacility.getMpid()+"a123");
            redisUtil.del(maintainFacility.getMpid());
            return ResultVo.success();
        }
        return ResultVo.failed();
     }
        
    @ApiOperation("删除维修信息")
    @DeleteMapping("deleteMaintainReport/{id}")
    public ResultVo deletefacility(@ApiParam(name = "id", value = "角色ID", required = true) @PathVariable String  id){
        boolean b = maintainReportService.removeById(id);
        if(b){
            redisUtil.del(id+"a123");
            redisUtil.get(id);
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}