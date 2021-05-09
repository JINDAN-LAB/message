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
@ApiModel(value="二级目录信息",description="二级目录信息")
public class WarehouseGoods extends Model<WarehouseGoods>{

	private static final long serialVersionUID = 1588838302190L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "id")
	private String id;
    
	@ApiModelProperty(name = "depositoryId" , value = "类别id")
	private String depositoryId;
    
	@ApiModelProperty(name = "depositoryName" , value = "类别名称")
	private String depositoryName;
    
	@ApiModelProperty(name = "name" , value = "货物名称")
	private String name;
    
	@ApiModelProperty(name = "state" , value = "状态： 0删除  1正常")
	private Integer state;
    
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
    
	@ApiModelProperty(name = "createName" , value = "创建人姓名")
	private String createName;
    
	@ApiModelProperty(name = "createId" , value = "创建人id")
	private String createId;

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
