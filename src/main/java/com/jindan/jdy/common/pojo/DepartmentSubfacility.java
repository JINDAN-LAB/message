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
@ApiModel(value="部门子设备",description="部门子设备")
public class DepartmentSubfacility extends Model<DepartmentSubfacility> {

	private static final long serialVersionUID = 1586872053399L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键")
	private String id;
    
	@ApiModelProperty(name = "goodsId" , value = "二级菜单")
	private String goodsId;
    
	@ApiModelProperty(name = "brand" , value = "品牌")
	private String brand;
    
	@ApiModelProperty(name = "model" , value = "模型")
	private String model;
    
	@ApiModelProperty(name = "nuit" , value = "单位")
	private String nuit;
    
	@ApiModelProperty(name = "number" , value = "数量")
	private Integer number;
    
	@ApiModelProperty(name = "price" , value = "单价")
	private Float price;
    
	@ApiModelProperty(name = "states" , value = "状态")
	private String states;
    
	@ApiModelProperty(name = "createName" , value = "名称")
	private String createName;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
    
	@ApiModelProperty(name = "fileId" , value = "文件ID")
	private String fileId;
    
	@ApiModelProperty(name = "imgurl" , value = "图片文件")
	private String imgurl;
    
	@ApiModelProperty(name = "departFacilityId" , value = "父设备")
	private String departFacilityId;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态")
	private Integer deleteId;


	@ApiModelProperty(name = "bitNumber" , value = "位号")
	private String bitNumber;

	@ApiModelProperty(name = "maintainPerson" , value = "责任人")
	private String maintainPerson;

	@ApiModelProperty(name = "isRepair" , value = "是否定期检修")
	private String isRepair;
	@ApiModelProperty(name = "repairPeriod" , value = "定期检修周期")
	private String repairPeriod;

	@ApiModelProperty(name = "pidNumber" , value = "PID编码")
	private String pidNumber;

	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;

	@ApiModelProperty(name = "specifications" , value = "规格")
	private String specifications;

	@ApiModelProperty(name = "departments" , value = "部门")
	private String departments;

	@ApiModelProperty(name = "serialnumber" , value = "序号")
	private String serialnumber;

	@ApiModelProperty(name = "assetnumber" , value = "固定资产编号")
	private String assetnumber;

	@ApiModelProperty(name = "allkg" , value = "总重")
	private String allkg;

	@ApiModelProperty(name = "installtime" , value = "安装时间")
	private String installtime;


	@ApiModelProperty(name = "usetime" , value = "使用时间")
	private String usetime;

	@ApiModelProperty(name = "rateOfWork" , value = "功率")
	private String rateOfWork;


	@ApiModelProperty(name = "powers" , value = "")
	private String powers;

	@ApiModelProperty(name = "parameters" , value = "")
	private String parameters;

	@ApiModelProperty(name = "lubrication" , value = "")
	private String lubrication;

	@ApiModelProperty(name = "texture" , value = "")
	private String texture;

	@ApiModelProperty(name = "allWeight" , value = "")
	private String allWeight;

	@ApiModelProperty(name = "revolutionsPerMinute" , value = "")
	private String revolutionsPerMinute;

	@ApiModelProperty(name = "commodityName" , value = "")
	private String commodityName;

	@ApiModelProperty(name = "frequencys" , value = "")
	private String frequencys;

	@ApiModelProperty(name = "warehouseId" , value = "")
	private String warehouseId;



}
