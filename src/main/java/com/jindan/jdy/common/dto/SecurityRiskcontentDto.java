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
public class SecurityRiskcontentDto extends PageVO {

    @TableId(value = "riskcontent_id", type = IdType.UUID)
    @ApiModelProperty(name = "riskcontentId" , value = "")
    private String riskcontentId;

    /**
     * 风险点id
     */
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String riskpointId;

    /**
     * 巡检分类id
     */
    @ApiModelProperty(name = "patrolClassificationId" , value = "")
    private String patrolClassificationId;

    /**
     * 风险管控内容
     */
    @ApiModelProperty(name = "riskControlContent" , value = "")
    private String riskControlContent;

    /**
     * 导致事故原因
     */
    @ApiModelProperty(name = "accidentCause" , value = "")
    private String accidentCause;

    /**
     * 可能导致的事故
     */
    @ApiModelProperty(name = "possibleAccidents" , value = "")
    private String possibleAccidents;

    /**
     * 应急处置措施
     */
    @ApiModelProperty(name = "emergencyMeasures" , value = "")
    private String emergencyMeasures;

    /**
     * 技术保障措施
     */
    @ApiModelProperty(name = "technicalMeasures" , value = "")
    private String technicalMeasures;

    /**
     * 影响范围
     */
    @ApiModelProperty(name = "influenceScope" , value = "")
    private String influenceScope;

    /**
     * 潜在后果
     */
    @ApiModelProperty(name = "potentialConsequences" , value = "")
    private String potentialConsequences;

    /**
     * 是否拍照
     */
    @ApiModelProperty(name = "isPhotograph" , value = "")
    private String isPhotograph;

    /**
     * 照片路径
     */
    @ApiModelProperty(name = "photoPath" , value = "")
    private String photoPath;

    /**
     * 是否数据输入
     */
    @ApiModelProperty(name = "isDataEntry" , value = "")
    private String isDataEntry;

    /**
     * 数据输入路径
     */
    @ApiModelProperty(name = "dataEntryPath" , value = "")
    private String dataEntryPath;

    /**
     * 正确的图片地址
     */
    @ApiModelProperty(name = "correctPicturePath" , value = "")
    private String correctPicturePath;

    /**
     * 错误的图片地址
     */
    @ApiModelProperty(name = "wrongPicturePath" , value = "")
    private String wrongPicturePath;

    /**
     * 研判时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "judgmentTime" , value = "")
    private Date judgmentTime;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
