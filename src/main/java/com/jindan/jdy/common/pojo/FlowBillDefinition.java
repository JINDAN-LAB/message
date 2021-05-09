package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description:TODO(流程定义表实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FlowBillDefinition extends Model<FlowBillDefinition> {

	private static final long serialVersionUID = 1597911645217L;

	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private String id;

	@ApiModelProperty(name = "title" , value = "流程名称")
	private String title;
    
	@ApiModelProperty(name = "user1" , value = "用户1")
	private String user1;
    
	@ApiModelProperty(name = "user2" , value = "用户2")
	private String user2;
    
	@ApiModelProperty(name = "user3" , value = "用户3")
	private String user3;
    
	@ApiModelProperty(name = "user4" , value = "用户4")
	private String user4;
    
	@ApiModelProperty(name = "user5" , value = "用户5")
	private String user5;
    
	@ApiModelProperty(name = "user6" , value = "用户6")
	private String user6;
    
	@ApiModelProperty(name = "user7" , value = "用户7")
	private String user7;
    
	@ApiModelProperty(name = "user8" , value = "用户8")
	private String user8;
    
	@ApiModelProperty(name = "user9" , value = "用户9")
	private String user9;
    
	@ApiModelProperty(name = "user10" , value = "用户10")
	private String user10;
    
	@ApiModelProperty(name = "condition1" , value = "条件1")
	private String condition1;
    
	@ApiModelProperty(name = "condition2" , value = "条件2")
	private String condition2;
    
	@ApiModelProperty(name = "condition3" , value = "条件3")
	private String condition3;
    
	@ApiModelProperty(name = "condition4" , value = "条件4")
	private String condition4;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@JsonIgnore
	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

	@ApiModelProperty(name = "flowId" , value = "")
	private String flowId;

	@ApiModelProperty(name = "flowName" , value = "")
	private String flowName;

	@ApiModelProperty(name = "flowDefinitionId" , value = "")
	private String flowDefinitionId;

	@ApiModelProperty(name = "flowDefinitionName" , value = "")
	private String flowDefinitionName;

	@ApiModelProperty(name = "flowDefinitionKey" , value = "")
	private String flowDefinitionKey;

	@ApiModelProperty(name = "flowDefinitionVersion" , value = "")
	private String flowDefinitionVersion;



}
