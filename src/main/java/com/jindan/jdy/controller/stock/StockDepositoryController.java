package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.pojo.StockDepository;
import com.jindan.jdy.common.utils.exception.BusinessException;
import com.jindan.jdy.service.stock.StockDepositoryService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * <p>说明： 仓库类别API接口层</P>
 *
 * @version: V 1.0
 * @author: xbdyilin
 * @time 2020年4月5日18:51:48
 */
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "仓库类别")
@RestController
@RequestMapping("/stockDepository")
public class StockDepositoryController {

    @Autowired
    private StockDepositoryService stockDepositoryService;

    @GetMapping("list")
    @ApiOperation("仓库类别列表")
    public ResultVo<List<StockDepository>> list() {
        List<StockDepository> stockDepositories = stockDepositoryService.stockDepositoryList();
        return ResultVo.success(stockDepositories);
    }

    @PostMapping("save")
    @ApiOperation("仓库类别添加")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别名称", name = "name", type = "String", paramType = "query", required = true),
    })
    public ResultVo save(@NotBlank(message = "类别名称不能为空") String name) {
        stockDepositoryService.saveStockDepository(name);
        return ResultVo.success();
    }

    @PostMapping("update")
    @ApiOperation("仓库类别修改")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别名称", name = "name", type = "String", paramType = "query", required = true),
            @ApiImplicitParam(value = "id", name = "id", type = "int", paramType = "query", required = true),
    })
    public ResultVo update(@NotBlank(message = "类别名称不能为空") String name
            , @NotNull(message = "id不能为空") int id) {
        stockDepositoryService.updateStockDepository(name, id);
        return ResultVo.success();
    }

    @PostMapping("delect")
    @ApiOperation("仓库类别删除")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", type = "int", paramType = "query", required = true),
            @ApiImplicitParam(value = "是否删除（状态： 0删除  1正常） ", name = "isDelect", type = "int", paramType = "query", required = true),
    })
    public ResultVo delect(@NotNull(message = "id不能为空") int id,
                           @NotNull(message = "isDelect不能为空") int isDelect) {
        stockDepositoryService.delectStockDepository(id, isDelect);
        return ResultVo.success();
    }


}