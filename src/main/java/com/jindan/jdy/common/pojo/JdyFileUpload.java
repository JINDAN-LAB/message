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
@ApiModel(value="停用",description="停用")
public class JdyFileUpload extends Model<JdyFileUpload> {

	private static final long serialVersionUID = 1586873878778L;

	@TableId(value = "fileUploadId", type = IdType.UUID)
	@ApiModelProperty(name = "fileUploadId" , value = "")
	private String fileUploadId;
    
	@ApiModelProperty(name = "filename" , value = "文件名")
	private String filename;
    
	@ApiModelProperty(name = "fileurl1" , value = "对象存储")
	private String fileurl1;
    
	@ApiModelProperty(name = "fileurl2" , value = "")
	private String fileurl2;
    
	@ApiModelProperty(name = "fileurl3" , value = "")
	private String fileurl3;
    
	@ApiModelProperty(name = "fileurl4" , value = "")
	private String fileurl4;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;

	@ApiModelProperty(name = "userId" , value = "")
	private String userId;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

	@ApiModelProperty(name = "fileDescribe" , value = "")
	private String fileDescribe;


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;


}
