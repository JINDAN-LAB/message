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
 * @Description:TODO(化验室委托单实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyPurchaseArriveTest extends Model<JdyPurchaseArriveTest> {

	private static final long serialVersionUID = 1609229023892L;

	@TableId(value = "hid", type = IdType.UUID)
	@ApiModelProperty(name = "hid" , value = "")
	private String hid;
    
	@ApiModelProperty(name = "hname" , value = "")
	private String hname;
    
	@ApiModelProperty(name = "hpihao" , value = "")
	private String hpihao;
    
	@ApiModelProperty(name = "guige" , value = "")
	private String guige;
    
	@ApiModelProperty(name = "nums" , value = "")
	private String nums;
    
	@ApiModelProperty(name = "remarks" , value = "")
	private String remarks;
    
	@ApiModelProperty(name = "wdepartments" , value = "")
	private String wdepartments;
    
	@ApiModelProperty(name = "wpersons" , value = "")
	private String wpersons;
    
	@ApiModelProperty(name = "wtime" , value = "")
	private String wtime;

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
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    

}
