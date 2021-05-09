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
 * @Description:TODO(流程控制实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="二维码验证",description="二维码验证")
public class ZxingVerify extends Model<ZxingVerify> {

	private static final long serialVersionUID = 1588060246511L;

	@TableId(value = "verify_id", type = IdType.UUID)
	@ApiModelProperty(value="主键",name="verifyId")
	private String verifyId;
    
	@ApiModelProperty(name = "verifyName" , value = "验证界面")
	private String verifyName;
    
	@ApiModelProperty(name = "verifyUrl" , value = "验证图片")
	private String verifyUrl;
    
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
	@ApiModelProperty(name = "deleteId" , value = "删除状态 不需要填入")
	private Integer deleteId;

	@ApiModelProperty(name = "types" , value = "验证图片")
	private String types;

}
