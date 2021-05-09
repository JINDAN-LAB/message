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
@ApiModel(value="重点落实",description="重点落实")
public class KeyPointPracticable extends Model<KeyPointPracticable> {

	private static final long serialVersionUID = 1588819013L;

	@TableId(value = "pid", type = IdType.UUID)
	@ApiModelProperty(name = "pid" , value = "")
	private String pid;
    
	@ApiModelProperty(name = "ptitles" , value = "标题")
	private String ptitles;
    
	@ApiModelProperty(name = "pcontents" , value = "内容")
	private String pcontents;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态 不需要传入")
	private Integer deleteId;
    
	@ApiModelProperty(name = "premarks" , value = "备注信息")
	private String premarks;
    
	@ApiModelProperty(name = "pstatus" , value = "状态信息")
	private String pstatus;

	@ApiModelProperty(name = "parentId" , value = "信息")
	private String parentId;


	@ApiModelProperty(name = "pfileId" , value = "状态信息")
	private String pfileId;

	@ApiModelProperty(name = "ppersons" , value = "人员信息")
	private String ppersons;



}
