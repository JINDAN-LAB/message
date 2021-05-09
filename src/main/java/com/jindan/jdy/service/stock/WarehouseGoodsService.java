package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.WarehouseGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(仓库管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseGoodsService extends IService<WarehouseGoods> {

    List<WarehouseGoods> seleteWarehouseGoodsService(WarehouseGoods departmentSuggestDto);
}