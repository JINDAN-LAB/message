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
public class SysUser extends Model<SysUser> {

	private static final long serialVersionUID = 1587954477273L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "name" , value = "")
	private String name;
    
	@ApiModelProperty(name = "loginname" , value = "")
	private String loginname;
    
	@ApiModelProperty(name = "address" , value = "")
	private String address;
    
	@ApiModelProperty(name = "sex" , value = "")
	private Integer sex;
    
	@ApiModelProperty(name = "remark" , value = "")
	private String remark;
    
	@ApiModelProperty(name = "pwd" , value = "")
	private String pwd;
    
	@ApiModelProperty(name = "deptid" , value = "")
	private Integer deptid;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "hiredate" , value = "")
	private Date hiredate;
    
	@ApiModelProperty(name = "mgr" , value = "")
	private Integer mgr;
    
	@ApiModelProperty(name = "available" , value = "")
	private Integer available;
    
	@ApiModelProperty(name = "ordernum" , value = "")
	private Integer ordernum;
    
	@ApiModelProperty(name = "type" , value = "用户类型[0超级管理员1，管理员，2普通用户]")
	private Integer type;
    
	@ApiModelProperty(name = "imgpath" , value = "头像地址")
	private String imgpath;
    
	@ApiModelProperty(name = "salt" , value = "")
	private String salt;
    

}
