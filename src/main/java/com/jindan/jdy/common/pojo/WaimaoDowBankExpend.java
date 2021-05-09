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
 * @Description:TODO(外贸道氏实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoDowBankExpend extends Model<WaimaoDowBankExpend> {

	private static final long serialVersionUID = 1596078727423L;

	@TableId(value = "yid", type = IdType.UUID)
	@ApiModelProperty(name = "yid" , value = "")
	private String yid;
    
	@ApiModelProperty(name = "paymentTime" , value = "付款时间")
	private String paymentTime;
    
	@ApiModelProperty(name = "shoukuanren" , value = "收款人")
	private String shoukuanren;
    
	@ApiModelProperty(name = "yjine" , value = "银行支出金额")
	private String yjine;
    
	@ApiModelProperty(name = "ynature" , value = "性质")
	private String ynature;
    
	@ApiModelProperty(name = "paymentForGoods" , value = "货款或者费用")
	private String paymentForGoods;
    
	@ApiModelProperty(name = "contractNumber" , value = "合同号")
	private String contractNumber;
    
	@ApiModelProperty(name = "yremarks" , value = "备注")
	private String yremarks;

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
	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@ApiModelProperty(name = "yjine2" , value = "银行费用金额")
	private String yjine2;

	@ApiModelProperty(name = "yfapiaohao" , value = "发票号")
	private String yfapiaohao;


}
