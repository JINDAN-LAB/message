package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyUserDto;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.JdyUser;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: BianPeng
 * 
 */
@Mapper
public interface JdyUserMapper extends BaseMapper<JdyUser> {

    List<UserPermission> seleAllUserPriment(Integer id);

    List<JdyUserDto> seleAllUserUserRole(String id);


    JdyUserDto seleteUserDetailsOne(String userId);

    List<JdyUserDto> selegetInfo(String id);

    List<JdyUser> selectJdyUserDtoList(JdyUserDto jdyUserDto);
}
