package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.ZxingStand;

/**   
 * @Description:TODO(自定义验证二维码域名目录数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface ZxingStandDao extends BaseMapper<ZxingStand> {
	
}
