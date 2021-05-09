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
 * @Description:TODO(规则实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyRule extends Model<JdyRule> {


	private static final long serialVersionUID = 1589422611674L;

	@TableId(value = "rule_id", type = IdType.UUID)
	@ApiModelProperty(name = "ruleId" , value = "主键ID")
	private String ruleId;
    
	@ApiModelProperty(name = "ruleOne" , value = "部长")
	private String ruleOne;
    
	@ApiModelProperty(name = "ruleTwo" , value = "总监")
	private String ruleTwo;

	@ApiModelProperty(name = "ruleTwoCondition" , value = "总监审批条件")
	private String ruleTwoCondition;
    
	@ApiModelProperty(name = "ruleThree" , value = "总经理")
	private String ruleThree;
    
	@ApiModelProperty(name = "ruleThreeCondition" , value = "总监审批条件")
	private String ruleThreeCondition;

	@ApiModelProperty(name = "ruleFour" , value = "董事长")
	private String ruleFour;
    
	@ApiModelProperty(name = "ruleFourCondition" , value = "董事长审批条件")
	private String ruleFourCondition;

	@TableId(value = "departments")
	@ApiModelProperty(name = "departments" , value = "使用在那个部门")
	private String departments;
    
	@ApiModelProperty(name = "ruleType" , value = "适用类型，审批，采购等类型")
	private String ruleType;

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
