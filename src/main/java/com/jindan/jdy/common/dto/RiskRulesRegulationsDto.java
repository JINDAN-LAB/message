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
public class RiskRulesRegulationsDto  extends PageVO {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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
