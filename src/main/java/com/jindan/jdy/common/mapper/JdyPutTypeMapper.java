package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyPutTypeDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyPutType;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyPutTypeMapper extends BaseMapper<JdyPutType> {

    List<JdyPutTypeDto> selectAll();
}
