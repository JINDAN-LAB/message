package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.RiskEmergencyDisposalCard;
import com.jindan.jdy.common.pojo.RiskResponsibilityList;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskResponsibilityListDto extends PageVO{

    private static final long serialVersionUID = 1L;

    /**
     * 责任清单
     */
    @ApiModelProperty(name = "riskResponsibilityLists" , value = "")
    private List<RiskResponsibilityList> riskResponsibilityLists;

    /**
     * 应急处置卡
     */
    @ApiModelProperty(name = "riskEmergencyDisposalCard" , value = "")
    private RiskEmergencyDisposalCard riskEmergencyDisposalCard;

    /**
     * 风险点
     */
    @ApiModelProperty(name = "jdyUser" , value = "")
    private JdyUser jdyUser;
}
