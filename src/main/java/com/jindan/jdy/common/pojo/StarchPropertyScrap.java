package com.jindan.jdy.common.pojo;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description:TODO(清理报废实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchPropertyScrap extends Model<StarchPropertyScrap> {

	private static final long serialVersionUID = 1598233957004L;

	@TableId(value = "bid", type = IdType.UUID)
	@ApiModelProperty(name = "bid" , value = "主键ID")
	private String bid;
    
	@ApiModelProperty(name = "status" , value = "办理状态")
	private String status;
    
	@ApiModelProperty(name = "clearNumber" , value = "清理单号")
	private String clearNumber;
    
	@ApiModelProperty(name = "clearDate" , value = "清理日期")
	private String clearDate;
    
	@ApiModelProperty(name = "clearPerson" , value = "清理人")
	private String clearPerson;
    
	@ApiModelProperty(name = "clearExplain" , value = "清理说明")
	private String clearExplain;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间  不要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@JsonIgnore
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@JsonIgnore
	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
