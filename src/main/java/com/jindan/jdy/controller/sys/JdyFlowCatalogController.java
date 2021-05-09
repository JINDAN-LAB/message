package com.jindan.jdy.controller.sys;

import com.jindan.jdy.common.dto.JdyFlowCatalogDto;
import com.jindan.jdy.common.pojo.JdyFlowCatalog;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.controller.utils.RedisUtil;
import com.jindan.jdy.service.sys.JdyFlowCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 流程目录信息API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Api(tags = "流程目录信息")
@RestController
@RequestMapping("/jdyFlowCatalog")
public class JdyFlowCatalogController{

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    JdyFlowCatalogService jdyFlowCatalogService;

    @ApiOperation("流程目录")
    @PostMapping("/seleteJdyFlowCatalog")
    public ResultVo seleteJdyFlowCatalog(@ApiParam(value = "jdyFlowCatalog", required = true)
                                         @RequestBody JdyFlowCatalog jdyFlowCatalog){
            if( redisUtil.get("jdyFlowCatalog") == null){
                List<JdyFlowCatalogDto> list  = jdyFlowCatalogService.queryCatList(jdyFlowCatalog);
                redisUtil.set("jdyFlowCatalog",list);
                return  ResultVo.success(list);
            }else{
                return  ResultVo.success(redisUtil.get("jdyFlowCatalog"));
            }
    }



    @ApiOperation("更新流程信息")
    @PostMapping("/updateJdyRule")
    public ResultVo updatejdyClassroom(@ApiParam(value = "jdyDomain", required = true)
                                       @RequestBody JdyFlowCatalog jdyDomain){

        boolean b = jdyFlowCatalogService.updateById(jdyDomain);
        redisUtil.del("jdyFlowCatalog");
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增供流程信息")
    @PostMapping("addJdySupplier")
    public ResultVo addJdySupplier( @ApiParam(name = "jdySupplier", required = true)
                                    @RequestBody JdyFlowCatalog jdySupplier){
        boolean save = jdyFlowCatalogService.save(jdySupplier);
        redisUtil.del("jdyFlowCatalog");
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除流程")
    @DeleteMapping("/deleteJdyJdyRule/{id}")
    public ResultVo deletejdyClassroom(@ApiParam(value = "id", name = "ID", required = true) @PathVariable String  id){
        boolean b = jdyFlowCatalogService.removeById(id);
        redisUtil.del("jdyFlowCatalog");
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

}