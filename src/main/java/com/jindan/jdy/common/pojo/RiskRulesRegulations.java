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
 * @since 2021-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskRulesRegulations extends Model<RiskRulesRegulations> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "rules_id", type = IdType.UUID)
    @ApiModelProperty(name = "rulesId" , value = "")
    private String rulesId;

    /**
     * 制度名称
     */
    @ApiModelProperty(name = "systemName" , value = "")
    private String systemName;

    /**
     * 制定日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "developmentDate" , value = "")
    private Date developmentDate;

    /**
     * 使用范围
     */
    @ApiModelProperty(name = "scopeOfUse" , value = "")
    private String scopeOfUse;

    /**
     * 版本号
     */
    @ApiModelProperty(name = "versionNumber" , value = "")
    private String versionNumber;

    /**
     * 附件
     */
    @ApiModelProperty(name = "enclosure" , value = "")
    private String enclosure;

    /**
     * 状态
     */
    @ApiModelProperty(name = "state" , value = "")
    private String state;

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
