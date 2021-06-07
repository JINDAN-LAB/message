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

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangfang
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskWarningDto extends PageVO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "rw_id", type = IdType.UUID)
    @ApiModelProperty(name = "rwId" , value = "")
    private String rwId;

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
    @ApiModelProperty(name = "insertTime" , value = "")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    /**
     * 预警时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "warningTime" , value = "")
    private Date warningTime;

    private Date endWarningTime;

    private Integer deleteId;

}
