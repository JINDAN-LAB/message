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
public class SysPermission extends Model<SysPermission> {

	private static final long serialVersionUID = 1587954436635L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "pid" , value = "")
	private Integer pid;
    
	@ApiModelProperty(name = "type" , value = "权限类型[menu/permission]")
	private String type;
    
	@ApiModelProperty(name = "parent" , value = "0子节点 1父节点")
	private Integer parent;
    
	@ApiModelProperty(name = "percode" , value = "权限编码[只有type= permission才有  user:view]")
	private String percode;
    
	@ApiModelProperty(name = "name" , value = "")
	private String name;
    
	@ApiModelProperty(name = "icon" , value = "")
	private String icon;
    
	@ApiModelProperty(name = "href" , value = "")
	private String href;
    
	@ApiModelProperty(name = "target" , value = "")
	private String target;
    
	@ApiModelProperty(name = "open" , value = "")
	private Integer open;
    
	@ApiModelProperty(name = "ordernum" , value = "")
	private Integer ordernum;
    
	@ApiModelProperty(name = "available" , value = "状态【0不可用1可用】")
	private Integer available;
    

}
