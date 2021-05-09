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
 * @Description:TODO(整改返回结果实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointZhenggaiResult extends Model<RiskPointZhenggaiResult> {

	private static final long serialVersionUID = 1607386760380L;

	@TableId(value = "zid", type = IdType.UUID)
	@ApiModelProperty(name = "zid" , value = "")
	private String zid;
    
	@ApiModelProperty(name = "zhenggaiTime" , value = "")
	private String zhenggaiTime;
    
	@ApiModelProperty(name = "zhenggaiRiqi" , value = "")
	private String zhenggaiRiqi;
    
	@ApiModelProperty(name = "zhenggaiCuoshi" , value = "")
	private String zhenggaiCuoshi;
    
	@ApiModelProperty(name = "zijin" , value = "")
	private String zijin;
    
	@ApiModelProperty(name = "imgurl1" , value = "")
	private String imgurl1;
    
	@ApiModelProperty(name = "imgurl2" , value = "")
	private String imgurl2;
    
	@ApiModelProperty(name = "imgurl3" , value = "")
	private String imgurl3;
    
	@ApiModelProperty(name = "fuzePerson" , value = "")
	private String fuzePerson;
    
	@ApiModelProperty(name = "status" , value = "")
	private String status;
    
	@ApiModelProperty(name = "yanshou" , value = "")
	private String yanshou;
    
	@ApiModelProperty(name = "yanshouTime" , value = "")
	private String yanshouTime;
    
	@ApiModelProperty(name = "yanshouName" , value = "")
	private String yanshouName;

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

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;

	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;


}
