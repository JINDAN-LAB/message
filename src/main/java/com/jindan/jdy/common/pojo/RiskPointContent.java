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
 * @Description:TODO(风险控制点内容实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RiskPointContent extends Model<RiskPointContent> {

	private static final long serialVersionUID = 1607386599881L;

	@TableId(value = "risk_ids", type = IdType.UUID)
	@ApiModelProperty(name = "riskIds" , value = "")
	private String riskIds;

	@ApiModelProperty(name = "riskContents" , value = "风险管控内容")
	private String riskContents;
    
	@ApiModelProperty(name = "accidetCause" , value = "导致事故原因")
	private String accidetCause;
    
	@ApiModelProperty(name = "mayAccidet" , value = "可能导致后果")
	private String mayAccidet;
    
	@ApiModelProperty(name = "meetMeasure" , value = "应急处理措施")
	private String meetMeasure;
    
	@ApiModelProperty(name = "influenceSphere" , value = "影响范围")
	private String influenceSphere;
    
	@ApiModelProperty(name = "latentResult" , value = "潜在后果")
	private String latentResult;
    
	@ApiModelProperty(name = "pazhao" , value = "拍照")
	private String pazhao;
    
	@ApiModelProperty(name = "shuju" , value = "数据")
	private String shuju;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

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

	@ApiModelProperty(name = "jibie" , value = "管控级别")
	private String jibie;

	@ApiModelProperty(name = "weixianJibie" , value = "危险级别")
	private String weixianJibie;

	@ApiModelProperty(name = "rcstatus" , value = "状态信息")
	private String rcstatus;

	@ApiModelProperty(name = "shebeiId" , value = "设备ID")
	private String shebeiId;

	@ApiModelProperty(name = "shebeiName" , value = "绑定设备名称")
	private String shebeiName;

}
