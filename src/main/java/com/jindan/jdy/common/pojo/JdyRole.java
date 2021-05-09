package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Options;
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
@ApiModel(value="角色信息",description="角色信息")
public class JdyRole extends Model<JdyRole> {

	private static final long serialVersionUID = 1586847121846L;


	@TableId(value = "role_id", type = IdType.UUID)
	@ApiModelProperty(name = "roleId" , value = "主键id")
	private String roleId;
    
	@ApiModelProperty(name = "roleName" , value = "角色名称")
	private String roleName;
    
	@ApiModelProperty(name = "roleDescribe" , value = "角色描述")
	private String roleDescribe;

	@ApiModelProperty(value = "插入时间 不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@ApiModelProperty(value = "更新时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要设置")
	@TableLogic
	private Integer deleteId;


}
