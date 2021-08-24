package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskMajorHazardSourcesDto extends PageVO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "rmhs_id", type = IdType.UUID)
    @ApiModelProperty(name = "rmhsId" , value = "")
    private String rmhsId;

    /**
     * 危险源名称
     */
    @ApiModelProperty(name = "nameOfHazardSource" , value = "")
    private String nameOfHazardSource;

    /**
     * 注册编号
     */
    @ApiModelProperty(name = "registrationNumber" , value = "")
    private String registrationNumber;

    /**
     * 活动区域
     */
    @ApiModelProperty(name = "zoneOfAction" , value = "")
    private String zoneOfAction;

    /**
     * 当前状态
     */
    @ApiModelProperty(name = "currentState" , value = "")
    private String currentState;

    /**
     * 危险类型
     */
    @ApiModelProperty(name = "hazardType" , value = "")
    private String hazardType;

    /**
     * 形成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "formationTime" , value = "")
    private Date formationTime;

    /**
     * 责任人
     */
    @ApiModelProperty(name = "personLiable" , value = "")
    private String personLiable;

    /**
     * 危险等级
     */
    @ApiModelProperty(name = "hazardLevel" , value = "")
    private String hazardLevel;

    /**
     * 确认依据
     */
    @ApiModelProperty(name = "confirmationBasis" , value = "")
    private String confirmationBasis;

    /**
     * 可能造成后果
     */
    @ApiModelProperty(name = "possibleConsequences" , value = "")
    private String possibleConsequences;

    /**
     * 控制措施
     */
    @ApiModelProperty(name = "controlMeasures" , value = "")
    private String controlMeasures;

    /**
     * 周围环境
     */
    @ApiModelProperty(name = "environment" , value = "")
    private String environment;

    /**
     * 重大危险源编码
     */
    @ApiModelProperty(name = "codeOfMajorHazard" , value = "")
    private String codeOfMajorHazard;

    /**
     * 是否关联隐患
     */
    @ApiModelProperty(name = "associatedHiddenDanger" , value = "")
    private String associatedHiddenDanger;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "insertTime" , value = "")
    private Date insertTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

}
