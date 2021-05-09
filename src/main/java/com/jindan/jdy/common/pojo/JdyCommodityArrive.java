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
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Description:TODO(到货单表体信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyCommodityArrive extends Model<JdyCommodityArrive> {

	private static final long serialVersionUID = 1609227435129L;

	@TableId(value = "commodity_id", type = IdType.UUID)
	@ApiModelProperty(name = "commodityId" , value = "")
	private String commodityId;
    
	@ApiModelProperty(name = "model" , value = "")
	private String model;
    
	@ApiModelProperty(name = "facilityName" , value = "设备名称")
	private String facilityName;
    
	@ApiModelProperty(name = "commodityName" , value = "商品编号")
	private String commodityName;
    
	@ApiModelProperty(name = "num" , value = "数量")
	private Integer num;
    
	@ApiModelProperty(name = "unitPrice" , value = "单价")
	private BigDecimal unitPrice;
    
	@ApiModelProperty(name = "person" , value = "提交人")
	private String person;

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
    
	@ApiModelProperty(name = "status" , value = "状态信息，采购 ，入库，库存，出库，带安装，运行，维修中，停运，报废")
	private String status;
    

    
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
    
	@ApiModelProperty(name = "warehouseId" , value = "")
	private String warehouseId;
    
	@ApiModelProperty(name = "goodsId" , value = "")
	private String goodsId;
    
	@ApiModelProperty(name = "brand" , value = "")
	private String brand;
    
	@ApiModelProperty(name = "nuit" , value = "")
	private String nuit;
    
	@ApiModelProperty(name = "rparentId" , value = "")
	private String rparentId;
    

}
