package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.RiskPointDetails;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**   
 * @Description:TODO(风险控制点内容返回值数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface RiskPointContentResultMapper extends BaseMapper<RiskPointContentResult> {

    List<RiskPointDetails> seleListDetailsWapper(@Param("array") List<String> array);

}
