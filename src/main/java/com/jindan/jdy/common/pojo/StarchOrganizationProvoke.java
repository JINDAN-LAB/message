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
 * @Description:TODO(资产调拨实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationProvoke extends Model<StarchOrganizationProvoke> {

	private static final long serialVersionUID = 1598232179899L;

	@TableId(value = "tid", type = IdType.UUID)
	@ApiModelProperty(name = "tid" , value = "主键ID")
	private String tid;
    
	@ApiModelProperty(name = "status" , value = "办理状态")
	private String status;
    
	@ApiModelProperty(name = "allotNumber" , value = "调拨单号")
	private String allotNumber;
    
	@ApiModelProperty(name = "allotTime" , value = "调拨日期")
	private String allotTime;
    
	@ApiModelProperty(name = "allotManager" , value = "调出管理员")
	private String allotManager;
    
	@ApiModelProperty(name = "allotCompany" , value = "调拨公司")
	private String allotCompany;
    
	@ApiModelProperty(name = "foldDate" , value = "调入日期")
	private String foldDate;
    
	@ApiModelProperty(name = "foldManagers" , value = "调入管理员")
	private String foldManagers;
    
	@ApiModelProperty(name = "foldCompany" , value = "调拨公司")
	private String foldCompany;
    
	@ApiModelProperty(name = "foldExplain" , value = "调拨说明")
	private String foldExplain;
    
	@ApiModelProperty(name = "foldPropertyId" , value = "相关ID")
	private String foldPropertyId;

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
