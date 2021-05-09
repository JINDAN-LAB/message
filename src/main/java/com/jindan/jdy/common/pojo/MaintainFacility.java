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
 * @Description:TODO(API应用KEY实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaintainFacility extends Model<MaintainFacility> {

	private static final long serialVersionUID = 1586873987068L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "maintainName" , value = "维修原因")
	private String maintainName;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间  不要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
    
	@ApiModelProperty(name = "maintainPerson" , value = "维修人")
	private String maintainPerson;
    
	@ApiModelProperty(name = "maintainType" , value = "维修类型： 检修，维修")
	private String maintainType;
    
	@ApiModelProperty(name = "departFacilityId" , value = "设备ID 信息")
	private String departFacilityId;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要设置")
	private Integer deleteId;
    
	@ApiModelProperty(name = "remark" , value = "详细备注")
	private String remark;
    
	@ApiModelProperty(name = "maintainContent" , value = "维修内容")
	private String maintainContent;


	@ApiModelProperty(name = "evaluate" , value = "设备评价")
	private String evaluate;




}
