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
 * @Description:TODO(每天发货报表的结果集实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayPutReportFormsResult extends Model<AssayPutReportFormsResult> {

	private static final long serialVersionUID = 1598060760934L;

	@TableId(value = "fhrid", type = IdType.UUID)
	@ApiModelProperty(name = "fhrid" , value = "")
	private String fhrid;
    
	@ApiModelProperty(name = "value1" , value = "")
	private String value1;
    
	@ApiModelProperty(name = "value2" , value = "")
	private String value2;
    
	@ApiModelProperty(name = "value3" , value = "")
	private String value3;
    
	@ApiModelProperty(name = "value4" , value = "")
	private String value4;
    
	@ApiModelProperty(name = "value5" , value = "")
	private String value5;
    
	@ApiModelProperty(name = "value6" , value = "")
	private String value6;
    
	@ApiModelProperty(name = "value7" , value = "")
	private String value7;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    
	@ApiModelProperty(name = "remarks" , value = "")
	private String remarks;

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

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
