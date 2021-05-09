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
public class RiskPointContentResultGongju extends PageVO {

    private static final long serialVersionUID = 16073863455L;

    @TableId(value = "ris_pcr", type = IdType.UUID)
    @ApiModelProperty(name = "risPcr" , value = "")
    private String risPcr;

    @ApiModelProperty(name = "patrolResult" , value = "巡查结果")
    private String patrolResult;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    @ApiModelProperty(name = "files1" , value = "隐患视图")
    private String files1;

    @ApiModelProperty(name = "files2" , value = "隐患视图")
    private String files2;

    @ApiModelProperty(name = "files3" , value = "隐患视图")
    private String files3;

    @ApiModelProperty(name = "patrolResultDetails" , value = "隐患详情")
    private String patrolResultDetails;

    @ApiModelProperty(name = "patrolResultJibie" , value = "风险级别")
    private String patrolResultJibie;

    @ApiModelProperty(name = "hiddenHeading" , value = "隐患大类")
    private String hiddenHeading;

    @ApiModelProperty(name = "hiddenSubclass" , value = "隐患小类")
    private String hiddenSubclass;

    @ApiModelProperty(name = "abarbeitungDeadline" , value = "整改期限")
    private String abarbeitungDeadline;

    @ApiModelProperty(name = "abarbeitungPerson" , value = "整改人")
    private String abarbeitungPerson;

    @ApiModelProperty(name = "duplicatePerson" , value = "抄送人")
    private String duplicatePerson;

    @ApiModelProperty(name = "peopleName" , value = "巡检人签字")
    private String peopleName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "status" , value = "状态")
    private String status;

    @ApiModelProperty(name = "hiddenLocation" , value = "隐患位置")
    private String hiddenLocation;

    @ApiModelProperty(name = "hiddenJibie" , value = "隐患级别")
    private String hiddenJibie;

    @ApiModelProperty(name = "zhuanrangTime" , value = "转让到期")
    private String zhuanrangTime;

    @ApiModelProperty(name = "rshebei" , value = "设备ID")
    private String rshebei;

    @ApiModelProperty(name = "shebeiID" , value = "设备ID")
    private String shebeiID;

    @ApiModelProperty(name = "jibies" , value = "管控级别")
    private String jibies;

}