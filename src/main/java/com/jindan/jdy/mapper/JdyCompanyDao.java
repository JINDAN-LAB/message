package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.JdyCompany;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(资产子设备信息数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyCompanyDao extends BaseMapper<JdyCompany> {
	
}
