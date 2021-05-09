package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @Description:TODO(货物规格表实体类)
 * 
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockSpecs extends Model<StockSpecs> {

	private static final long serialVersionUID = 1586084821284L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "id")
	private Integer id;

	@ApiModelProperty(name = "goodsId" , value = "货物id")
	private Integer goodsId;
    
	@ApiModelProperty(name = "brand" , value = "品牌")
	private String brand;
    
	@ApiModelProperty(name = "model" , value = "型号")
	private String model;
    
	@ApiModelProperty(name = "unit" , value = "单位")
	private String unit;
    
	@ApiModelProperty(name = "number" , value = "数量")
	private Integer number;
    
	@ApiModelProperty(name = "price" , value = "价格")
	private String price;
    
	@ApiModelProperty(name = "state" , value = "状态： 0删除  1正常")
	private Integer state;
    
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
