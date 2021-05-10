package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.JdyRole;
import org.apache.ibatis.annotations.Mapper;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyRoleMapper extends BaseMapper<JdyRole> {

    Integer insertJdyRole(JdyRole jdyRole);
}
