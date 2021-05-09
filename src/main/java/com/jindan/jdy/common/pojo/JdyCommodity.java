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
import java.math.BigDecimal;
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
@ApiModel(value="采购详情",description="采购详情")
public class JdyCommodity extends Model<JdyCommodity> {

	private static final long serialVersionUID = 1587028170844L;

	@TableId(value = "commodity_id", type = IdType.UUID)
	@ApiModelProperty(name = "commodityId" , value = "主键")
	private String commodityId;

	@ApiModelProperty(name = "model" , value = "规格")
	private String model;

	@ApiModelProperty(name = "facilityName" , value = "设备名称")
	private String facilityName;

	@ApiModelProperty(name = "commodityName" , value = "商品编号")
	private String commodityName;

	@ApiModelProperty(name = "num" , value = "数量")
	private Integer num;

	@ApiModelProperty(name = "units" , value = "单位")
	private String units;

	@ApiModelProperty(name = "person" , value = "提交人")
	private String person;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@ApiModelProperty(name = "status" , value = "状态信息，采购 ，入库，库存，出库，带安装，运行，维修中，停运，报废")
	private Integer status;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除标识 0正常  1 已删除")
	private Integer deleteId;

	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;

	@ApiModelProperty(name = "frequencys" , value = "变频")
	private String frequencys;

	@ApiModelProperty(name = "revolutionsPerMinute" , value = "电机、转/分")
	private String revolutionsPerMinute;

	@ApiModelProperty(name = "pieceWeight" , value = "单重")
	private Double pieceWeight;

	@ApiModelProperty(name = "allWeight" , value = "总重")
	private Double allWeight;

	@ApiModelProperty(name = "texture" , value = "材质")
	private String texture;

	@ApiModelProperty(name = "lubrication" , value = "润滑方式")
	private String lubrication;

	@ApiModelProperty(name = "parameters" , value = "电机参数")
	private String parameters;

	@ApiModelProperty(name = "powers" , value = "功率")
	private String powers;

	@ApiModelProperty(name = "parentId" , value = "父级信息")
	private String parentId;

	@ApiModelProperty(name = "warehouseId" , value = "父级信息")
	private String warehouseId;

	@ApiModelProperty(name = "goodsId" , value = "")
	private String goodsId;

	@ApiModelProperty(name = "brand" , value = "品牌")
	private String brand;

	@ApiModelProperty(name = "nuit" , value = "计数单位")
	private String nuit;

	@ApiModelProperty(name = "materialModel" , value = "物料型号")
	private String materialModel;

	@ApiModelProperty(name = "materialName" , value = "物料名称")
	private String materialName;

	@ApiModelProperty(name = "materialCode" , value = "物料编码")
	private String materialCode;

	@ApiModelProperty(name = "unitPrice" , value = "无税单价")
	private String unitPrice;

	@ApiModelProperty(name = "taxUnitPrice" , value = "含税单价")
	private String taxUnitPrice;

	@ApiModelProperty(name = "jiashuiHeji" , value = "加税合计")
	private String jiashuiHeji;

	@ApiModelProperty(name = "huilv" , value = "汇率")
	private String huilv;

	@ApiModelProperty(name = "zongshuie" , value = "总税额")
	private String zongshuie;

	@ApiModelProperty(name = "baozhuang" , value = "包装物")
	private String baozhuang;


}
