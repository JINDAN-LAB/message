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
@ApiModel(value="发货信息",description="发货信息")
public class DomesticFahuo extends Model<DomesticFahuo> {

	private static final long serialVersionUID = 1588822714062L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;

	@ApiModelProperty(name = "danjuhao" , value = "单据号")
	private String danjuhao;
    
	@ApiModelProperty(name = "danjuriqi" , value = "单据日期")
	private String danjuriqi;
    
	@ApiModelProperty(name = "yewuyuan" , value = "业务员")
	private String yewuyuan;
    
	@ApiModelProperty(name = "fangshi" , value = "运输方式")
	private String fangshi;
    
	@ApiModelProperty(name = "shouhuokehu" , value = "收获客户")
	private String shouhuokehu;
    
	@ApiModelProperty(name = "wuliaomingcheng" , value = "物料名称")
	private String wuliaomingcheng;
    
	@ApiModelProperty(name = "bzw" , value = "包装物")
	private String bzw;
    
	@ApiModelProperty(name = "shuliang" , value = "数量")
	private String shuliang;
    
	@ApiModelProperty(name = "hanshuidanjia" , value = "含税单价")
	private String hanshuidanjia;
    
	@ApiModelProperty(name = "jiashuiheji" , value = "加税合计")
	private String jiashuiheji;
    
	@ApiModelProperty(name = "yunshudanjia" , value = "运输单价")
	private String yunshudanjia;
    
	@ApiModelProperty(name = "bhbz" , value = "不含包装")
	private String bhbz;
    
	@ApiModelProperty(name = "shifouweixinkehu" , value = "新客户")
	private String shifouweixinkehu;
    
	@ApiModelProperty(name = "yongjinbili" , value = "佣金比例")
	private String yongjinbili;
    
	@ApiModelProperty(name = "yongjin" , value = "佣金")
	private String yongjin;
    
	@ApiModelProperty(name = "danhao" , value = "单号")
	private String danhao;
    
	@ApiModelProperty(name = "jijiaticheng" , value = "基价提成")
	private String jijiaticheng;
    
	@ApiModelProperty(name = "jijiatichengbili" , value = "基价提成比例")
	private String jijiatichengbili;
    
	@ApiModelProperty(name = "shoukuanxieyi" , value = "客户协议")
	private String shoukuanxieyi;
    
	@ApiModelProperty(name = "jisuanriqi" , value = "计算日期")
	private String jisuanriqi;
    
	@ApiModelProperty(name = "dayin" , value = "打印")
	private String dayin;
    
	@ApiModelProperty(name = "queren" , value = "状态")
	private String queren;
    
	@ApiModelProperty(name = "dayintime" , value = "打印时间")
	private String dayintime;

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


	@ApiModelProperty(name = "chanmofeiyong" , value = "费用")
	private String chanmofeiyong;



    

}
