package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.RiskPointContentDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.RiskPointContent;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**   
 * @Description:TODO(风险控制点内容数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface RiskPointContentMapper extends BaseMapper<RiskPointContent> {

    List<RiskPointContentDto> seleListJieGuoWapper(RiskPointContent riskPointContent);

    List<RiskPointContentDto> seleteSaoMaBaohan(@Param("shebeiID") String riskPointContent, @Param("uname") String username);

}
