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
 * @Description:TODO(检测委托账单实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayEntrustBills extends Model<AssayEntrustBills> {

	private static final long serialVersionUID = 1598011044287L;

	@TableId(value = "eid", type = IdType.UUID)
	@ApiModelProperty(name = "eid" , value = "")
	private String eid;

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
    
	@ApiModelProperty(name = "ename" , value = "产品名称")
	private String ename;
    
	@ApiModelProperty(name = "epihao" , value = "批号")
	private String epihao;
    
	@ApiModelProperty(name = "eguige" , value = "产品规格")
	private String eguige;
    
	@ApiModelProperty(name = "enums" , value = "产品数量")
	private String enums;
    
	@ApiModelProperty(name = "eunit" , value = "委托单位")
	private String eunit;
    
	@ApiModelProperty(name = "eperson" , value = "委托人")
	private String eperson;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "dateTime" , value = "委托日期")
	private Date dateTime;
    
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
    

}
