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
public class WaimaoDowMarket extends Model<WaimaoDowMarket> {

	private static final long serialVersionUID = 1596078766073L;

	@TableId(value = "xid", type = IdType.UUID)
	@ApiModelProperty(name = "xid" , value = "")
	private String xid;
    
	@ApiModelProperty(name = "shouhuokehu" , value = "收货客户")
	private String shouhuokehu;
    
	@ApiModelProperty(name = "materialNames" , value = "物料名称")
	private String materialNames;
    
	@ApiModelProperty(name = "nums" , value = "数量")
	private String nums;
    
	@ApiModelProperty(name = "unitx" , value = "单位")
	private String unitx;
    
	@ApiModelProperty(name = "xtax" , value = "税额")
	private String xtax;
    
	@ApiModelProperty(name = "drawback" , value = "退税")
	private String drawback;
    
	@ApiModelProperty(name = "refundRates" , value = "")
	private String refundRates;
    
	@ApiModelProperty(name = "remarksx" , value = "")
	private String remarksx;

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
    
	@ApiModelProperty(name = "xhetonghao" , value = "合同号")
	private String xhetonghao;

	@ApiModelProperty(name = "xfapiaohao" , value = "发票号")
	private String xfapiaohao;

}
