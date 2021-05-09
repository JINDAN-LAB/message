package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyPurchaseOrder;

/**   
 * @Description:TODO(采购订单信息数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyPurchaseOrderDao extends BaseMapper<JdyPurchaseOrder> {
	
}
