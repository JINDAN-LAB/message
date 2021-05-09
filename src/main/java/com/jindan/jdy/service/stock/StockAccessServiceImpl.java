package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.pojo.StockAccess;
import com.jindan.jdy.common.mapper.StockAccessDao;
import com.jindan.jdy.common.pojo.StockGoods;
import com.jindan.jdy.common.pojo.StockSpecs;
import com.jindan.jdy.common.utils.Constants;
import com.jindan.jdy.common.utils.ConvertUtils;
import com.jindan.jdy.common.utils.exception.BusinessException;
import com.jindan.jdy.common.vo.StockAccessListVO;
import com.jindan.jdy.common.vo.StockAccessSaveVO;
import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Description:TODO(进入库服务实现)
 * @version: V 1.0
 * @author: xbdyilin
 */

@Component
//@Slf4j
public class StockAccessServiceImpl extends ServiceImpl<StockAccessDao, StockAccess> implements StockAccessService {

    @Autowired
    private StockSpecsService stockSpecsService;

    @Autowired
    private StockGoodsService stockGoodsService;

    @Autowired
    private RedisLockRegistry redisLockRegistry;

    @Override
    public PageInfo<StockAccess> StockAccessList(StockAccessListVO stockAccessListVO) {
        List<StockAccess> stockAccesses = baseMapper.selectList(new QueryWrapper<StockAccess>()
                .eq("type", stockAccessListVO.getType())
                .like(StringUtils.isNotBlank(stockAccessListVO.getName()), "name", stockAccessListVO.getName()));
        return new PageInfo<>(stockAccesses);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStockAccess(StockAccessSaveVO stockAccessSaveVO) throws InterruptedException {
        String lockKey = stockAccessSaveVO.getSpecsId() + "stockAccessDepaId";
        Lock lock = redisLockRegistry.obtain(lockKey);
        if (!lock.tryLock(3, TimeUnit.SECONDS)) {
            throw new BusinessException("获取执行锁失败！");
        }
        try {
            StockSpecs stockSpecs = stockSpecsService.getById(stockAccessSaveVO.getSpecsId());
            if (null == stockSpecs) {
                throw new BusinessException("此规格不存在");
            }
            StockGoods stockGoods = stockGoodsService.getById(stockSpecs.getGoodsId());
            if (null == stockGoods) {
                throw new BusinessException("此货物不存在");
            }
            StockAccess stockAccess = ConvertUtils.sourceToTarget(stockAccessSaveVO, StockAccess.class);
            stockAccess.setGoodsId(stockGoods.getId());
            stockAccess.setGoodsName(stockGoods.getName());
            Date date = new Date();
            stockAccess.setCreateTime(date);
            stockAccess.setUpdateTime(date);
            save(stockAccess);
            stockHandle(date, stockAccessSaveVO, stockSpecs);
        } catch (BusinessException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.unlock();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }

    //库存操作
    private void stockHandle(Date date, StockAccessSaveVO stockAccessSaveVO, StockSpecs stockSpecs) {
        if (Constants.STOCK_ACCESS_TYPE_WAREHOUSING == stockAccessSaveVO.getType()) {
            stockSpecs.setNumber(stockSpecs.getNumber() + stockAccessSaveVO.getNumber());
            stockSpecs.setUpdateTime(date);
            stockSpecsService.updateById(stockSpecs);
        } else if (Constants.STOCK_ACCESS_TYPE_RETRIEVAL == stockAccessSaveVO.getType()) {
            if (stockAccessSaveVO.getNumber() > stockSpecs.getNumber()) {
                throw new BusinessException("处理数量不能大于库存数量");
            }
            stockSpecs.setNumber(stockSpecs.getNumber() - stockAccessSaveVO.getNumber());
            stockSpecs.setUpdateTime(date);
            stockSpecsService.updateById(stockSpecs);
        }
    }

}