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
 * @Description:TODO(重点项目实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="重点项目进度",description="重点项目进度")
public class KeyPointSchedule extends Model<KeyPointSchedule> {

	private static final long serialVersionUID = 1588819397218L;

	@TableId(value = "sid", type = IdType.UUID)
	@ApiModelProperty(name = "sid" , value = "主键ID")
	private String sid;
    
	@ApiModelProperty(name = "stitles" , value = "标题")
	private String stitles;
    
	@ApiModelProperty(name = "scontents" , value = "内容")
	private String scontents;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要设置")
	private Integer deleteId;
    
	@ApiModelProperty(name = "spersons" , value = "提交人")
	private String spersons;
    
	@ApiModelProperty(name = "sstatus" , value = "装态信息")
	private String sstatus;
    
	@ApiModelProperty(name = "sremarks" , value = "备注")
	private String sremarks;

	@ApiModelProperty(name = "parentId" , value = "信息")
	private String parentId;

	@ApiModelProperty(name = "sfileId" , value = "状态信息")
	private String sfileId;


}
