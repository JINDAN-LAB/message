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
 * @Description:TODO(外贸提成实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTichengXishu extends Model<WaimaoTichengXishu> {

	private static final long serialVersionUID = 1592364793676L;

	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "xiankuan" , value = "")
	private String xiankuan;
    
	@ApiModelProperty(name = "qiyunxian" , value = "")
	private String qiyunxian;
    
	@ApiModelProperty(name = "chaoqiqiyunxian" , value = "")
	private String chaoqiqiyunxian;
    
	@ApiModelProperty(name = "huoyunxian" , value = "")
	private String huoyunxian;
    
	@ApiModelProperty(name = "chaoqihuoyunxian" , value = "")
	private String chaoqihuoyunxian;
    
	@ApiModelProperty(name = "buchaoqi1" , value = "")
	private String buchaoqi1;
    
	@ApiModelProperty(name = "buchaoqi2" , value = "")
	private String buchaoqi2;
    
	@ApiModelProperty(name = "chaoqi1" , value = "")
	private String chaoqi1;
    
	@ApiModelProperty(name = "chaoqi2" , value = "")
	private String chaoqi2;
    
	@ApiModelProperty(name = "jingshui1" , value = "")
	private String jingshui1;
    
	@ApiModelProperty(name = "jingshui2" , value = "")
	private String jingshui2;
    
	@ApiModelProperty(name = "jingshui3" , value = "")
	private String jingshui3;
    
	@ApiModelProperty(name = "jingshui3b" , value = "")
	private String jingshui3b;
    
	@ApiModelProperty(name = "xinkehu1" , value = "")
	private String xinkehu1;
    
	@ApiModelProperty(name = "xinkehu2" , value = "")
	private String xinkehu2;
    
	@ApiModelProperty(name = "xinkehu3" , value = "")
	private String xinkehu3;
    
	@ApiModelProperty(name = "xinkehu3b" , value = "")
	private String xinkehu3b;
    
	@ApiModelProperty(name = "wanwu" , value = "")
	private String wanwu;
    
	@ApiModelProperty(name = "waner" , value = "")
	private String waner;
    
	@ApiModelProperty(name = "yuefen" , value = "")
	private String yuefen;
    
	@ApiModelProperty(name = "chenglixibuchaoqi" , value = "")
	private String chenglixibuchaoqi;
    
	@ApiModelProperty(name = "chenglixichaoqi" , value = "")
	private String chenglixichaoqi;

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
	@ApiModelProperty(name = "deleteId" , value = "删除状态")
	private Integer deleteId;

}
