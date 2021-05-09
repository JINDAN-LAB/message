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
 * @Description:TODO(资产维修登记实体类)
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchMaintainRegister extends Model<StarchMaintainRegister> {

	private static final long serialVersionUID = 1598233726531L;

	@TableId(value = "wxid", type = IdType.UUID)
	@ApiModelProperty(name = "wxid" , value = "主键ID")
	private String wxid;

	@ApiModelProperty(name = "status" , value = "办理状态")
	private String status;
    
	@ApiModelProperty(name = "wxstatus" , value = "状态")
	private String wxstatus;
    
	@ApiModelProperty(name = "serviceNumber" , value = "维修单号")
	private String serviceNumber;
    
	@ApiModelProperty(name = "imgurl" , value = "照片")
	private String imgurl;

	@ApiModelProperty(name = "serviceDate" , value = "业务日期")
	private String serviceDate;
    
	@ApiModelProperty(name = "servicePerson" , value = "责任人")
	private String servicePerson;
    
	@ApiModelProperty(name = "warrantyPerson" , value = "报修人")
	private String warrantyPerson;

	@ApiModelProperty(name = "wxPersons" , value = "维修人")
	private String wxPersons;
    
	@ApiModelProperty(name = "serviceJine" , value = "维修花费")
	private String serviceJine;
    
	@ApiModelProperty(name = "serviceContent" , value = "维修内容")
	private String serviceContent;
    
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

	@ApiModelProperty(name = "departments" , value = "维修申请部门")
	private String departments;

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

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

	@ApiModelProperty(name = "ptypes" , value = "类型 1维保变更，2正常维保信息")
	private String ptypes;

	@ApiModelProperty(name = "ptype" , value = "故障类型")
	private String ptype;

	@ApiModelProperty(name = "evaluateWeixiu" , value = "维修人评价")
	private String evaluateWeixiu;

	@ApiModelProperty(name = "evaluateBaoxiu" , value = "报修人评价")
	private String evaluateBaoxiu;

	@ApiModelProperty(name = "evaluateWeixiuRemarks" , value = "维修人评价备注")
	private String evaluateWeixiuRemarks;

	@ApiModelProperty(name = "evaluateBaoxiuRemarks" , value = "报修人评价备注")
	private String evaluateBaoxiuRemarks;

}
