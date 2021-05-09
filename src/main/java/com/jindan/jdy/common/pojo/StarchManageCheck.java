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
 * @Description:TODO(盘点管理实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchManageCheck extends Model<StarchManageCheck> {

	private static final long serialVersionUID = 1598234595686L;

	@TableId(value = "cid", type = IdType.UUID)
	@ApiModelProperty(name = "cid" , value = "主键ID")
	private String cid;
    
	@ApiModelProperty(name = "checkName" , value = "盘点名称")
	private String checkName;
    
	@ApiModelProperty(name = "checkTakeStock" , value = "手工盘点")
	private String checkTakeStock;
    
	@ApiModelProperty(name = "creckPerson" , value = "创建人")
	private String creckPerson;
    
	@ApiModelProperty(name = "creationDate" , value = "创建日期")
	private String creationDate;
    
	@ApiModelProperty(name = "creationStatus" , value = "盘点状态")
	private String creationStatus;

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
    
	@ApiModelProperty(name = "checkCompany" , value = "检查公司")
	private String checkCompany;
    
	@ApiModelProperty(name = "belongCompany" , value = "属于公司")
	private String belongCompany;
    
	@ApiModelProperty(name = "zichanType" , value = "资产类别")
	private String zichanType;
    
	@ApiModelProperty(name = "quyu" , value = "区域")
	private String quyu;
    
	@ApiModelProperty(name = "managers" , value = "管理人")
	private String managers;


    

}
