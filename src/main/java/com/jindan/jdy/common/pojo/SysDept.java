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
public class SysDept extends Model<SysDept> {

	private static final long serialVersionUID = 1587954396555L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "pid" , value = "")
	private Integer pid;
    
	@ApiModelProperty(name = "name" , value = "")
	private String name;
    
	@ApiModelProperty(name = "open" , value = "")
	private Integer open;
    
	@ApiModelProperty(name = "parent" , value = "1父节点  0 子节点")
	private Integer parent;
    
	@ApiModelProperty(name = "remark" , value = "")
	private String remark;
    
	@ApiModelProperty(name = "loc" , value = "")
	private String loc;
    
	@ApiModelProperty(name = "available" , value = "状态【0不可用1可用】")
	private Integer available;
    
	@ApiModelProperty(name = "ordernum" , value = "排序码【为了调事显示顺序】")
	private Integer ordernum;
    

}
