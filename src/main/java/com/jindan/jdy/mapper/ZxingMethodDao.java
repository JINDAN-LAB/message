package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.ZxingMethod;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(自定义验证二维码域名目录数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface ZxingMethodDao extends BaseMapper<ZxingMethod> {
	
}
