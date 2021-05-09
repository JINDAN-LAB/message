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

/**   
 * @Description:TODO(销售表头信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyMarket extends Model<JdyMarket> {

	private static final long serialVersionUID = 1610346856522L;
	
	@ApiModelProperty(name = "mid" , value = "")
	private String mid;
    
	@ApiModelProperty(name = "hetongNumber" , value = "合同编号")
	private String hetongNumber;
    
	@ApiModelProperty(name = "hetongName" , value = "合同名称")
	private String hetongName;
    
	@ApiModelProperty(name = "hetongType" , value = "合同类型")
	private String hetongType;
    
	@ApiModelProperty(name = "hetongSignTime" , value = "合同签订日期")
	private String hetongSignTime;
    
	@ApiModelProperty(name = "planEffectDate" , value = "计划生效日期")
	private String planEffectDate;
    
	@ApiModelProperty(name = "planEndDate" , value = "计划终止日期")
	private String planEndDate;
    
	@ApiModelProperty(name = "clients" , value = "客户")
	private String clients;
    
	@ApiModelProperty(name = "marketPersons" , value = "销售人员")
	private String marketPersons;
    
	@ApiModelProperty(name = "marketDepartment" , value = "销售部门")
	private String marketDepartment;
    
	@ApiModelProperty(name = "bizhong" , value = "币种")
	private String bizhong;
    
	@ApiModelProperty(name = "xieyi" , value = "收款协议")
	private String xieyi;
    
	@ApiModelProperty(name = "hetongStatus" , value = "合同状态")
	private String hetongStatus;
    
	@ApiModelProperty(name = "leijiSumjine" , value = "累计收款总额")
	private String leijiSumjine;
    
	@ApiModelProperty(name = "zongSum" , value = "总数量")
	private String zongSum;
    
	@ApiModelProperty(name = "jiashuiheji" , value = "价税合计")
	private String jiashuiheji;
    
	@ApiModelProperty(name = "transType" , value = "运输方式")
	private String transType;
    
	@ApiModelProperty(name = "accountDeadline" , value = "结算期限")
	private String accountDeadline;
    
	@ApiModelProperty(name = "submitPersons" , value = "")
	private String submitPersons;
    
	@ApiModelProperty(name = "shenpiPersons" , value = "")
	private String shenpiPersons;
    
	@ApiModelProperty(name = "submitTimes" , value = "")
	private String submitTimes;
    
	@ApiModelProperty(name = "shenpiTimes" , value = "")
	private String shenpiTimes;
    

}
