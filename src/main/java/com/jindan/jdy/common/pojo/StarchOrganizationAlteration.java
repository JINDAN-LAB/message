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
 * @Description:TODO(资产信息变更实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationAlteration extends Model<StarchOrganizationAlteration> {

	private static final long serialVersionUID = 1598232777512L;

	@TableId(value = "aid", type = IdType.UUID)
	@ApiModelProperty(name = "aid" , value = "主键ID")
	private String aid;
    
	@ApiModelProperty(name = "astatus" , value = "办理状态")
	private String astatus;
    
	@ApiModelProperty(name = "alterationNumber" , value = "变更单号")
	private Integer alterationNumber;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "alterationDate" , value = "业务日期")
	private Date alterationDate;
    
	@ApiModelProperty(name = "alterationPerson" , value = "处理人")
	private String alterationPerson;
    
	@ApiModelProperty(name = "alterationType" , value = "资产类别")
	private String alterationType;
    
	@ApiModelProperty(name = "alterationName" , value = "资产名称")
	private String alterationName;
    
	@ApiModelProperty(name = "alterationEares" , value = "区域")
	private String alterationEares;
    
	@ApiModelProperty(name = "depositRegion" , value = "存放地点")
	private String depositRegion;
    
	@ApiModelProperty(name = "useCompany" , value = "使用公司")
	private String useCompany;
    
	@ApiModelProperty(name = "useDepartment" , value = "使用部门")
	private String useDepartment;
    
	@ApiModelProperty(name = "usePerson" , value = "使用人")
	private String usePerson;
    
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;

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
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;
    

}
