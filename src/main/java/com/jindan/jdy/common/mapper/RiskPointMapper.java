package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.dto.RiskPointDto;
import com.jindan.jdy.common.pojo.RiskPointContent;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.RiskPoint;

import java.util.List;

/**
 * @Description:TODO(风险控制数据访问层)
 *
 * @version: V1.0
 * @author: kong
 *
 */
@Mapper
public interface RiskPointMapper extends BaseMapper<RiskPoint> {

    List<RiskPointDto> seleListBaohanWapper(RiskPoint riskPointContent);

    int insertSave(RiskPoint riskPoint);


}
