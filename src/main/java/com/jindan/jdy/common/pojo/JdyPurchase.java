package com.jindan.jdy.common.pojo;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.enumerate.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description:TODO(API应用KEY实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="采购表头",description="采购表头")
public class JdyPurchase extends Model<JdyPurchase> {

	private static final long serialVersionUID = 1586871957992L;

	@TableId(value = "purchase_id", type = IdType.UUID)
	@ApiModelProperty(name = "purchaseId" , value = "主键ID")
	private String purchaseId;
    
	@ApiModelProperty(name = "invoices" , value = "发票名称")
	private String invoices;
    
	@ApiModelProperty(name = "supplierId" , value = "供应商名称")
	private String supplierId;
    
	@ApiModelProperty(name = "operator" , value = "操作人")
	private String operator;
    
	@ApiModelProperty(name = "purchaseType" , value = "采购类型")
	private String purchaseType;

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

	@ApiModelProperty(name = "statics" , value = "状态")
	private String statics;


	@ApiModelProperty(name = "purchaseTime" , value = "采购时间")
	private String purchaseTime;

	@ApiModelProperty(name = "departments" , value = "采购部门")
	private String departments;

	@ApiModelProperty(name = "status" , value = "状态2")
	private String status;

	@ApiModelProperty(name = "transports" , value = "运输方式")
	private String transports;

	@ApiModelProperty(name = "puuids" , value = "关联商品")
	private String puuids;

	@ApiModelProperty(name = "allMoney" , value = "总金额")
	private String allMoney;


	@ApiModelProperty(name = "paperNumber" , value = "纸质合同")
	private String paperNumber;
	@ApiModelProperty(name = "contractName" , value = "合同名称")
	private String contractName;
	@ApiModelProperty(name = "contractType" , value = "合同类型")
	private String contractType;
	@ApiModelProperty(name = "signTimes" , value = "部署日期")
	private String signTimes;
	@ApiModelProperty(name = "oppositeName" , value = "对方名称")
	private String oppositeName;
	@ApiModelProperty(name = "deliveryLocation" , value = "交货地址")
	private String deliveryLocation;
	@ApiModelProperty(name = "currencys" , value = "币种")
	private String currencys;
	@ApiModelProperty(name = "zhebenParities" , value = "折本汇率")
	private String zhebenParities;
	@ApiModelProperty(name = "fukuanXieyi" , value = "付款协议")
	private String fukuanXieyi;
	@ApiModelProperty(name = "yufuLimit" , value = "预付款限额")
	private String yufuLimit;
	@ApiModelProperty(name = "hetongStatus" , value = "合同状态")
	private String hetongStatus;
	@ApiModelProperty(name = "leijiSums" , value = "累计付款总额")
	private String leijiSums;
	@ApiModelProperty(name = "sums" , value = "总数量")
	private String sums;
	@ApiModelProperty(name = "jiashuiheji" , value = "加税合计")
	private String jiashuiheji;
	@ApiModelProperty(name = "zhidanPersons" , value = "制单人")
	private String zhidanPersons;
	@ApiModelProperty(name = "shenpiPersons" , value = "审批人")
	private String shenpiPersons;
	@ApiModelProperty(name = "zhidanRiqi" , value = "制单日期")
	private String zhidanRiqi;
	@ApiModelProperty(name = "shenpiRiqi" , value = "审批日期")
	private String shenpiRiqi;
	@ApiModelProperty(name = "planEffect" , value = "计划生效日期")
	private String planEffect;
	@ApiModelProperty(name = "planEnd" , value = "计划终止日期")
	private String planEnd;

}
