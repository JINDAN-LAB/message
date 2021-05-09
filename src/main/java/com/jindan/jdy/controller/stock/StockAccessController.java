package com.jindan.jdy.controller.stock;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.pojo.StockAccess;
import com.jindan.jdy.common.utils.api.ResultVo;
import com.jindan.jdy.common.vo.StockAccessListVO;
import com.jindan.jdy.common.vo.StockAccessSaveVO;
import com.jindan.jdy.service.stock.StockAccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>说明： 进入库API接口层</P>
 *
 * @version: V 1.0
 * @author: xbdyilin
 * @time 2020年4月5日18:51:48
 */
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "进出库")
@RestController
@RequestMapping("/stockAccess")
@Slf4j
public class StockAccessController {

    @Autowired
    private StockAccessService stockAccessService;

    @GetMapping("list")
    @ApiOperation("进出库列表")
    public ResultVo<PageInfo<StockAccess>> list(@Valid StockAccessListVO stockAccessListVO) {
        PageInfo<StockAccess> pageInfo = stockAccessService.StockAccessList(stockAccessListVO);
        return ResultVo.success(pageInfo);
    }

    @PostMapping("save")
    @ApiOperation("入库单/出库单")
    public ResultVo save(@Valid StockAccessSaveVO stockAccessSaveVO) throws InterruptedException {
        stockAccessService.saveStockAccess(stockAccessSaveVO);
        return ResultVo.success();
    }



}