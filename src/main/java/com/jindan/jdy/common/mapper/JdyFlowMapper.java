package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyFlowDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyFlow;

import java.util.List;

/**   
 * @Description:TODO(规则数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyFlowMapper extends BaseMapper<JdyFlow> {

    List<JdyFlowDto> seleteJdyFlowperson(JdyFlowDto departmentSuggestDto);
}
