package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointDetails extends PageVO {
    private static final long serialVersionUID = 16073865297L;

    @TableId(value = "rid", type = IdType.UUID)
    @ApiModelProperty(name = "rid" , value = "")
    private String rid;

    @ApiModelProperty(name = "riskType" , value = "风险点分类")
    private String riskType;

    @ApiModelProperty(name = "riskName" , value = "风险点名称")
    private String riskName;

    @ApiModelProperty(name = "riskLocation" , value = "风险位置")
    private String riskLocation;

    @ApiModelProperty(name = "riskPerson" , value = "风控责任人")
    private String riskPerson;

    @ApiModelProperty(name = "examineType" , value = "检查类型")
    private String examineType;

    @ApiModelProperty(name = "isTeshu" , value = "是否特殊作业")
    private String isTeshu;

    @ApiModelProperty(name = "accidentMay" , value = "发生事故可能性")
    private String accidentMay;

    @ApiModelProperty(name = "riskOften" , value = "危险频繁程度")
    private String riskOften;

    @ApiModelProperty(name = "accidentResult" , value = "事故造成后果")
    private String accidentResult;

    @ApiModelProperty(name = "riskRank" , value = "危险级别")
    private String riskRank;

    @ApiModelProperty(name = "riskExplain" , value = "风险点描述")
    private String riskExplain;

    @ApiModelProperty(name = "riskExplainResult" , value = "可能造成的后果")
    private String riskExplainResult;

    @ApiModelProperty(name = "riskDetailLocation" , value = "详细位置")
    private String riskDetailLocation;

    @ApiModelProperty(name = "accidentTypes" , value = "诱发事故类型")
    private String accidentTypes;

    @ApiModelProperty(name = "lossForecast" , value = "损失预测")
    private String lossForecast;

    @ApiModelProperty(name = "controlMeasure" , value = "管控措施")
    private String controlMeasure;

    @ApiModelProperty(name = "potentialRisk" , value = "潜在隐患情况")
    private String potentialRisk;

    @ApiModelProperty(name = "measureMeet" , value = "应急处理措施")
    private String measureMeet;

    @ApiModelProperty(name = "technologySafeguard" , value = "技术保障措施")
    private String technologySafeguard;

    @ApiModelProperty(name = "remarks" , value = "备注")
    private String remarks;

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

    private List<RiskPointContentDto> list;

    private List<RiskPointPersons> personsList;

}
