package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.SysPermission;

/**   
 * @Description:TODO(流程控制数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface SysPermissionDao extends BaseMapper<SysPermission> {
	
}
