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
 * @Description:TODO(资产入库实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StarchOrganizationPut extends Model<StarchOrganizationPut> {

	private static final long serialVersionUID = 1598230789681L;

	@TableId(value = "oid", type = IdType.UUID)
	@ApiModelProperty(name = "oid" , value = "主键ID")
	private String oid;

	@ApiModelProperty(name = "status" , value = "状态")
	private String status;
    
	@ApiModelProperty(name = "imgurl" , value = "图片地址")
	private String imgurl;
    
	@ApiModelProperty(name = "erweima" , value = "二维码")
	private String erweima;
    
	@ApiModelProperty(name = "propertyName" , value = "资产名称")
	private String propertyName;
    
	@ApiModelProperty(name = "propertyType" , value = "资产类别")
	private String propertyType;
    
	@ApiModelProperty(name = "propertyGuige" , value = "资产规格")
	private String propertyGuige;
    
	@ApiModelProperty(name = "propertySn" , value = "资产SN码")
	private String propertySn;
    
	@ApiModelProperty(name = "propertyNums" , value = "设备数量")
	private String propertyNums;
    
	@ApiModelProperty(name = "propertyJine" , value = "金额")
	private String propertyJine;
    
	@ApiModelProperty(name = "companys" , value = "使用公司")
	private String companys;
    
	@ApiModelProperty(name = "departments" , value = "使用部门")
	private String departments;
    
	@ApiModelProperty(name = "usePerson" , value = "使用人")
	private String usePerson;

	@ApiModelProperty(name = "areas" , value = "地区")
	private String areas;
    
	@ApiModelProperty(name = "storeAreas" , value = "存放地址")
	private String storeAreas;
    
	@ApiModelProperty(name = "managerPerson" , value = "管理员")
	private String managerPerson;
    
	@ApiModelProperty(name = "belongCompany" , value = "所属公司")
	private String belongCompany;
    
	@ApiModelProperty(name = "purchaseTime" , value = "购买时间")
	private String purchaseTime;
    
	@ApiModelProperty(name = "suppliers" , value = "供应商")
	private String suppliers;
    
	@ApiModelProperty(name = "serviceLife" , value = "使用期限")
	private String serviceLife;

	@ApiModelProperty(name = "creatorPerson" , value = "创建人")
	private String creatorPerson;
    
	@ApiModelProperty(name = "sourceOf" , value = "来源")
	private String sourceOf;


	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;

	@ApiModelProperty(name = "baoyang" , value = "保养类型")
	private String baoyang;


	@ApiModelProperty(name = "runhua" , value = "润滑")
	private String runhua;


	@ApiModelProperty(name = "canshu" , value = "参数")
	private String canshu;

	@ApiModelProperty(name = "bianma" , value = "编码")
	private String bianma;

	@ApiModelProperty(name = "xuhao" , value = "序号")
	private String xuhao;

	@ApiModelProperty(name = "weihao" , value = "位号")
	private String weihao;

	@ApiModelProperty(name = "shangcibaoyang" , value = "上次保养")
	private String shangcibaoyang;

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
    
	@ApiModelProperty(name = "maintainCycle" , value = "维修周期")
	private String maintainCycle;
    
	@ApiModelProperty(name = "spareIs" , value = "是否有备品备件")
	private String spareIs;
    
	@ApiModelProperty(name = "parentId" , value = "父类ID")
	private String parentId;

	@ApiModelProperty(name = "weixiuren" , value = "维修人")
	private String weixiuren;

	@ApiModelProperty(name = "jianxiuren" , value = "检修人")
	private String jianxiuren;

	@ApiModelProperty(name = "jixiugong" , value = "机修工")
	private String jixiugong;

	@ApiModelProperty(name = "ptypes" , value = "是否已经领用")
	private String ptypes;

	@ApiModelProperty(name = "ptype" , value = "是否已经领用")
	private String ptype;

	@ApiModelProperty(name = "pinpai" , value = "品牌")
	private String pinpai;

	@ApiModelProperty(name = "countingUnit" , value = "计数单位")
	private String countingUnit;

	@ApiModelProperty(name = "powers" , value = "功率")
	private String powers;

	@ApiModelProperty(name = "caizhi" , value = "材质")
	private String caizhi;




}
