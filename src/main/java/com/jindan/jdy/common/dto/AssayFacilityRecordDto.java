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
public class AssayFacilityRecordDto extends PageVO {


    private static final long serialVersionUID = 15980121530L;
    @TableId(value = "eqid", type = IdType.UUID)
    @ApiModelProperty(name = "eqid" , value = "")
    private String eqid;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(name = "detectionTime" , value = "")
    private String detectionTime;

    @ApiModelProperty(name = "shouSample" , value = "受检样品")
    private String shouSample;

    @ApiModelProperty(name = "detectionItem" , value = "检测项目")
    private String detectionItem;

    @ApiModelProperty(name = "sampleIdentifying" , value = "样品标识")
    private String sampleIdentifying;

    @ApiModelProperty(name = "useTime" , value = "使用日期")
    private String useTime;

    @ApiModelProperty(name = "facilityStatus" , value = "")
    private String facilityStatus;

    @ApiModelProperty(name = "usePerson" , value = "使用人")
    private String usePerson;


}
