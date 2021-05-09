package com.jindan.jdy.common.dto;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.ZxingMethod;
import com.jindan.jdy.common.pojo.ZxingStand;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

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
public class ZxingErweimDto  extends PageVO {

	private static final long serialVersionUID = 1587364835514L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private String id;
    
	@ApiModelProperty(name = "nametitle" , value = "")
	private String nametitle;
    
	@ApiModelProperty(name = "suttle" , value = "")
	private String suttle;
    
	@ApiModelProperty(name = "standard" , value = "")
	private String standard;
    
	@ApiModelProperty(name = "tab" , value = "")
	private String tab;
    
	@ApiModelProperty(name = "kg" , value = "")
	private String kg;

	@ApiModelProperty(name = "type" , value = "")
	private String type;
    
	@ApiModelProperty(name = "use" , value = "")
	private String use;
    
	@ApiModelProperty(name = "method" , value = "")
	private String method;
    
	@ApiModelProperty(name = "scope" , value = "")
	private String scope;
    
	@ApiModelProperty(name = "condition" , value = "")
	private String condition;
    
	@ApiModelProperty(name = "notice" , value = "")
	private String notice;
    
	@ApiModelProperty(name = "imgurl" , value = "")
	private String imgurl;
    
	@ApiModelProperty(name = "username" , value = "")
	private String username;
    
	@ApiModelProperty(name = "md" , value = "")
	private String md;
    
	@ApiModelProperty(name = "tijiaotime" , value = "")
	private String tijiaotime;
    
	@ApiModelProperty(name = "pihao" , value = "")
	private String pihao;
    
	@ApiModelProperty(name = "hanliang" , value = "")
	private String hanliang;
    
	@ApiModelProperty(name = "tradename" , value = "")
	private String tradename;
    
	@ApiModelProperty(name = "ensurevalue" , value = "")
	private String ensurevalue;
    
	@ApiModelProperty(name = "chengfenvalues" , value = "")
	private String chengfenvalues;
    
	@ApiModelProperty(name = "typeid" , value = "")
	private String typeid;
    
	@ApiModelProperty(name = "dateproduced" , value = "")
	private String dateproduced;
    
	@ApiModelProperty(name = "materielcode" , value = "")
	private String materielcode;
    
	@ApiModelProperty(name = "supplierid" , value = "")
	private String supplierid;
    
	@ApiModelProperty(name = "guige" , value = "")
	private String guige;
    
	@ApiModelProperty(name = "suppliertype" , value = "")
	private String suppliertype;
    
	@ApiModelProperty(name = "mulutype" , value = "")
	private Integer mulutype;
    
	@ApiModelProperty(name = "usertype" , value = "'1'")
	private String usertype;
    
	@ApiModelProperty(name = "piwen" , value = "")
	private String piwen;

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

	@ApiModelProperty(name = "status" , value = "")
	private Integer status;


	@ApiModelProperty(name = "expirations" , value = "")
	private String expirations;

	@ApiModelProperty(name = "grades" , value = "")
	private String grades;
	@ApiModelProperty(name = "specifications" , value = "")
	private String specifications;
	@ApiModelProperty(name = "categorys" , value = "")
	private String categorys;
	@ApiModelProperty(name = "erpcode" , value = "")
	private String erpcode;
	@ApiModelProperty(name = "sapcode" , value = "")
	private String sapcode;
	@ApiModelProperty(name = "baozhuangGrades" , value = "")
	private String baozhuangGrades;

	@ApiModelProperty(name = "suppliers" , value = "")
	private String suppliers;

	@ApiModelProperty(name = "materialcode" , value = "")
	private String materialcode;

	ZxingMethod zxingMethod;

	ZxingStand zxingStand;



}
