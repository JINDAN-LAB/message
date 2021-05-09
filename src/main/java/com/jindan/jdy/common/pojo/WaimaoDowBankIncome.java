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
public class WaimaoDowBankIncome extends Model<WaimaoDowBankIncome> {

	private static final long serialVersionUID = 1596078752275L;

	@TableId(value = "ysid", type = IdType.UUID)
	@ApiModelProperty(name = "ysid" , value = "")
	private String ysid;
    
	@ApiModelProperty(name = "shouTime" , value = "收款时间")
	private String shouTime;
    
	@ApiModelProperty(name = "shouRen" , value = "收款人")
	private String shouRen;
    
	@ApiModelProperty(name = "shouJine" , value = "银行收入金额")
	private String shouJine;
    
	@ApiModelProperty(name = "shouNature" , value = "性质")
	private String shouNature;
    
	@ApiModelProperty(name = "huoTuishui" , value = "退款或者退税")
	private String huoTuishui;
    
	@ApiModelProperty(name = "contractFapiao" , value = "合同或者发票号")
	private String contractFapiao;
    
	@ApiModelProperty(name = "ysremarks" , value = "")
	private String ysremarks;

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
    
	@ApiModelProperty(name = "shouJine2" , value = "银行退税金额")
	private String shouJine2;

	@ApiModelProperty(name = "ysfapiaohao" , value = "发票号")
	private String ysfapiaohao;

}
