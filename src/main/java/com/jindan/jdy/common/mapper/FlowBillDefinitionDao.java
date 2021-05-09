package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.FlowBillDefinition;

/**   
 * @Description:TODO(流程定义表数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface FlowBillDefinitionDao extends BaseMapper<FlowBillDefinition> {
	
}
