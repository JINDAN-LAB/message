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
@ApiModel(value="权限信息",description="权限信息")
public class UserPermission extends Model<UserPermission> {

	private static final long serialVersionUID = 1586847138953L;

	@TableId(value = "permission_id", type = IdType.UUID)
	@ApiModelProperty(name = "permissionId" , value = "主键ID")
	private String permissionId;
    
	@ApiModelProperty(name = "permission" , value = "权限")
	private String permission;
    
	@ApiModelProperty(name = "permissionName" , value = "权限名称")
	private String permissionName;

	@ApiModelProperty(value = "插入时间 不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@ApiModelProperty(value = "更新时间  不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	private Integer deleteId;




}
