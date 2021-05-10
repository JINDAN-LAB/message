package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.RiskPointDtoDetails;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(风险控制人数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface RiskPointPersonsMapper extends BaseMapper<RiskPointPersons> {

    List<RiskPointDtoDetails> seleListBaohanrenwuWapper(RiskPointPersons riskPointContent);

    List<RiskPointDtoDetails> seleteChaoqiPersons(RiskPointPersons riskPointContent);
}
