package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(用户关联角色数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface UserRoleDao extends BaseMapper<UserRole> {
	
}
