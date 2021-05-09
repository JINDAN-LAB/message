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
 * @author: BianPeng
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="用户信息",description="用户信息")
public class JdyUser extends Model<JdyUser> {

	private static final long serialVersionUID = 1586846725836L;

	@TableId(value = "user_id", type = IdType.UUID)
	@ApiModelProperty(name = "userId" , value = "主键ID")
	private String userId;
    
	@ApiModelProperty(name = "username" , value = "用户名称")
	private String username;
    
	@ApiModelProperty(name = "password" , value = "密码")
	private String password;
    
	@ApiModelProperty(name = "wecharId" , value = "微信公众号ID")
	private String wecharId;
    
	@ApiModelProperty(name = "roleId" , value = "角色ID")
	private String roleId;
    
	@ApiModelProperty(name = "phone" , value = "手机号")
	private String phone;
    
	@ApiModelProperty(name = "email" , value = "邮箱")
	private String email;
    
	@ApiModelProperty(name = "power" , value = "权力")
	private String power;

	@ApiModelProperty(name = "status" , value = "状态")
	private String status;
    
	@ApiModelProperty(name = "icon" , value = "头像")
	private String icon;

	private String departments;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态   不要设置")
	private Integer deleteId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间  不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "新增日期  不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@ApiModelProperty(name = "sex" , value = "性别")
	private String sex;

	@ApiModelProperty(name = "gongsi" , value = "公司")
	private String gongsi;

    

}
