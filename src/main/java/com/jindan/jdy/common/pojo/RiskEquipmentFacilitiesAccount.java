package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class RiskEquipmentFacilitiesAccount extends Model<RiskEquipmentFacilitiesAccount> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "refa_id", type = IdType.UUID)
    @ApiModelProperty(name = "refaId" , value = "")
    private String refaId;

    /**
     * 设备名称
     */
    @ApiModelProperty(name = "equipmentName" , value = "")
    private String equipmentName;

    /**
     * 规格型号
     */
    @ApiModelProperty(name = "specificationAndModel" , value = "")
    private String specificationAndModel;

    /**
     * 生产厂家
     */
    @ApiModelProperty(name = "manufacturer" , value = "")
    private String manufacturer;

    /**
     * 定检周期
     */
    @ApiModelProperty(name = "periodicInspectionCycle" , value = "")
    private String periodicInspectionCycle;

    /**
     * 运行场所
     */
    @ApiModelProperty(name = "operationSite" , value = "")
    private String operationSite;

    /**
     * 注册证号
     */
    @ApiModelProperty(name = "registrationCertificateNo" , value = "")
    private String registrationCertificateNo;

    /**
     * 使用证名称
     */
    @ApiModelProperty(name = "licenseName" , value = "")
    private String licenseName;

    /**
     * 使用证号
     */
    @ApiModelProperty(name = "licenseNo" , value = "")
    private String licenseNo;

    /**
     * 厂内编号
     */
    @ApiModelProperty(name = "factoryNumber" , value = "")
    private String factoryNumber;

    /**
     * 出厂日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "dateOfProduction" , value = "")
    private Date dateOfProduction;

    /**
     * 投用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "operationTime" , value = "")
    private Date operationTime;

    /**
     * 负责人
     */
    @ApiModelProperty(name = "personInCharge" , value = "")
    private String personInCharge;

    /**
     * 上次检修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "lastOverhaulTime" , value = "")
    private Date lastOverhaulTime;

    /**
     * 下次检修时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "nextOverhaulTime" , value = "")
    private Date nextOverhaulTime;

    /**
     * 检测状态
     */
    @ApiModelProperty(name = "detectionStatus" , value = "")
    private String detectionStatus;

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
