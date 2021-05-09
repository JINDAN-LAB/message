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
import java.math.BigDecimal;
import java.util.Date;

/**   
 * @Description:TODO(耗材资产出入库内容实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationPutConsumableChuru extends Model<StarchOrganizationPutConsumableChuru> {

	private static final long serialVersionUID = 1600052206458L;

	@TableId(value = "cids", type = IdType.UUID)
	@ApiModelProperty(name = "cids" , value = "")
	private String cids;

	@ApiModelProperty(name = "churu" , value = "")
	private String churu;

	@ApiModelProperty(name = "imgurl" , value = "")
	private String imgurl;

	@ApiModelProperty(name = "guige" , value = "")
	private String guige;

	@ApiModelProperty(name = "danwei" , value = "")
	private String danwei;

	@ApiModelProperty(name = "num" , value = "")
	private Integer num;

	@ApiModelProperty(name = "unitPrice" , value = "")
	private BigDecimal unitPrice;

	@ApiModelProperty(name = "numJine" , value = "")
	private String numJine;

	@ApiModelProperty(name = "remarks" , value = "")
	private String remarks;

	@ApiModelProperty(name = "anquanDowm" , value = "")
	private String anquanDowm;

	@ApiModelProperty(name = "anquanUp" , value = "")
	private String anquanUp;

	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;


	@ApiModelProperty(name = "bianma" , value = "")
	private String bianma;

	@ApiModelProperty(name = "names" , value = "")
	private String names;

	@ApiModelProperty(name = "pici" , value = "")
	private String pici;

	@ApiModelProperty(name = "erweima" , value = "")
	private String erweima;

	@ApiModelProperty(name = "parentIds" , value = "")
	private String parentIds;

	@ApiModelProperty(name = "status" , value = "已确认，未确认")
	private String status;

	@ApiModelProperty(name = "ptype" , value = "已确认，未确认")
	private String ptype;

	@ApiModelProperty(name = "ptypes" , value = "")
	private String ptypes;

	@ApiModelProperty(name = "departments" , value = "")
	private String departments;

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



}
