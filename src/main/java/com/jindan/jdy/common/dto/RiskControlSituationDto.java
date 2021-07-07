package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.pojo.RiskControlSituation;
import com.jindan.jdy.common.pojo.RiskPoint;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskControlSituationDto extends PageVO {

    private static final long serialVersionUID = 1L;

    /**
     * 风险管控情况
     */
    @ApiModelProperty(name = "listRiskControlSituation" , value = "")
    private List<RiskControlSituation> listRiskControlSituation;

    /**
     * 风险点
     */
    @ApiModelProperty(name = "riskPoint" , value = "")
    private RiskPoint riskPoint;
}
