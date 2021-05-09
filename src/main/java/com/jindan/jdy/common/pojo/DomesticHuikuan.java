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
 * @Description:TODO(内贸提成实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="回款信息",description="回款信息")
public class DomesticHuikuan extends Model<DomesticHuikuan> {

	private static final long serialVersionUID = 1588822729307L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键")
	private Integer id;
    
	@ApiModelProperty(name = "huikuanriqi" , value = "回款日期")
	private String huikuanriqi;
    
	@ApiModelProperty(name = "xingming" , value = "名称")
	private String xingming;
    
	@ApiModelProperty(name = "kehumingcheng" , value = "客户名称")
	private String kehumingcheng;
    
	@ApiModelProperty(name = "jine" , value = "回款金额")
	private String jine;
    
	@ApiModelProperty(name = "jine2" , value = "备用金额")
	private String jine2;
    
	@ApiModelProperty(name = "shijishiyongjine" , value = "实际使用金额")
	private String shijishiyongjine;
    
	@ApiModelProperty(name = "chengduiriqi" , value = "承兑日期")
	private String chengduiriqi;
    
	@ApiModelProperty(name = "xiankuanjiang" , value = "先款奖")
	private String xiankuanjiang;
    
	@ApiModelProperty(name = "lixijiang" , value = "利息奖")
	private String lixijiang;
    
	@ApiModelProperty(name = "xiankuanjiangbili" , value = "先款奖比例")
	private String xiankuanjiangbili;
    
	@ApiModelProperty(name = "lixijiangbili" , value = "利息奖比例")
	private String lixijiangbili;
    
	@ApiModelProperty(name = "biaoshi" , value = "标识")
	private String biaoshi;
    
	@ApiModelProperty(name = "yufutianshu" , value = "预付天数")
	private String yufutianshu;
    
	@ApiModelProperty(name = "daorutime" , value = "单据日期")
	private String daorutime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
	@TableField(fill = FieldFill.INSERT)
	private Date insertTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;

	@TableLogic
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
