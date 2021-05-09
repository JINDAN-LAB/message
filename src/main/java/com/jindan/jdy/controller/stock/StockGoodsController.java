package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.dto.StockGoodsDTO;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.common.vo.StockGoodsVO;
import com.jindan.jdy.service.stock.StockGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>说明： 货物表API接口层</P>
 *
 * @version: V 1.0
 * @author: xbdyilin
 * @time 2020年4月5日18:51:48
 */
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "货物表")
@RestController
@RequestMapping("/stockGoods")
public class StockGoodsController {

    @Autowired
    private StockGoodsService stockGoodsService;

    @GetMapping("stockgoodslist")
    @ApiOperation("根据分类id查询货物")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "类别id", name = "id", type = "String",paramType = "query", required = true),
    })
    public ResultVo<List<StockGoodsDTO>> stockGoodsList(String id) {
        List<StockGoodsDTO> stockGoodsDTOS = stockGoodsService.stockGoodsList(id);
        return ResultVo.success(stockGoodsDTOS);
    }

    @PostMapping("save")
    @ApiOperation("货物添加")
    public ResultVo<String> save(@Valid StockGoodsVO stockGoodsVO) {
        stockGoodsService.saveStockGoods(stockGoodsVO);
        return ResultVo.success();
    }


}