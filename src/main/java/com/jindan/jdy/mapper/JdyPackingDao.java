package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.JdyPacking;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(盘点单数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyPackingDao extends BaseMapper<JdyPacking> {
	
}
