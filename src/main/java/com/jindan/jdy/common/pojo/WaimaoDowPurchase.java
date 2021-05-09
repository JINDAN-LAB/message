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
public class WaimaoDowPurchase extends Model<WaimaoDowPurchase> {

	private static final long serialVersionUID = 1596078781409L;

	@TableId(value = "cid", type = IdType.UUID)
	@ApiModelProperty(name = "cid" , value = "")
	private String cid;
    
	@ApiModelProperty(name = "suppliers" , value = "供应商")
	private String suppliers;
    
	@ApiModelProperty(name = "materialName" , value = "物料名称")
	private String materialName;
    
	@ApiModelProperty(name = "units" , value = "单位")
	private String units;
    
	@ApiModelProperty(name = "nums" , value = "数量")
	private String nums;
    
	@ApiModelProperty(name = "jine" , value = "金额")
	private String jine;
    
	@ApiModelProperty(name = "tax" , value = "税额")
	private String tax;

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
    
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
    
	@ApiModelProperty(name = "jiahsuiheji" , value = "价税合计")
	private String jiahsuiheji;
    
	@ApiModelProperty(name = "chetonghao" , value = "合同号")
	private String chetonghao;

	@ApiModelProperty(name = "cfapiaohao" , value = "发票号")
	private String cfapiaohao;


}
