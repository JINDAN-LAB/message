package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.dto.StockGoodsDTO;
import com.jindan.jdy.common.pojo.StockGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.vo.StockGoodsVO;

import java.util.List;

/**   
 * @Description:TODO(货物表服务层)
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
public interface StockGoodsService extends IService<StockGoods> {

    List<StockGoodsDTO> stockGoodsList(String id);

    void saveStockGoods(StockGoodsVO stockGoodsVO);
}