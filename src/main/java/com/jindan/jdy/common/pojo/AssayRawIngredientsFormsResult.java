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
 * @Description:TODO(报表结果发货报表的结果集实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayRawIngredientsFormsResult extends Model<AssayRawIngredientsFormsResult> {

	private static final long serialVersionUID = 1598062827595L;

	@TableId(value = "yulb", type = IdType.UUID)
	@ApiModelProperty(name = "yulb" , value = "")
	private String yulb;
    
	@ApiModelProperty(name = "datev" , value = "")
	private String datev;
    
	@ApiModelProperty(name = "values1" , value = "")
	private String values1;
    
	@ApiModelProperty(name = "values2" , value = "")
	private String values2;
    
	@ApiModelProperty(name = "values3" , value = "")
	private String values3;
    
	@ApiModelProperty(name = "values4" , value = "")
	private String values4;
    
	@ApiModelProperty(name = "values5" , value = "")
	private String values5;
    
	@ApiModelProperty(name = "values6" , value = "")
	private String values6;
    
	@ApiModelProperty(name = "values7" , value = "")
	private String values7;
    
	@ApiModelProperty(name = "values8" , value = "")
	private String values8;
    
	@ApiModelProperty(name = "values9" , value = "")
	private String values9;
    
	@ApiModelProperty(name = "values10" , value = "")
	private String values10;
    
	@ApiModelProperty(name = "values11" , value = "")
	private String values11;
    
	@ApiModelProperty(name = "values12" , value = "")
	private String values12;
    
	@ApiModelProperty(name = "values13" , value = "")
	private String values13;
    
	@ApiModelProperty(name = "values14" , value = "")
	private String values14;
    
	@ApiModelProperty(name = "values15" , value = "")
	private String values15;
    
	@ApiModelProperty(name = "values16" , value = "")
	private String values16;
    
	@ApiModelProperty(name = "values17" , value = "")
	private String values17;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;

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
    

}
