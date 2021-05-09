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
 * @Description:TODO(小程序商品实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyCommodityApplet extends Model<JdyCommodityApplet> {

	private static final long serialVersionUID = 1592441922389L;

	@TableId(value = "xid", type = IdType.UUID)
	@ApiModelProperty(name = "xid" , value = "")
	private String xid;
    
	@ApiModelProperty(name = "xtitle" , value = "")
	private String xtitle;

	@ApiModelProperty(name = "xcontent" , value = "")
	private String xcontent;
    
	@ApiModelProperty(name = "xtime" , value = "")
	private String xtime;
    
	@ApiModelProperty(name = "xperson" , value = "")
	private String xperson;
    
	@ApiModelProperty(name = "ximgurl1" , value = "")
	private String ximgurl1;
    
	@ApiModelProperty(name = "ximgurl2" , value = "")
	private String ximgurl2;
    
	@ApiModelProperty(name = "ximgurl3" , value = "")
	private String ximgurl3;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "创建时间")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;


}
