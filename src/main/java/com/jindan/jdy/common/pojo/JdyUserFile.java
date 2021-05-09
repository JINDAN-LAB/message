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
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="文档管理",description="文档管理")
public class JdyUserFile extends Model<JdyUserFile> {

	private static final long serialVersionUID = 1587180612875L;

	@TableId(value = "file_id", type = IdType.UUID)
	@ApiModelProperty(name = "fileId" , value = "主键ID")
	private String fileId;
    
	@ApiModelProperty(name = "userFileName" , value = "用户文件名称")
	private String userFileName;
    
	@ApiModelProperty(name = "fileName" , value = "文件名称")
	private String fileName;
    
	@ApiModelProperty(name = "fileDescribe" , value = "文件描述")
	private String fileDescribe;


	@ApiModelProperty(name = "userId" , value = "用户Id")
	private String userId;

	@ApiModelProperty(name = "parentId" , value = "所属对象")
	private String parentId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "新增日期 不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态")
	private Integer deleteId;



}
