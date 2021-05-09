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
 * @Description:TODO(自定义验证二维码域名目录实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="二维码执行标准",description="二维码执行标准")
public class ZxingStand extends Model<ZxingStand> {

	private static final long serialVersionUID = 1587910197550L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "standard" , value = "执行标准名称")
	private String standard;
    
	@ApiModelProperty(name = "standurl" , value = "执行标砖文件")
	private String standurl;
    
	@ApiModelProperty(name = "submitperson" , value = "提交人")
	private String submitperson;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入日期  不需要填入")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改时间 不需要填入")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态，不需要填入")
	private Integer deleteId;


}
