package com.jindan.jdy.common.dto;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class UserRoleDto extends Model<UserRoleDto> {

	private static final long serialVersionUID = 158684719588L;

	@TableId(value = "userRoleId", type = IdType.UUID)
	@ApiModelProperty(name = "userRoleId" , value = "")
	private String userRoleId;
    
	@ApiModelProperty(name = "userId" , value = "")
	private String userId;
    
	@ApiModelProperty(name = "roleId" , value = "")
	private String roleId;

	@ApiModelProperty(value = "插入时间")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@ApiModelProperty(value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	private Integer deleteId;

	private JdyUser jdyUser;

	private JdyRole jdyRole;


}
