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
public class RiskSpecialCertificateAccountDto extends PageVO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "rsca_id", type = IdType.UUID)
    @ApiModelProperty(name = "rscaId" , value = "")
    private String rscaId;

    /**
     * 证书编号
     */
    @ApiModelProperty(name = "certificateNo" , value = "")
    private String certificateNo;

    /**
     * 持证人
     */
    @ApiModelProperty(name = "certificateHolder" , value = "")
    private String certificateHolder;

    /**
     * 作业类型
     */
    @ApiModelProperty(name = "jobCategory" , value = "")
    private String jobCategory;

    /**
     * 准操项目
     */
    @ApiModelProperty(name = "standardOperationItems" , value = "")
    private String standardOperationItems;

    /**
     * 发证单位
     */
    @ApiModelProperty(name = "certificateIssuingUnit" , value = "")
    private String certificateIssuingUnit;

    /**
     * 检测状态
     */
    @ApiModelProperty(name = "detectionStatus" , value = "")
    private String detectionStatus;

    /**
     * 初次领证日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "issueDate" , value = "")
    private Date issueDate;

    /**
     * 有效期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "termOfValidity" , value = "")
    private Date termOfValidity;

    /**
     * 领证日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "dateOfIssue" , value = "")
    private Date dateOfIssue;

    /**
     * 复审期限
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "reviewPeriod" , value = "")
    private Date reviewPeriod;

    /**
     * 复审日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "reviewDate" , value = "")
    private Date reviewDate;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "insertTime" , value = "")
    private Date insertTime;

    /**
     * 开始时间
     */
    @ApiModelProperty(name = "startTime" , value = "")
    private String startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(name = "endTime" , value = "")
    private String endTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

}
