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
 * @Description:TODO(风险控制任务超期实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointContentChaoqi extends Model<RiskPointContentChaoqi> {

	private static final long serialVersionUID = 1608631599180L;

	@TableId(value = "cq_id", type = IdType.UUID)
	@ApiModelProperty(name = "cqId" , value = "")
	private String cqId;

	@ApiModelProperty(name = "contentId" , value = "超期内容")
	private String contentId;
    
	@ApiModelProperty(name = "chaoqiTime" , value = "超期时间")
	private String chaoqiTime;
    
	@ApiModelProperty(name = "chaoqiPerson" , value = "超期人")
	private String chaoqiPerson;

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

	@ApiModelProperty(name = "nums" , value = "超期次数")
	private String nums;

}
