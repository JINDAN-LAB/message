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
 * @Description:TODO(仓库目录信息实体类)
 * 
 * @version: V1.0
 * @author: kong
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyCommodityCatalog extends Model<JdyCommodityCatalog> {

	private static final long serialVersionUID = 1611541196107L;
	
	@ApiModelProperty(name = "cmid" , value = "")
	private String cmid;
    
	@ApiModelProperty(name = "parentId" , value = "")
	private String parentId;
    
	@ApiModelProperty(name = "name" , value = "")
	private String name;
    
	@ApiModelProperty(name = "state" , value = "")
	private String state;
    
	@ApiModelProperty(name = "createName" , value = "")
	private String createName;
    
	@ApiModelProperty(name = "createId" , value = "")
	private String createId;
    
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
    
	@ApiModelProperty(name = "levels" , value = "")
	private String levels;
    
	@ApiModelProperty(name = "orders" , value = "")
	private String orders;
    
	@ApiModelProperty(name = "icon" , value = "")
	private String icon;
    
	@ApiModelProperty(name = "url" , value = "")
	private String url;
    

}
