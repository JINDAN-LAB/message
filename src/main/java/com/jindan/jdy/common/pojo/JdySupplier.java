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
@ApiModel(value="供应商",description="供应商")
public class JdySupplier extends Model<JdySupplier> {

	private static final long serialVersionUID = 1586871696571L;

	@TableId(value = "supplier_id", type = IdType.UUID)
	@ApiModelProperty(name = "supplierId" , value = "主键ID")
	private String supplierId;
    
	@ApiModelProperty(name = "supplierName" , value = "供应商名称")
	private String supplierName;
    
	@ApiModelProperty(name = "supplierLocation" , value = "供应商地址")
	private String supplierLocation;
    
	@ApiModelProperty(name = "supplierPerson" , value = "供应商联系人")
	private String supplierPerson;
    
	@ApiModelProperty(name = "supplierNumber" , value = "联系人电话")
	private String supplierNumber;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = " 新增日期 不需要设置")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "更新时间 不需要设置")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要设置")
	private Integer deleteId;
    
	@ApiModelProperty(name = "status" , value = "状态")
	private Integer status;

	@ApiModelProperty(name = "locationNumber" , value = "联系人")
	private String locationNumber;

}
