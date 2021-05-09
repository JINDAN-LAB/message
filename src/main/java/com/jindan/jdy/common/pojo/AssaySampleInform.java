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
 * @Description:TODO(检测抽样样品实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssaySampleInform extends Model<AssaySampleInform> {

	private static final long serialVersionUID = 1598011465662L;

	@TableId(value = "sample_id", type = IdType.UUID)
	@ApiModelProperty(name = "sampleId" , value = "")
	private String sampleId;

	@ApiModelProperty(name = "productName" , value = "")
	private String productName;
    
	@ApiModelProperty(name = "productPihao" , value = "")
	private String productPihao;
    
	@ApiModelProperty(name = "productGuige" , value = "")
	private String productGuige;
    
	@ApiModelProperty(name = "productNums" , value = "")
	private String productNums;
    
	@ApiModelProperty(name = "productUnit" , value = "")
	private String productUnit;
    
	@ApiModelProperty(name = "productPerson" , value = "")
	private String productPerson;
    
	@ApiModelProperty(name = "productTime" , value = "")
	private String productTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

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
    

}
