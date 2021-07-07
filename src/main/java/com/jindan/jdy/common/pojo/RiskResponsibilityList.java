package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2021-06-17
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskResponsibilityList extends Model<RiskResponsibilityList> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rl_id", type = IdType.UUID)
    @ApiModelProperty(name = "rlId" , value = "")
    private String rlId;

    /**
     * 员工ID
     */
    @ApiModelProperty(name = "jdyUserId" , value = "")
    private String jdyUserId;

    /**
     * 安全职责
     */
    @ApiModelProperty(name = "safetyResponsibilities" , value = "")
    private String safetyResponsibilities;

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
