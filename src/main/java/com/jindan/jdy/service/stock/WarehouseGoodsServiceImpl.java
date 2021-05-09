package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WarehouseGoodsDao;
import com.jindan.jdy.common.pojo.WarehouseGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(仓库管理服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WarehouseGoodsServiceImpl  extends ServiceImpl<WarehouseGoodsDao,WarehouseGoods> implements WarehouseGoodsService  {


    @Autowired
    WarehouseGoodsDao warehouseGoodsDao;

    @Override
    public List<WarehouseGoods> seleteWarehouseGoodsService(WarehouseGoods departmentSuggestDto) {

        QueryWrapper<WarehouseGoods>  queryWrapper =new QueryWrapper<>();

        return warehouseGoodsDao.selectList(queryWrapper);
    }
}