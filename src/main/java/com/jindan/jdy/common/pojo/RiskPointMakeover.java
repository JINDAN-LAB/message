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
 * @Description:TODO(风险控制转让实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointMakeover extends Model<RiskPointMakeover> {

	private static final long serialVersionUID = 1607386688344L;

	@TableId(value = "zhuan_id", type = IdType.UUID)
	@ApiModelProperty(name = "zhuanId" , value = "")
	private String zhuanId;
    
	@ApiModelProperty(name = "riskPoint" , value = "")
	private String riskPoint;
    
	@ApiModelProperty(name = "startTime" , value = "")
	private String startTime;
    
	@ApiModelProperty(name = "endTime" , value = "")
	private String endTime;
    
	@ApiModelProperty(name = "transformPerson" , value = "")
	private String transformPerson;
    
	@ApiModelProperty(name = "receptionPerson" , value = "")
	private String receptionPerson;
    
	@ApiModelProperty(name = "zstatus" , value = "")
	private String zstatus;

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
