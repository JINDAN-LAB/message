package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTarget;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.waimao.WaimaoTargetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 设备维修申报API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年5月28日
*
*/
@Api(tags = "年销售目标量")
@RestController
@RequestMapping("/waimaoTarget")
public class WaimaoTargetController{

    @Autowired
    WaimaoTargetService waimaoTargetService;

    @ApiOperation(value = "年销售量查询", notes = "参数:年销售量查询")
    @PostMapping("/seleteHuikuan")
    public ResultVo seleteHuikuan(@ApiParam(value = "waimaoHuikuan", required = false)
                                  @RequestBody WaimaoTarget waimaoHuikuan){
        List<WaimaoTarget> list = waimaoTargetService.seletelist(waimaoHuikuan);
        return  ResultVo.success(list);
    }

    @ApiOperation("修改年销售量")
    @PostMapping("/updatehuikuan")
    public ResultVo updatehuikuan(@ApiParam(value = "waimaoHuikuan", required = true)
                                  @RequestBody WaimaoTarget waimaoHuikuan){
        boolean b = waimaoTargetService.updateById(waimaoHuikuan);
        if(b){
            return ResultVo.success(waimaoHuikuan);
        }
        return ResultVo.failed();
    }


    @ApiOperation("新增年销售量")
    @PostMapping("/addhuikuans")
    public ResultVo addhuikuans(@ApiParam(name = "waimaoHuikuan", required = true)
                                @RequestBody WaimaoTarget waimaoHuikuan){
        boolean save = waimaoTargetService.save(waimaoHuikuan);
        if(save){
            return ResultVo.success(waimaoHuikuan);
        }
        return ResultVo.failed();
    }

    @ApiOperation("删除年销售量")
    @DeleteMapping("/deletehuikuan/{id}")
    public ResultVo deletehuikuan(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoTargetService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}