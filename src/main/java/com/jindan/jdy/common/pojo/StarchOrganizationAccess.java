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
 * @Description:TODO(资产领用与退库实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationAccess extends Model<StarchOrganizationAccess> {

	private static final long serialVersionUID = 1598231025388L;

	@TableId(value = "lid", type = IdType.UUID)
	@ApiModelProperty(name = "lid" , value = "主键ID")
	private String lid;

	@ApiModelProperty(name = "status" , value = "状态")
	private String status;
    
	@ApiModelProperty(name = "recipientsNumber" , value = "领用单号")
	private String recipientsNumber;
    
	@ApiModelProperty(name = "receiveTime" , value = "领取时间")
	private String receiveTime;
    
	@ApiModelProperty(name = "receivePerson" , value = "领用人")
	private String receivePerson;
    
	@ApiModelProperty(name = "receiveCompany" , value = "领取后使用公司")
	private String receiveCompany;
    
	@ApiModelProperty(name = "receiveDepartment" , value = "领取后存放部门")
	private String receiveDepartment;
    
	@ApiModelProperty(name = "receiveArea" , value = "领取后存放区域")
	private String receiveArea;
    
	@ApiModelProperty(name = "receivePlace" , value = "领取后存放地点")
	private String receivePlace;
    
	@ApiModelProperty(name = "receiveRemarks" , value = "领用备注")
	private String receiveRemarks;
    
	@ApiModelProperty(name = "cancellingStocksTime" , value = "预计退库日期")
	private String cancellingStocksTime;
    
	@ApiModelProperty(name = "managePerson" , value = "处理人")
	private String managePerson;
    
	@ApiModelProperty(name = "assetNumber" , value = "资产编号")
	private String assetNumber;
    
	@ApiModelProperty(name = "assetType" , value = "资产类型")
	private String assetType;
    
	@ApiModelProperty(name = "assetName" , value = "资产名称")
	private String assetName;
    
	@ApiModelProperty(name = "assetModel" , value = "规格型号")
	private String assetModel;
    
	@ApiModelProperty(name = "assetSn" , value = "SN码")
	private String assetSn;
    
	@ApiModelProperty(name = "modelJine" , value = "金额")
	private String modelJine;
    
	@ApiModelProperty(name = "useCompany" , value = "使用公司")
	private String useCompany;
    
	@ApiModelProperty(name = "useDepartment" , value = "使用部门")
	private String useDepartment;
    
	@ApiModelProperty(name = "usePerson" , value = "使用人")
	private String usePerson;
    
	@ApiModelProperty(name = "managerPerson" , value = "管理员")
	private String managerPerson;
    
	@ApiModelProperty(name = "belongCompany" , value = "所属公司")
	private String belongCompany;
    
	@ApiModelProperty(name = "belongEreas" , value = "存放地区")
	private String belongEreas;
    
	@ApiModelProperty(name = "belongPlace" , value = "存放地点")
	private String belongPlace;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

	@ApiModelProperty(name = "ptype" , value = "已确认，未确认")
	private String ptype;

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




}
