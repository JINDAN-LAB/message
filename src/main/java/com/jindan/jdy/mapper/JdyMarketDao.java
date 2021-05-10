package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.JdyMarket;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(销售表头信息数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyMarketDao extends BaseMapper<JdyMarket> {
	
}
