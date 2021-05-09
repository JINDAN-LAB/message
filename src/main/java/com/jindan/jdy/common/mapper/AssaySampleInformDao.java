package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.AssaySampleInform;

/**   
 * @Description:TODO(检测抽样样品数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface AssaySampleInformDao extends BaseMapper<AssaySampleInform> {
	
}
