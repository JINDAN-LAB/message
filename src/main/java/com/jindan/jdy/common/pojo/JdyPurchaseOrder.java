package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;

/**   
 * @Description:TODO(采购订单信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyPurchaseOrder extends Model<JdyPurchaseOrder> {

	private static final long serialVersionUID = 1610346906595L;
	
	@ApiModelProperty(name = "pids" , value = "")
	private String pids;
    
	@ApiModelProperty(name = "orderType" , value = "订单类型")
	private String orderType;
    
	@ApiModelProperty(name = "orderNumber" , value = "订单编号")
	private String orderNumber;
    
	@ApiModelProperty(name = "orderTimes" , value = "订单日期")
	private String orderTimes;
    
	@ApiModelProperty(name = "orderSupplier" , value = "供应商")
	private String orderSupplier;
    
	@ApiModelProperty(name = "accountTypes" , value = "结算方式")
	private String accountTypes;
    
	@ApiModelProperty(name = "buyerPersons" , value = "采购员")
	private String buyerPersons;
    
	@ApiModelProperty(name = "orderDepartment" , value = "采购部门")
	private String orderDepartment;
    
	@ApiModelProperty(name = "types" , value = "类别")
	private String types;
    
	@ApiModelProperty(name = "jiashuihejis" , value = "加税合计")
	private String jiashuihejis;
    
	@ApiModelProperty(name = "orderStatus" , value = "单据状态")
	private String orderStatus;
    
	@ApiModelProperty(name = "remarks" , value = "备注信息")
	private String remarks;
    
	@ApiModelProperty(name = "bankAccount" , value = "银行账户")
	private String bankAccount;
    
	@ApiModelProperty(name = "makeTime" , value = "制单日期")
	private String makeTime;
    
	@ApiModelProperty(name = "shenpiTimes" , value = "审批日期")
	private String shenpiTimes;
    
	@ApiModelProperty(name = "makePersonss" , value = "制单人")
	private String makePersonss;
    
	@ApiModelProperty(name = "shenpiPersons" , value = "审批人")
	private String shenpiPersons;
    

}
