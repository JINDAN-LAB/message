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
 * @Description:TODO(领料单实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyPacking extends Model<JdyPacking> {

	private static final long serialVersionUID = 1589342083477L;
	
	@ApiModelProperty(name = "pickingId" , value = "主键ID")
	private String pickingId;
    
	@ApiModelProperty(name = "pickTitle" , value = "货物名称")
	private String pickTitle;
    
	@ApiModelProperty(name = "pickContent" , value = "详细说明")
	private String pickContent;
    
	@ApiModelProperty(name = "pickNum" , value = "数量")
	private Integer pickNum;
    
	@ApiModelProperty(name = "remarks" , value = "备注信息")
	private String remarks;

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
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@ApiModelProperty(name = "packPerson" , value = "提交人")
	private String packPerson;
    
	@ApiModelProperty(name = "packDepartment" , value = "提交部门")
	private String packDepartment;
    
	@ApiModelProperty(name = "packUuid" , value = "唯一标识")
	private String packUuid;

	@ApiModelProperty(name = "status" , value = "")
	private Integer status;


}
