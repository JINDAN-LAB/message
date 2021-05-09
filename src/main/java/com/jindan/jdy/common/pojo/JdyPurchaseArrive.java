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
 * @Description:TODO(到货单表头信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyPurchaseArrive extends Model<JdyPurchaseArrive> {

	private static final long serialVersionUID = 1609227394159L;

	@TableId(value = "purchase_id", type = IdType.UUID)
	@ApiModelProperty(name = "purchaseId" , value = "主键")
	private String purchaseId;
    
	@ApiModelProperty(name = "invoices" , value = "发票")
	private String invoices;
    
	@ApiModelProperty(name = "supplierId" , value = "供应商ID")
	private String supplierId;
    
	@ApiModelProperty(name = "operator" , value = "操作人")
	private String operator;
    
	@ApiModelProperty(name = "purchaseType" , value = "采购类型   出库或者入库")
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
    
	@ApiModelProperty(name = "departments" , value = "部门")
	private String departments;
    
	@ApiModelProperty(name = "status" , value = "状态2")
	private String status;
    
	@ApiModelProperty(name = "transports" , value = "运输单号")
	private String transports;
    
	@ApiModelProperty(name = "puuids" , value = "内容ID")
	private String puuids;
    
	@ApiModelProperty(name = "allMoney" , value = "总金额")
	private String allMoney;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    

}
