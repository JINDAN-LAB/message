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
 * @Description:TODO(产品发货信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayPutReportForms extends Model<AssayPutReportForms> {

	private static final long serialVersionUID = 1598060438281L;

	@TableId(value = "fhid", type = IdType.UUID)
	@ApiModelProperty(name = "fhid" , value = "")
	private String fhid;
    
	@ApiModelProperty(name = "title1" , value = "")
	private String title1;
    
	@ApiModelProperty(name = "title2" , value = "")
	private String title2;
    
	@ApiModelProperty(name = "title3" , value = "")
	private String title3;
    
	@ApiModelProperty(name = "title4" , value = "")
	private String title4;
    
	@ApiModelProperty(name = "title5" , value = "")
	private String title5;
    
	@ApiModelProperty(name = "title6" , value = "")
	private String title6;
    
	@ApiModelProperty(name = "title7" , value = "")
	private String title7;
    
	@ApiModelProperty(name = "status" , value = "")
	private String status;
    
	@ApiModelProperty(name = "names" , value = "")
	private String names;


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
