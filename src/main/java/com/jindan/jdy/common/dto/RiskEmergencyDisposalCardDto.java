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
public class RiskEmergencyDisposalCardDto extends PageVO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "edc_id", type = IdType.UUID)
    @ApiModelProperty(name = "edcId" , value = "")
    private String edcId;

    /**
     * 员工ID
     */
    @ApiModelProperty(name = "jdyUserId" , value = "")
    private String jdyUserId;

    /**
     * 现场处置措施
     */
    @ApiModelProperty(name = "disposalMeasures" , value = "")
    private String disposalMeasures;

    /**
     * 上报程序
     */
    @ApiModelProperty(name = "reportingProcedure" , value = "")
    private String reportingProcedure;

    /**
     * 上报联系方式
     */
    @ApiModelProperty(name = "reportContactInformation" , value = "")
    private String reportContactInformation;

    /**
     * 上报内容
     */
    @ApiModelProperty(name = "reportContent" , value = "")
    private String reportContent;

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
