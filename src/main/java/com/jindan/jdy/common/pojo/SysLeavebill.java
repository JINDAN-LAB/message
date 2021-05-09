package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description:TODO(流程控制实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLeavebill extends Model<SysLeavebill> {

	private static final long serialVersionUID = 1587954406755L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "title" , value = "")
	private String title;
    
	@ApiModelProperty(name = "content" , value = "")
	private String content;
    
	@ApiModelProperty(name = "days" , value = "")
	private Double days;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "leavetime" , value = "")
	private Date leavetime;
    
	@ApiModelProperty(name = "state" , value = "1,新建  2，已提交   3，审批中  4，审批完成")
	private String state;
    
	@ApiModelProperty(name = "userid" , value = "")
	private Integer userid;
    

}
