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
 * @Description:TODO(API应用KEY实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="建议信息",description="建议信息")
public class DepartmentSuggest extends Model<DepartmentSuggest> {

	private static final long serialVersionUID = 1587017449685L;
	@TableId(value = "suggest_id", type = IdType.UUID)
	@ApiModelProperty(name = "suggestId" , value = "主键")
	private String suggestId;
    
	@ApiModelProperty(name = "suggestName" , value = "建议标题")
	private String suggestName;
    
	@ApiModelProperty(name = "suggestContent" , value = "建议内容")
	private String suggestContent;
    
	@ApiModelProperty(name = "deaprtment" , value = "部门信息")
	private String deaprtment;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
    
	@ApiModelProperty(name = "status" , value = "状态")
	private Integer status;
    
	@ApiModelProperty(name = "resultContent" , value = "反馈")
	private String resultContent;

	@ApiModelProperty(name = "userId" , value = "用户信息")
	private String userId;

	@ApiModelProperty(name = "resultPre" , value = "")
	private String resultPre;

	@ApiModelProperty(name = "resultTime" , value = "")
	private String resultTime;


}
