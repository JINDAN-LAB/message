package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.StarchAccessPut;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(领用和退库关系表数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchAccessPutDao extends BaseMapper<StarchAccessPut> {
	
}
