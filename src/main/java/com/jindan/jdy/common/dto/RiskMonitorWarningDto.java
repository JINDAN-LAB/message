package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
public class RiskMonitorWarningDto extends PageVO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rmw_id", type = IdType.UUID)
    @ApiModelProperty(name = "rmwId" , value = "")
    private String rmwId;

    /**
     * 预警责任人
     */
    @ApiModelProperty(name = "warningPerson" , value = "预警责任人")
    private String warningPerson;

    /**
     * 接收手机号
     */
    @ApiModelProperty(name = "mobile" , value = "接收手机号")
    private String mobile;

    /**
     * 预警消息
     */
    @ApiModelProperty(name = "warningContent" , value = "预警消息")
    private String warningContent;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "insertTime" , value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime insertTime;

    /**
     * 预警时间
     */
    @ApiModelProperty(name = "warningTime" , value = "预警时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime warningTime;

    private Integer deleteId;

    private String startTime;

    private String endTime;

}
