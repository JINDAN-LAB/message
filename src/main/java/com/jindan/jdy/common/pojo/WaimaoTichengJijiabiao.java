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
 * @Description:TODO(外贸提成实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="外贸基价",description="外贸基价")
public class WaimaoTichengJijiabiao extends Model<WaimaoTichengJijiabiao> {

	private static final long serialVersionUID = 1592364786028L;

	@TableId(value = "titlename")
	@ApiModelProperty(name = "titlename" , value = "")
	private String titlename;
    
	@ApiModelProperty(name = "fifth" , value = "")
	private String fifth;
    
	@ApiModelProperty(name = "fourthly" , value = "")
	private String fourthly;
    
	@ApiModelProperty(name = "thirdly" , value = "")
	private String thirdly;
    
	@ApiModelProperty(name = "second" , value = "")
	private String second;
    
	@ApiModelProperty(name = "first" , value = "")
	private String first;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态")
	private Integer deleteId;
    

}
