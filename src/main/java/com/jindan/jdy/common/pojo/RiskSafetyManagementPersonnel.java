package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangfang
 * @since 2021-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskSafetyManagementPersonnel extends Model<RiskSafetyManagementPersonnel> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rsmp_id", type = IdType.UUID)
    @ApiModelProperty(name = "rsmpId" , value = "")
    private String rsmpId;

    /**
     * 员工ID
     */
    @ApiModelProperty(name = "jdyUserId" , value = "")
    private String jdyUserId;

    /**
     * 安全管理人员
     */
    @ApiModelProperty(name = "safetyManagementPersonnel" , value = "")
    private String safetyManagementPersonnel;

    /**
     * 职务
     */
    @ApiModelProperty(name = "post" , value = "")
    private String post;

    /**
     * 手机号
     */
    @ApiModelProperty(name = "phone" , value = "")
    private String phone;

    /**
     * 有效开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "effectiveStartTime" , value = "")
    private Date effectiveStartTime;

    /**
     * 有效结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "effectiveEndTime" , value = "")
    private Date effectiveEndTime;

    /**
     * 行业类别
     */
    @ApiModelProperty(name = "industryCategory" , value = "")
    private String industryCategory;

    /**
     * 发证机关
     */
    @ApiModelProperty(name = "issuingAuthority" , value = "")
    private String issuingAuthority;

    /**
     * 证书编号
     */
    @ApiModelProperty(name = "certificateNo" , value = "")
    private String certificateNo;

    /**
     * 复训时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "retrainingTime" , value = "")
    private Date retrainingTime;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remarks" , value = "")
    private String remarks;

    /**
     * 复训状态
     */
    @ApiModelProperty(name = "retrainingStatus" , value = "")
    private String retrainingStatus;

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
