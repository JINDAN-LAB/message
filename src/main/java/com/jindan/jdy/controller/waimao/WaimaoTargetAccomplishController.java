package com.jindan.jdy.controller.waimao;

import com.jindan.jdy.common.pojo.WaimaoTargetAccomplish;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.waimao.WaimaoTargetAccomplishService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
*
* <p>说明： 外贸目标完成API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年10月14日
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
//@Api("外贸目标完成")
@RestController
@RequestMapping("/转成汇率")
public class WaimaoTargetAccomplishController{

    @Autowired
    WaimaoTargetAccomplishService waimaoHuikuanService;

    @ApiOperation(value = "年汇率查询", notes = "参数:年汇率查询")
    @PostMapping("/seleteNianHuilv")
    public ResultVo seleteNianHuilv(@ApiParam(value = "waimaoTargetAccomplish", required = false)
                                  @RequestBody WaimaoTargetAccomplish waimaoTargetAccomplish){
        List<WaimaoTargetAccomplish> list = waimaoHuikuanService.seletelist(waimaoTargetAccomplish);
        return  ResultVo.success(list);
    }

    @ApiOperation("更新年汇率")
    @PostMapping("/updateNianhuilv")
    public ResultVo updateWaimaoTargetAccomplish(@ApiParam(value = "waimaoTargetAccomplish", required = true)
                                  @RequestBody WaimaoTargetAccomplish waimaoTargetAccomplish){
        boolean b = waimaoHuikuanService.updateById(waimaoTargetAccomplish);
        if(b){
            return ResultVo.success(waimaoTargetAccomplish);
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增年汇率")
    @PostMapping("/addNianhuilv")
    public ResultVo addhuikuans(@ApiParam(name = "waimaoTargetAccomplish", required = true)
                                @RequestBody WaimaoTargetAccomplish waimaoTargetAccomplish){
        boolean save = waimaoHuikuanService.save(waimaoTargetAccomplish);
        if(save){
            return ResultVo.success(waimaoTargetAccomplish);
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除年汇率")
    @DeleteMapping("/deleteNianhuilv/{id}")
    public ResultVo deletehuikuan(@ApiParam(value = "id", name = "角色ID", required = true) @PathVariable String  id){
        boolean b = waimaoHuikuanService.removeById(id);
        if(b){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}