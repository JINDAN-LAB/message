package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.RiskPointJobSlipDetailsDto;
import com.jindan.jdy.common.dto.RiskPointJobSlipDto;
import com.jindan.jdy.common.pojo.RiskPointJobSlipApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(作业票批准数据访问层)
 * @version: V1.0
 * @author: kong
 */
@Mapper
public interface RiskPointJobSlipApplyMapper extends BaseMapper<RiskPointJobSlipApply> {

    List<RiskPointJobSlipDto> seleDetails(RiskPointJobSlipApply userPermission);

    List<RiskPointJobSlipDetailsDto> seleteZhenggaiRiskPoint(RiskPointJobSlipApply riskPointContent);

}
