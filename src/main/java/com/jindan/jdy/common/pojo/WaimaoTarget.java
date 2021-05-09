package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description:TODO(设备维修申报实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTarget extends Model<WaimaoTarget> {

	private static final long serialVersionUID = 1591000331811L;

	@TableId(value = "wtid")
	@ApiModelProperty(name = "wtid" , value = "")
	private String wtid;

	@ApiModelProperty(name = "waimaoPerson" , value = "")
	private String waimaoPerson;
    
	@ApiModelProperty(name = "yiyue" , value = "")
	private String yiyue;
    
	@ApiModelProperty(name = "eryue" , value = "")
	private String eryue;
    
	@ApiModelProperty(name = "sanyue" , value = "")
	private String sanyue;
    
	@ApiModelProperty(name = "siyue" , value = "")
	private String siyue;
    
	@ApiModelProperty(name = "wuyue" , value = "")
	private String wuyue;
    
	@ApiModelProperty(name = "liuyue" , value = "")
	private String liuyue;
    
	@ApiModelProperty(name = "qiyue" , value = "")
	private String qiyue;
    
	@ApiModelProperty(name = "bayue" , value = "")
	private String bayue;
    
	@ApiModelProperty(name = "jiuyue" , value = "")
	private String jiuyue;
    
	@ApiModelProperty(name = "shiyue" , value = "")
	private String shiyue;
    
	@ApiModelProperty(name = "shiyiyue" , value = "")
	private String shiyiyue;
    
	@ApiModelProperty(name = "shieryue" , value = "")
	private String shieryue;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	private Date updateTime;
    
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
