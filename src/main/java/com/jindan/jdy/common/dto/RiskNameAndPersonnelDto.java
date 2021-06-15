package com.jindan.jdy.common.dto;

import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskNameAndPersonnelDto extends PageVO {

    private static final long serialVersionUID = 1L;

    /**
     * 风险点名称
     */
    @ApiModelProperty(name = "riskName" , value = "")
    private String riskName;

    /**
     * 风险点名称
     */
    @ApiModelProperty(name = "riskNameId" , value = "")
    private String riskNameId;

    /**
     * 巡检人
     */
    @ApiModelProperty(name = "inspectionPersonnel" , value = "")
    private String inspectionPersonnel;
}
