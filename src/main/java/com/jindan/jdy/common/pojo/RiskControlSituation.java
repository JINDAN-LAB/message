package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangfang
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskControlSituation extends Model<RiskControlSituation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rcs_id", type = IdType.UUID)
    @ApiModelProperty(name = "rcsId" , value = "")
    private String rcsId;

    /**
     * 风险点名称
     */
    @ApiModelProperty(name = "riskName" , value = "")
    private String riskName;

    /**
     * 主要管控级别
     */
    @ApiModelProperty(name = "mainControlLevel" , value = "")
    private String mainControlLevel;

    /**
     * 巡检人
     */
    @ApiModelProperty(name = "inspectionPersonnel" , value = "")
    private String inspectionPersonnel;

    /**
     * 次要管控级别
     */
    @ApiModelProperty(name = "minorControlLevel" , value = "")
    private String minorControlLevel;

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

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "insertTime" , value = "")
    private LocalDateTime insertTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

}
