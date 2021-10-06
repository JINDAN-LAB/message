package com.jindan.jdy.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskGridControlDto {

    private static final long serialVersionUID = 1L;

    /**
     * 风险点名称
     */
    @ApiModelProperty(name = "dutyCycle" , value = "")
    private String riskName;

    /**
     * 任务周期
     */
    @ApiModelProperty(name = "dutyCycle" , value = "")
    private String dutyCycle;

    /**
     * 任务类型
     */
    @ApiModelProperty(name = "taskType" , value = "")
    private String taskType;
}
