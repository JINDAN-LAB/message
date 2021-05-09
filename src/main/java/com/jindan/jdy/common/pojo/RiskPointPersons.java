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
 * @Description:TODO(风险控制人实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointPersons extends Model<RiskPointPersons> {

	private static final long serialVersionUID = 1607386729934L;

	@TableId(value = "ris_id", type = IdType.UUID)
	@ApiModelProperty(name = "risId" , value = "")
	private String risId;

	@ApiModelProperty(name = "riskTaskType" , value = "任务类型")
	private String riskTaskType;
    
	@ApiModelProperty(name = "parentId" , value = "任务ID")
	private String parentId;
    
	@ApiModelProperty(name = "taskPerson" , value = "巡检人员")
	private String taskPerson;
    
	@ApiModelProperty(name = "controlRank" , value = "管控级别")
	private String controlRank;
    
	@ApiModelProperty(name = "frequency" , value = "频次")
	private String frequency;
    
	@ApiModelProperty(name = "lastTimes" , value = "上次巡查时间")
	private String lastTimes;

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

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
