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
@ApiModel(value="重点1项目",description="重点1项目")
public class KeyPointProject extends Model<KeyPointProject> {

	private static final long serialVersionUID = 1588819386503L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "titles" , value = "标题")
	private String titles;
    
	@ApiModelProperty(name = "contents" , value = "内容信息")
	private String contents;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要修改")
	private Integer deleteId;
    
	@ApiModelProperty(name = "status" , value = "状态信息")
	private String status;
    
	@ApiModelProperty(name = "parentFile" , value = "文档信息")
	private String parentFile;
    
	@ApiModelProperty(name = "deparement" , value = "部门信息")
	private String deparement;
    
	@ApiModelProperty(name = "persons" , value = "提交人")
	private String persons;
    
	@ApiModelProperty(name = "remartks" , value = "备注信息")
	private String remartks;

	@ApiModelProperty(name = "submitTime" , value = "提交时间")
	private String submitTime;


}
