package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.StockSpecs;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(货物规格表数据访问层)
 *
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
@Mapper
public interface StockSpecsDao extends BaseMapper<StockSpecs> {
	
}
