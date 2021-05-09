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
import java.util.Date;

/**   
 * @Description:TODO(进入库实体类)
 * 
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockAccess extends Model<StockAccess> {

	private static final long serialVersionUID = 1586084503574L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "id")
	private Integer id;
    
	@ApiModelProperty(name = "type" , value = "类型：1入库  2出库")
	private Integer type;
    
	@ApiModelProperty(name = "goodId" , value = "货物id")
	private Integer goodsId;
    
	@ApiModelProperty(name = "goodName" , value = "货物名称")
	private String goodsName;

	@ApiModelProperty(name = "specsId" , value = "规格id")
	private String specsId;
    
	@ApiModelProperty(name = "number" , value = "数量")
	private Integer number;
    
	@ApiModelProperty(name = "depaId" , value = "部门id")
	private Integer depaId;
    
	@ApiModelProperty(name = "depaName" , value = "部门name")
	private String depaName;
    
	@ApiModelProperty(name = "createName" , value = "创建人姓名")
	private String createName;
    
	@ApiModelProperty(name = "createId" , value = "创建人id")
	private Integer createId;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "创建时间")
	private Date createTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改时间")
	private Date updateTime;
    

}
