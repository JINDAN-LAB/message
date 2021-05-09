package com.jindan.jdy.controller.purchase;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.JdyPutDto;
import com.jindan.jdy.common.dto.JdyPutTypeDetails;
import com.jindan.jdy.common.pojo.JdyPurchaseArriveTest;
import com.jindan.jdy.common.pojo.JdyPut;
import com.jindan.jdy.service.purchase.JdyPurchaseArriveTestService;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.service.purchase.JdyPutService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import java.util.List;

/**
*
* <p>说明： 化验室委托单API接口层</P>
* @version: V1.0
* @author: kong
* @time    2020年12月29日
*
*/
@Api(tags = "化验室委托单")
@RestController
@RequestMapping("/jdyPurchaseArriveTest")
public class JdyPurchaseArriveTestController{

    @Autowired
    JdyPurchaseArriveTestService jdyPutService;


    @ApiOperation(value = "化验室委托单查询", notes = "参数:化验室委托单信息")
    @PostMapping("/seleteJdyPurchase")
    public ResultVo seleteDepartment(@ApiParam(name = "jdyPurchaseDto", required = false)
                                     @RequestBody JdyPurchaseArriveTest jdyPurchaseDto){

        List<JdyPurchaseArriveTest> list = jdyPutService.seletelist(jdyPurchaseDto);
        return ResultVo.success(list);
    }

    @ApiOperation("更新化验室委托单")
    @PostMapping("updatejdyPurchase")
    public ResultVo updatejdyPurchase(@ApiParam(name = "jdyPurchase", required = true)
                                      @RequestBody JdyPurchaseArriveTest JdyPut){
        boolean save = jdyPutService.updateById(JdyPut);
        if(save){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }

    @ApiOperation("新增化验室委托单")
    @PostMapping("addJdyPurchase")
    public ResultVo addsubset(@ApiParam(name = "jdyPurchaseDto", required = true)
                              @RequestBody JdyPurchaseArriveTest  jdyPurchaseDto){
        boolean save1 = jdyPutService.insertSave(jdyPurchaseDto);
        if(save1){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


    @ApiOperation("删除化验室委托单")
    @DeleteMapping("deleteJdyCommodity/{commodityId}")
    public ResultVo deletejdyCommodity(@ApiParam(name = "commodityId", value = "删除ID", required = true) @PathVariable String  commodityId){
        boolean remove = jdyPutService.removeById(commodityId);
        if(remove){
            return ResultVo.success();
        }
        return ResultVo.failed();
    }


}