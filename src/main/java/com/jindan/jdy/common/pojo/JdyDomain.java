package com.jindan.jdy.common.pojo;




import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Date;

/**
 * @Description:TODO(自定义验证二维码域名目录实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="暂定",description="暂定")
public class JdyDomain extends Model<JdyDomain> {

	private static final long serialVersionUID = 1587864827918L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "domain" , value = "主要")
	private String domain;

	@ApiModelProperty(name = "insertTime" , value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;
	
	@ApiModelProperty(name = "updateTime" , value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@ApiModelProperty(name = "status" , value = "")
	private String status;

	@ApiModelProperty(name = "errorUrl" , value = "")
	private String errorUrl;

	@ApiModelProperty(name = "successUrl" , value = "")
	private String successUrl;



}
