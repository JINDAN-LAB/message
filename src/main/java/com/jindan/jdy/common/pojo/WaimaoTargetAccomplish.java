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
 * @Description:TODO(外贸目标完成实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTargetAccomplish extends Model<WaimaoTargetAccomplish> {

	private static final long serialVersionUID = 1603856521533L;

	@TableId(value = "wmid")
	@ApiModelProperty(name = "wmid" , value = "")
	private String wmid;
    
	@ApiModelProperty(name = "wperson" , value = "")
	private String wperson;
    
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
    
	@ApiModelProperty(name = "zongsum" , value = "")
	private String zongsum;
    
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	private Date updateTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	private Date insertTime;
    

}
