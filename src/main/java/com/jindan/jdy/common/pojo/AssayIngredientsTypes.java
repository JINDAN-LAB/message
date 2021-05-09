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
 * @Description:TODO(检测-分类表实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayIngredientsTypes extends Model<AssayIngredientsTypes> {

	private static final long serialVersionUID = 1598865547814L;

	@TableId(value = "yfid", type = IdType.UUID)
	@ApiModelProperty(name = "yfid" , value = "")
	private String yfid;

	@ApiModelProperty(name = "titles" , value = "")
	private String titles;
    
	@ApiModelProperty(name = "orders" , value = "")
	private String orders;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

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
    
	@ApiModelProperty(name = "icon" , value = "")
	private String icon;

	@ApiModelProperty(name = "urls" , value = "")
	private String urls;

	@ApiModelProperty(name = "status" , value = "")
	private String status;


}
