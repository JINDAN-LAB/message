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
@ApiModel(value="计算系数",description="计算系数")
public class DomesticXishu extends Model<DomesticXishu> {

	private static final long serialVersionUID = 1588822762686L;
	
	@TableId(value = "id", type = IdType.UUID)
	@ApiModelProperty(name = "id" , value = "")
	private Integer id;
    
	@ApiModelProperty(name = "xiankuan" , value = "先款奖")
	private String xiankuan;
    
	@ApiModelProperty(name = "qiyunxian" , value = "汽运先款奖")
	private String qiyunxian;
    
	@ApiModelProperty(name = "chaoqiqiyunxian" , value = "汽运超期")
	private String chaoqiqiyunxian;
    
	@ApiModelProperty(name = "huoyunxian" , value = "火运先")
	private String huoyunxian;
    
	@ApiModelProperty(name = "chaoqihuoyunxian" , value = "超期火运")
	private String chaoqihuoyunxian;
    
	@ApiModelProperty(name = "buchaoqi1" , value = "不超期1")
	private String buchaoqi1;

	@ApiModelProperty(name = "buchaoqi2" , value = "不超期1")
	private String buchaoqi2;

	@ApiModelProperty(name = "chaoqi1" , value = "超期1")
	private String chaoqi1;
    
	@ApiModelProperty(name = "chaoqi2" , value = "超期2")
	private String chaoqi2;
    
	@ApiModelProperty(name = "jingshui1" , value = "净水价1")
	private String jingshui1;
    
	@ApiModelProperty(name = "jingshui2" , value = "净水价2")
	private String jingshui2;
    
	@ApiModelProperty(name = "jingshui3" , value = "净水价3")
	private String jingshui3;
    
	@ApiModelProperty(name = "jingshui3b" , value = "净水价4")
	private String jingshui3b;
    
	@ApiModelProperty(name = "xinkehu1" , value = "新客户1")
	private String xinkehu1;
    
	@ApiModelProperty(name = "xinkehu2" , value = "新客户2")
	private String xinkehu2;
    
	@ApiModelProperty(name = "xinkehu3" , value = "新客户3")
	private String xinkehu3;
    
	@ApiModelProperty(name = "xinkehu3b" , value = "新客户3B")
	private String xinkehu3b;
    
	@ApiModelProperty(name = "wanwu" , value = "万五")
	private String wanwu;
    
	@ApiModelProperty(name = "waner" , value = "万二")
	private String waner;
    
	@ApiModelProperty(name = "yuefen" , value = "运费")
	private String yuefen;
    
	@ApiModelProperty(name = "chenglixibuchaoqi" , value = "成兑利息不超期")
	private String chenglixibuchaoqi;
    
	@ApiModelProperty(name = "chenglixichaoqi" , value = "成兑利息超期")
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
