package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StockGoods;

/**   
 * @Description:TODO(货物表数据访问层)
 *
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
@Mapper
public interface StockGoodsDao extends BaseMapper<StockGoods> {
	
}
