package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.StockGoodsDTO;
import com.jindan.jdy.mapper.StockGoodsDao;
import com.jindan.jdy.common.pojo.StockGoods;
import com.jindan.jdy.common.pojo.StockSpecs;
import com.jindan.jdy.common.utils.ConvertUtils;
import com.jindan.jdy.common.vo.StockGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:TODO(货物表服务实现)
 * @version: V 1.0
 * @author: xbdyilin
 */

@Component
public class StockGoodsServiceImpl extends ServiceImpl<StockGoodsDao, StockGoods> implements StockGoodsService {

    @Autowired
    private StockSpecsService stockSpecsService;

    @Override
    public List<StockGoodsDTO> stockGoodsList(String id) {
        List<StockGoods> stockGoods = baseMapper.selectList(new QueryWrapper<StockGoods>()
                .eq("depository_id", id));
        List<StockGoodsDTO> stockGoodsDTOS = ConvertUtils.sourceToTarget(stockGoods, StockGoodsDTO.class);
        stockGoodsDTOS.forEach(param -> {
            StockSpecs stockSpecs = stockSpecsService.getById(param.getId());
            param.setStockSpecs(stockSpecs);
        });
        return stockGoodsDTOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStockGoods(StockGoodsVO stockGoodsVO) {
        StockGoods stockGoods = new StockGoods();
        stockGoods.setDepositoryId(stockGoodsVO.getDepositoryId());
        stockGoods.setDepositoryName(stockGoodsVO.getDepositoryName());
        stockGoods.setName(stockGoodsVO.getName());
        stockGoods.setRemarks(stockGoodsVO.getRemarks());
        stockGoods.setCreateTime(new Date());
        stockGoods.setUpdateTime(new Date());
        int id = baseMapper.insert(stockGoods);
        StockSpecs stockSpecs = new StockSpecs();
        stockSpecs.setGoodsId(id);
        stockSpecs.setBrand(stockGoodsVO.getBrand());
        stockSpecs.setModel(stockGoodsVO.getModel());
        stockSpecs.setUnit(stockGoodsVO.getUnit());
        stockSpecs.setPrice(stockGoodsVO.getPrice());
        stockSpecs.setCreateTime(new Date());
        stockSpecs.setUpdateTime(new Date());
        save(stockGoods);
    }
}