package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangfang
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskManagementWarning extends Model<RiskManagementWarning> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rmw_id", type = IdType.UUID)
    @ApiModelProperty(name = "rmwId" , value = "")
    private String rmwId;

    /**
     * 预警责任人
     */
    @ApiModelProperty(name = "warningPerson" , value = "")
    private String warningPerson;

    /**
     * 接收手机号
     */
    @ApiModelProperty(name = "mobile" , value = "")
    private String mobile;

    /**
     * 预警消息
     */
    @ApiModelProperty(name = "warningContent" , value = "")
    private String warningContent;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "insertTime" , value = "")
    private LocalDateTime insertTime;

    /**
     * 预警时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "warningTime" , value = "")
    private LocalDateTime warningTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

}
