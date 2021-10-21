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
 * @since 2021-10-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityPatrolClassification extends Model<SecurityPatrolClassification> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "patrol_classification_id", type = IdType.UUID)
    @ApiModelProperty(name = "patrolClassificationId" , value = "")
    private String patrolClassificationId;

    /**
     * 风险点id
     */
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String riskpointId;

    /**
     * 管控级别
     */
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String controllevel;

    /**
     * 分类一
     */
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String categoryOne;

    /**
     * 分类二
     */
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String categoryTwo;

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


    @Override
    protected Serializable pkVal() {
        return this.patrolClassificationId;
    }

}
