package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.StockDepositoryMapper;
import com.jindan.jdy.common.pojo.StockDepository;
import com.jindan.jdy.common.pojo.StockGoods;
import com.jindan.jdy.common.utils.Constants;
import com.jindan.jdy.common.utils.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Description:TODO(仓库类别服务实现)
 * @version: V 1.0
 * @author: xbdyilin
 */

@Component
public class StockDepositoryServiceImpl extends ServiceImpl<StockDepositoryMapper, StockDepository> implements StockDepositoryService {

    @Autowired
    private StockGoodsService stockGoodsService;

    @Autowired
    private StockDepositoryMapper stockDepositoryMapper;

    @Override
    public List<StockDepository> stockDepositoryList() {
        return stockDepositoryMapper.stockDepositoryList();
    }

    @Override
    public void saveStockDepository(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        Collection<StockDepository> stockDepositories = listByMap(map);
        if (stockDepositories.size() > 0) {
            throw new BusinessException("此名称已存在");
        }
        StockDepository stockDepository = new StockDepository();
        stockDepository.setName(name);
        stockDepository.setParentId(1);
        stockDepository.setCreateTime(new Date());
        stockDepository.setUpdateTime(new Date());
        save(stockDepository);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStockDepository(String name, int id) {
        StockDepository stockDepository = new StockDepository();
        stockDepository.setId(id);
        stockDepository.setName(name);
        updateById(stockDepository);
        StockGoods stockGoods = new StockGoods();
        stockGoods.setDepositoryName(name);
        stockGoodsService.update(stockGoods,
                new QueryWrapper<StockGoods>().eq("depository_id", id));
    }

    @Override
    public void delectStockDepository(int id, int isDelect) {
        List<StockGoods> stockGoods = stockGoodsService.list(new QueryWrapper<StockGoods>()
                .eq("depository_id", id));
        if (stockGoods.size() > 0) {
            throw new BusinessException("此类型下还有货物，不能删除");
        }
        StockDepository stockDepository = new StockDepository();
        stockDepository.setId(id);
        if (Constants.STOCK_ACCESS_STATE_ISDALECT_YES == isDelect) {
            stockDepository.setState(Constants.STOCK_ACCESS_STATE_ISDALECT_YES);
        } else {
            stockDepository.setState(Constants.STOCK_ACCESS_STATE_ISDALECT_NO);
        }
        updateById(stockDepository);
    }
}