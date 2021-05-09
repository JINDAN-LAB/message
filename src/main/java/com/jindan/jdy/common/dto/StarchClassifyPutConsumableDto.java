package com.jindan.jdy.common.dto;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.pojo.WarehouseDepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**   
 * @Description:TODO(耗材资产分类实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchClassifyPutConsumableDto extends Model<StarchClassifyPutConsumableDto> {

	private static final long serialVersionUID = 1600068421253L;

	@TableId(value = "ids", type = IdType.UUID)
	@ApiModelProperty(name = "ids" , value = "")
	private String ids;
    
	@ApiModelProperty(name = "names" , value = "")
	private String names;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    
	@ApiModelProperty(name = "url" , value = "")
	private String url;
    
	@ApiModelProperty(name = "icon" , value = "")
	private String icon;
    
	@ApiModelProperty(name = "orders" , value = "")
	private String orders;

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
    
	@ApiModelProperty(name = "levels" , value = "")
	private String levels;

	@ApiModelProperty(name = "nums" , value = "")
	private String nums;

	@ApiModelProperty(name = "jine" , value = "")
	private String jine;

	List<StarchOrganizationPut> starchOrganizationPutList;

	List<StarchClassifyPutConsumableDto> list;

	List<WarehouseDepository> warehouseDepositoryList;

}
