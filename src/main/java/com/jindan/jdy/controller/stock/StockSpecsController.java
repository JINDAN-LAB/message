package com.jindan.jdy.controller.stock;

import com.jindan.jdy.common.pojo.StockSpecs;
import com.jindan.jdy.service.stock.StockSpecsService;
import com.jindan.jdy.common.utils.api.ResultVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
/**
*
* <p>说明： 货物规格表API接口层</P>
* @version: V 1.0
* @author: xbdyilin
* @time    2020年4月5日18:51:48
*
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "货物规格表")
@RestController
@RequestMapping("/stockSpecs")
public class StockSpecsController{


}

