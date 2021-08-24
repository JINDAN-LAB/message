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
public class RiskSafetyMeetingAccountDto extends PageVO{

    private static final long serialVersionUID = 1L;

    @TableId(value = "rsmaId", type = IdType.UUID)
    @ApiModelProperty(name = "rsmaId" , value = "")
    private String rsmaId;

    /**
     * 会议名称
     */
    @ApiModelProperty(name = "conferenceName" , value = "")
    private String conferenceName;

    /**
     * 预计开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "estimatedStartTime" , value = "")
    private Date estimatedStartTime;

    /**
     * 预计结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "estimatedEndTime" , value = "")
    private Date estimatedEndTime;

    /**
     * 实际开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "actualStartTime" , value = "")
    private Date actualStartTime;

    /**
     * 实际结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "actualEndTime" , value = "")
    private Date actualEndTime;

    /**
     * 学时
     */
    @ApiModelProperty(name = "classHours" , value = "")
    private String classHours;

    /**
     * 结束文书图片
     */
    @ApiModelProperty(name = "endDocumentPicture" , value = "")
    private String endDocumentPicture;

    /**
     * 会议开始图片
     */
    @ApiModelProperty(name = "meetingStartPicture" , value = "")
    private String meetingStartPicture;

    /**
     * 会议结束图片
     */
    @ApiModelProperty(name = "meetingEndPicture" , value = "")
    private String meetingEndPicture;

    /**
     * 签到二维码
     */
    @ApiModelProperty(name = "checkInQrcode" , value = "")
    private String checkInQrcode;

    /**
     * 签退二维码
     */
    @ApiModelProperty(name = "signOutQrcode" , value = "")
    private String signOutQrcode;

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
