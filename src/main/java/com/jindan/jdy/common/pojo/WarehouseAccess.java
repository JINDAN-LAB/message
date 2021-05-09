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
 * @Description:TODO(仓库管理实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="进出库信息",description="进出库信息")
public class WarehouseAccess extends Model<WarehouseAccess> {

	private static final long serialVersionUID = 1588838282524L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "id")
	private String id;
    
	@ApiModelProperty(name = "type" , value = "类型：1入库  2出库")
	private Integer type;
    
	@ApiModelProperty(name = "goodsId" , value = "货物id")
	private String goodsId;
    
	@ApiModelProperty(name = "goodsName" , value = "货物名称")
	private String goodsName;
    
	@ApiModelProperty(name = "specsId" , value = "规格id")
	private String specsId;
    
	@ApiModelProperty(name = "number" , value = "数量")
	private Integer number;
    
	@ApiModelProperty(name = "depaId" , value = "部门id")
	private String depaId;
    
	@ApiModelProperty(name = "depaName" , value = "部门name")
	private String depaName;
    
	@ApiModelProperty(name = "createName" , value = "创建人姓名")
	private String createName;
    
	@ApiModelProperty(name = "createId" , value = "创建人id")
	private Integer createId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态")
	private Integer deleteId;


}
