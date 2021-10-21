package com.jindan.jdy.common.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangfang
 * @since 2021-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityRiskpointJudged extends Model<SecurityRiskpointJudged> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "riskpoint_id", type = IdType.UUID)
    @ApiModelProperty(name = "riskpointId" , value = "")
    private String riskpointId;

    /**
     * 风险点分类
     */
    @ApiModelProperty(name = "riskpointClassification" , value = "")
    private String riskpointClassification;

    /**
     * 风险点名称
     */
    @ApiModelProperty(name = "riskpointName" , value = "")
    private String riskpointName;

    /**
     * 风险点位置
     */
    @ApiModelProperty(name = "riskpointLocation" , value = "")
    private String riskpointLocation;

    /**
     * 负责人
     */
    @ApiModelProperty(name = "personInCharge" , value = "")
    private String personInCharge;

    /**
     * 检查类型
     */
    @ApiModelProperty(name = "inspectionType" , value = "")
    private String inspectionType;

    /**
     * NFC编码
     */
    @ApiModelProperty(name = "nfcCode" , value = "")
    private String nfcCode;

    /**
     * 是否特种设备
     */
    @ApiModelProperty(name = "isSpecialEquipment" , value = "")
    private String isSpecialEquipment;

    /**
     * 发生事故的可能性LEC（L）
     */
    @ApiModelProperty(name = "lecAccidentPossibility" , value = "")
    private String lecAccidentPossibility;

    /**
     * 暴露于危险环境的频繁程度LEC（E）
     */
    @ApiModelProperty(name = "lecDangerDegree" , value = "")
    private String lecDangerDegree;

    /**
     * 事故可能造成的后果LEC（C）
     */
    @ApiModelProperty(name = "lecPossibleConsequence" , value = "")
    private String lecPossibleConsequence;

    /**
     * 危险性LEC（D=L*E*C）
     */
    @ApiModelProperty(name = "lecDanger" , value = "")
    private String lecDanger;

    /**
     * 事故发生的可能性LS（L）
     */
    @ApiModelProperty(name = "lsAccidentPossibility" , value = "")
    private String lsAccidentPossibility;

    /**
     * 事故后果严重性LS（S）
     */
    @ApiModelProperty(name = "lsConsequenceSeriousness" , value = "")
    private String lsConsequenceSeriousness;

    /**
     * 风险值LS（R=L*S）
     */
    @ApiModelProperty(name = "lsRiskValue" , value = "")
    private String lsRiskValue;

    /**
     * 风险等级/危险等级
     */
    @ApiModelProperty(name = "riskLevel" , value = "")
    private String riskLevel;

    /**
     * 风险点描述
     */
    @ApiModelProperty(name = "riskpointDescription" , value = "")
    private String riskpointDescription;

    /**
     * 可能造成后果
     */
    @ApiModelProperty(name = "possibleConsequences" , value = "")
    private String possibleConsequences;

    /**
     * 详细位置
     */
    @ApiModelProperty(name = "detailedLocation" , value = "")
    private String detailedLocation;

    /**
     * 诱发事故类型
     */
    @ApiModelProperty(name = "accidentType" , value = "")
    private String accidentType;

    /**
     * 损失预测
     */
    @ApiModelProperty(name = "lossPrediction" , value = "")
    private String lossPrediction;

    /**
     * 管控措施
     */
    @ApiModelProperty(name = "controlMeasures" , value = "")
    private String controlMeasures;

    /**
     * 潜在隐患情况
     */
    @ApiModelProperty(name = "hiddenDanger" , value = "")
    private String hiddenDanger;

    /**
     * 应急处置措施
     */
    @ApiModelProperty(name = "emergencyMeasures" , value = "")
    private String emergencyMeasures;

    /**
     * 技术保障措施
     */
    @ApiModelProperty(name = "technicalMeasures" , value = "")
    private String technicalMeasures;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remarks" , value = "")
    private String remarks;

    /**
     * 研判时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "judgmentTime" , value = "")
    private Date judgmentTime;

    /**
     * 是否已离线
     */
    @ApiModelProperty(name = "isOffline" , value = "")
    private String isOffline;

    /**
     * 风险点状态
     */
    @ApiModelProperty(name = "riskpointStatus" , value = "")
    private String riskpointStatus;

    /**
     * 二维码路径
     */
    @ApiModelProperty(name = "qrcodePath" , value = "")
    private String qrcodePath;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(name = "insertTime" , value = "")
    private Date insertTime;

    /**
     * 逻辑删除
     */
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;


    @Override
    protected Serializable pkVal() {
        return this.riskpointId;
    }

}
