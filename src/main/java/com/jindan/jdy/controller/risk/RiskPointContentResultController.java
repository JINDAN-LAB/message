package com.jindan.jdy.controller.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.DepartmentFacilityDto;
import com.jindan.jdy.common.dto.RiskPointContentResultDto;
import com.jindan.jdy.common.dto.RiskPointContentResultGongju;
import com.jindan.jdy.common.dto.RiskPointDetails;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.jindan.jdy.common.vo.PageVO;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.service.risk.RiskPointContentResultService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.risk.RiskPointContentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.lang.reflect.Field;
import java.util.List;

/**
*
* <p>说明： 风险控制点内容返回值API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月8日
*
*/
@Api(tags ="风险控制点内容返回值")
@RestController
@RequestMapping("/riskPointContentResult")
public class RiskPointContentResultController{

    @Autowired
    RiskPointContentResultService riskPointContentService;

    @Autowired
    RiskPointContentService riskContentService;

    public boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        boolean flag = false;
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.get(obj) == null){
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    @ApiOperation(value = "已巡检查询", notes = "参数:已巡检查询")
    @PostMapping("/seleteRiskResult")
    public ResultVo seleteRiskResult(@ApiParam(name = "riskPointContent", required = false)
                                           @RequestBody RiskPointContentResult riskPointContent) throws Exception {
        List<RiskPointDetails> list = riskPointContentService.seleListDetailsWapper(riskPointContent);
        return  ResultVo.success(list);
    }


    @ApiOperation(value = "风险控制点内容返回值查询", notes = "参数:风险控制点内容查询")
    @PostMapping("/seleteRiskPointContentResult")
    public ResultVo seleteRiskPointContentResult(@ApiParam(name = "riskPointContent", required = false)
                                                 @RequestBody RiskPointContentResult riskPointContent){
        List<RiskPointContentResult> list = riskPointContentService.seleListWapper(riskPointContent);
        return  ResultVo.success(list);
    }


    @ApiOperation(value = "风险控制点内容带分页", notes = "参数:风险控制点带分页")
    @PostMapping("/seleteRiskPointContentFenye")
    public ResultVo seleteRiskPointContentFenye(@ApiParam(name = "riskPointContent", required = false)
                                                 @RequestBody RiskPointContentResultDto riskPointContent){
        Page<RiskPointContentResult> list = riskPointContentService.seleFenyeListWapper(riskPointContent);
       return  ResultVo.success(list);
    }


    @ApiOperation("更新风险控制点内容返回值")
    @PostMapping("updateRiskPointContentResult")
    public ResultVo updateRiskPointContentResult(@ApiParam(name = "userPermission", required = true)
                                                 @RequestBody RiskPointContentResult userPermission){
        boolean b = riskPointContentService.updateById(userPermission);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增风险控制点内容返回值")
    @PostMapping("addRiskPointContentResult")
    public ResultVo addRiskPointContentResult(@ApiParam(name = "userPermission", required = true)
                                              @RequestBody RiskPointContentResult  userPermission){
        boolean save = riskPointContentService.save(userPermission);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除风险控制点内容返回值")
    @DeleteMapping("deleteRiskPointContentResult/{id}")
    public ResultVo deleteRiskPointContentResult(@ApiParam(name = "id", value = "权限ID", required = true) @PathVariable String  id){
        boolean b = riskPointContentService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }



}