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
 * @Description:TODO(资产子设备信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyCompany extends Model<JdyCompany> {

	private static final long serialVersionUID = 1602571409106L;

	@TableId(value = "ids", type = IdType.UUID)
	@ApiModelProperty(name = "ids" , value = "")
	private String ids;
    
	@ApiModelProperty(name = "companyName" , value = "公司名称")
	private String companyName;
    
	@ApiModelProperty(name = "companyPerson" , value = "公司法人")
	private String companyPerson;
    
	@ApiModelProperty(name = "yingyeZhizhao" , value = "营业执照")
	private String yingyeZhizhao;
    
	@ApiModelProperty(name = "personsNumbers" , value = "电话")
	private String personsNumbers;
    
	@ApiModelProperty(name = "shuihao" , value = "税号")
	private String shuihao;

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
