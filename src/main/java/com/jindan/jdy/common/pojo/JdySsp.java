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
 * @Description:TODO(二维码目录实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="随手拍",description="随手拍")
public class JdySsp extends Model<JdySsp> {

	private static final long serialVersionUID = 1587638326028L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private String id;
    
	@ApiModelProperty(name = "content" , value = "内容")
	private String content;
    
	@ApiModelProperty(name = "locations" , value = "地址")
	private String locations;
    
	@ApiModelProperty(name = "imgurl" , value = "图片地址")
	private String imgurl;

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
    
	@ApiModelProperty(name = "userId" , value = "用户ID")
	private String userId;
    
	@ApiModelProperty(name = "status" , value = "状态")
	private String status;
    
	@ApiModelProperty(name = "chuliTime" , value = "处理时长")
	private String chuliTime;
    
	@ApiModelProperty(name = "resultPer" , value = "处理人")
	private String resultPer;
    
	@ApiModelProperty(name = "exceedTime" , value = "是否逾期")
	private String exceedTime;
    
	@ApiModelProperty(name = "resultUrl" , value = "结束图片")
	private String resultUrl;
    


	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "是否删除")
	private Integer deleteId;
    
	@ApiModelProperty(name = "resultContent" , value = "反馈内容")
	private String resultContent;


}
