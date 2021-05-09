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
 * @Description:TODO(API应用KEY实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="角色对应权限",description="角色对应权限")
public class UserRolePermission extends Model<UserRolePermission> implements  Serializable {

	private static final long serialVersionUID = 1586847159629L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "roleId" , value = "角色ID")
	private String roleId;
    
	@ApiModelProperty(name = "permissionId" , value = "权限ID")
	private String permissionId;

	@ApiModelProperty(value = "插入时间 不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@ApiModelProperty(value = "更新时间  不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@ApiModelProperty(value = "删除状态  不需要设置")
	@TableLogic
	private Integer deleteId;


}
