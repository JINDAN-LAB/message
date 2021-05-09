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
 * @Description:TODO(资产借用与归还实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationBorrowReturn extends Model<StarchOrganizationBorrowReturn> {

	private static final long serialVersionUID = 1598231608786L;

	@TableId(value = "brid", type = IdType.UUID)
	@ApiModelProperty(name = "brid" , value = "主键ID")
	private String brid;
    
	@ApiModelProperty(name = "status" , value = "办理状态")
	private String status;
    
	@ApiModelProperty(name = "borrowNumber" , value = "借用单号")
	private Integer borrowNumber;
    
	@ApiModelProperty(name = "borrowTime" , value = "借用时间")
	private String borrowTime;
    
	@ApiModelProperty(name = "borrowPerson" , value = "借用人")
	private String borrowPerson;
    
	@ApiModelProperty(name = "predictReturnTime" , value = "预计归还时间")
	private String predictReturnTime;
    
	@ApiModelProperty(name = "returnTime" , value = "归还时间")
	private String returnTime;
    
	@ApiModelProperty(name = "propertyParentId" , value = "资产编号")
	private String propertyParentId;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间  不要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@JsonIgnore
	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

	@ApiModelProperty(name = "departments" , value = "部门")
	private String departments;



}
