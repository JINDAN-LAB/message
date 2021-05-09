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
 * @Description:TODO(二维码实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="二维码",description="二维码")
public class ZxingErweim extends Model<ZxingErweim> implements Serializable {

	private static final long serialVersionUID = 1587364835514L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "主键ID 自动生成")
	private String id;
    
	@ApiModelProperty(name = "nametitle" , value = "产品名称")
	private String nametitle;
    
	@ApiModelProperty(name = "suttle" , value = "净重")
	private String suttle;
    
	@ApiModelProperty(name = "standard" , value = "执行标准")
	private String standard;
    
	@ApiModelProperty(name = "tab" , value = "标号信息")
	private String tab;
    
	@ApiModelProperty(name = "kg" , value = "毛重")
	private String kg;

	@TableField("`type`")
	@ApiModelProperty(name = "type" , value = "类型")
	private String type;

	@TableField("`use`")
	@ApiModelProperty(name = "use" , value = "使用")
	private String use;
    
	@ApiModelProperty(name = "method" , value = "使用方法")
	private String method;
    
	@ApiModelProperty(name = "scope" , value = "")
	private String scope;

	@TableField("`condition`")
	@ApiModelProperty(name = "condition" , value = "")
	private String condition;
    
	@ApiModelProperty(name = "notice" , value = "注意事项")
	private String notice;
    
	@ApiModelProperty(name = "imgurl" , value = "二维码图片")
	private String imgurl;
    
	@ApiModelProperty(name = "username" , value = "")
	private String username;
    
	@ApiModelProperty(name = "md" , value = "加密文件")
	private String md;
    
	@ApiModelProperty(name = "tijiaotime" , value = "提交时间")
	private String tijiaotime;
    
	@ApiModelProperty(name = "pihao" , value = "批号信息")
	private String pihao;
    
	@ApiModelProperty(name = "hanliang" , value = "净含量")
	private String hanliang;
    
	@ApiModelProperty(name = "tradename" , value = "商品名")
	private String tradename;
    
	@ApiModelProperty(name = "ensurevalue" , value = "确认值")
	private String ensurevalue;
    
	@ApiModelProperty(name = "chengfenvalues" , value = "成分保证值")
	private String chengfenvalues;
    
	@ApiModelProperty(name = "typeid" , value = "特殊标签")
	private String typeid;
    
	@ApiModelProperty(name = "dateproduced" , value = "生成值")
	private String dateproduced;
    
	@ApiModelProperty(name = "materielcode" , value = "特殊标签")
	private String materielcode;
    
	@ApiModelProperty(name = "supplierid" , value = "供应商编码")
	private String supplierid;
    
	@ApiModelProperty(name = "guige" , value = "规格")
	private String guige;
    
	@ApiModelProperty(name = "suppliertype" , value = "供应商类型")
	private String suppliertype;
    
	@ApiModelProperty(name = "mulutype" , value = "")
	private Integer mulutype;
    
	@ApiModelProperty(name = "usertype" , value = "'1'")
	private String usertype;
    
	@ApiModelProperty(name = "piwen" , value = "批准文号")
	private String piwen;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态  不需要设置")
	private Integer deleteId;

	@TableField("`status`")
	@ApiModelProperty(name = "status" , value = "状态")
	private Integer status;

	@ApiModelProperty(name = "expirations" , value = "")
	private String expirations;


	@ApiModelProperty(name = "grades" , value = "")
	private String grades;
	@ApiModelProperty(name = "specifications" , value = "")
	private String specifications;
	@ApiModelProperty(name = "categorys" , value = "")
	private String categorys;
	@ApiModelProperty(name = "erpcode" , value = "ERP编码")
	private String erpcode;
	@ApiModelProperty(name = "sapcode" , value = "SAP编码")
	private String sapcode;
	@ApiModelProperty(name = "baozhuangGrades" , value = "包装规格")
	private String baozhuangGrades;

	@ApiModelProperty(name = "suppliers" , value = "")
	private String suppliers;

	@ApiModelProperty(name = "materialcode" , value = "")
	private String materialcode;

}
