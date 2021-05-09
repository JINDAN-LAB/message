package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.RiskPointJobSlipApply;
import com.jindan.jdy.common.pojo.RiskPointJobSlipDetails;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointJobSlipDto extends Model<RiskPointJobSlipApply> {

    private static final long serialVersionUID = 16101549690L;

    @TableId(value = "zdid", type = IdType.UUID)
    @ApiModelProperty(name = "zdid" , value = "")
    private String zdid;

    @ApiModelProperty(name = "znumber" , value = "作业编号")
    private String znumber;

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

    @ApiModelProperty(name = "parentId" , value = "")
    private String parentId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "zstartTime" , value = "开始时间")
    private Date zstartTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "zendTime" , value = "结束时间")
    private Date zendTime;

    @ApiModelProperty(name = "scenePersons" , value = "现场管理人")
    private String scenePersons;

    @ApiModelProperty(name = "safetyPersons" , value = "车间安全员接人")
    private String safetyPersons;

    @ApiModelProperty(name = "riskAssess" , value = "风险评估")
    private String riskAssess;

    @ApiModelProperty(name = "zuoyeLocation" , value = "作业地点")
    private String zuoyeLocation;

    @ApiModelProperty(name = "zstatus" , value = "状态")
    private String zstatus;

    @ApiModelProperty(name = "applyDepartment" , value = "申请单位")
    private String applyDepartment;

    @ApiModelProperty(name = "applyPersons" , value = "申请人")
    private String applyPersons;

    @ApiModelProperty(name = "ptypes" , value = "传入作业名称，要求作业名称不允许重复。")
    private String ptypes;

    @ApiModelProperty(name = "scenePersonsSignature" , value = "现场管理人员签字")
    private String scenePersonsSignature;

    @ApiModelProperty(name = "theirPersonsSignature" , value = "安环部长签字")
    private String theirPersonsSignature;

    @ApiModelProperty(name = "safetyPersonSingnature" , value = "安全员签字备注")
    private String safetyPersonSingnature;

    @ApiModelProperty(name = "workshopPersons" , value = "车间负责人")
    private String workshopPersons;

    @ApiModelProperty(name = "theirPersons" , value = "安环部长设置")
    private String theirPersons;

    @ApiModelProperty(name = "remarks1" , value = "安全员签字备注")
    private String remarks1;

    @ApiModelProperty(name = "remarks2" , value = "车间负责人备注")
    private String remarks2;

    @ApiModelProperty(name = "remarks3" , value = "安环部负责人签字")
    private String remarks3;

    private List<RiskPointJobSlipDetailsDto> detailsList;

    private RiskPointJobSlipApply  riskPointJobSlipPpply;

}
