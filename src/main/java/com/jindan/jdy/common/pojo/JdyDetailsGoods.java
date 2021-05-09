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
import java.util.Date;

/**   
 * @Description:TODO(仓库基本信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyDetailsGoods extends Model<JdyDetailsGoods> {

	private static final long serialVersionUID = 1611541109854L;
	
	@ApiModelProperty(name = "mcid" , value = "")
	private String mcid;
    
	@ApiModelProperty(name = "wuliaoCode" , value = "物料编码")
	private String wuliaoCode;
    
	@ApiModelProperty(name = "wuliaoName" , value = "物料名称")
	private String wuliaoName;
    
	@ApiModelProperty(name = "wuliaoType" , value = "物料型号")
	private String wuliaoType;
    
	@ApiModelProperty(name = "nuits" , value = "单位")
	private String nuits;
    
	@ApiModelProperty(name = "pihao" , value = "批号")
	private String pihao;
    
	@ApiModelProperty(name = "nums" , value = "数量")
	private String nums;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    
	@ApiModelProperty(name = "wuliaoMode" , value = "型号")
	private String wuliaoMode;
    
	@ApiModelProperty(name = "wuliaoPrice" , value = "物料单价")
	private String wuliaoPrice;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "insertTime" , value = "")
	private Date insertTime;
    
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "updateTime" , value = "")
	private Date updateTime;
    
	@ApiModelProperty(name = "deleteId" , value = "")
	private Integer deleteId;
    

}
