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
 * @Description:TODO(报表发货报表的结果集实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayRawIngredientsForms extends Model<AssayRawIngredientsForms> {

	private static final long serialVersionUID = 1598062813025L;

	@TableId(value = "yfid", type = IdType.UUID)
	@ApiModelProperty(name = "yfid" , value = "")
	private String yfid;
    
	@ApiModelProperty(name = "name" , value = "")
	private String name;
    
	@ApiModelProperty(name = "titl1" , value = "")
	private String titl1;
    
	@ApiModelProperty(name = "titl2" , value = "")
	private String titl2;
    
	@ApiModelProperty(name = "titl3" , value = "")
	private String titl3;
    
	@ApiModelProperty(name = "titl4" , value = "")
	private String titl4;
    
	@ApiModelProperty(name = "titl5" , value = "")
	private String titl5;
    
	@ApiModelProperty(name = "titl6" , value = "")
	private String titl6;
    
	@ApiModelProperty(name = "titl7" , value = "")
	private String titl7;
    
	@ApiModelProperty(name = "titl8" , value = "")
	private String titl8;
    
	@ApiModelProperty(name = "titl9" , value = "")
	private String titl9;
    
	@ApiModelProperty(name = "titl10" , value = "")
	private String titl10;
    
	@ApiModelProperty(name = "titl11" , value = "")
	private String titl11;
    
	@ApiModelProperty(name = "titl12" , value = "")
	private String titl12;
    
	@ApiModelProperty(name = "titl13" , value = "")
	private String titl13;
    
	@ApiModelProperty(name = "titl14" , value = "")
	private String titl14;
    
	@ApiModelProperty(name = "titl15" , value = "")
	private String titl15;
    
	@ApiModelProperty(name = "titl16" , value = "")
	private String titl16;
    
	@ApiModelProperty(name = "titl17" , value = "")
	private String titl17;
    
	@ApiModelProperty(name = "remarks" , value = "")
	private String remarks;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@ApiModelProperty(name = "dates" , value = "")
	private String dates;
    

}
