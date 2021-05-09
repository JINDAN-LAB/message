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
 * @Description:TODO(食品安全人员管理实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyAppletFootSafetyPerson extends Model<JdyAppletFootSafetyPerson> {

	private static final long serialVersionUID = 1596247147501L;

	@TableId(value = "pid", type = IdType.UUID)
	@ApiModelProperty(name = "pid" , value = "")
	private String pid;
    
	@ApiModelProperty(name = "username" , value = "")
	private String username;
    
	@ApiModelProperty(name = "password" , value = "")
	private String password;
    
	@ApiModelProperty(name = "departments" , value = "")
	private String departments;
    
	@ApiModelProperty(name = "chejian" , value = "")
	private String chejian;
    
	@ApiModelProperty(name = "gongduan" , value = "")
	private String gongduan;
    
	@ApiModelProperty(name = "banzu" , value = "")
	private String banzu;
    
	@ApiModelProperty(name = "quanxian" , value = "班组，车间，总监，公司")
	private String quanxian;

	@ApiModelProperty(name = "zongjianid" , value = "总监ID")
	private String zongjianid;

	@ApiModelProperty(name = "gongsi" , value = "公司ID")
	private String gongsi;
	
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

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;


}
