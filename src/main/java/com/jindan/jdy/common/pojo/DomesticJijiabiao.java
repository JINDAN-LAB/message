package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description:TODO(内贸提成实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="基价1",description="基价1")
public class DomesticJijiabiao extends Model<DomesticJijiabiao> {

	private static final long serialVersionUID = 1588822741718L;

	@TableId(value = "name")
	@ApiModelProperty(name = "name" , value = "基价名称")
	private String name;
    
	@ApiModelProperty(name = "diwudang" , value = "第五档基价")
	private String diwudang;
    
	@ApiModelProperty(name = "disidang" , value = "第四档基价")
	private String disidang;
    
	@ApiModelProperty(name = "disandang" , value = "第三档基价")
	private String disandang;
    
	@ApiModelProperty(name = "dierdang" , value = "第二档基价")
	private String dierdang;
    
	@ApiModelProperty(name = "diyidang" , value = "第一档基价")
	private String diyidang;

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
    

}
