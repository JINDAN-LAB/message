package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyPut;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="类型",description="类型")
public class JdyPutDto   extends PageVO {
    
    private static final long serialVersionUID = 15871916006L;

    @TableId(value = "putId", type = IdType.UUID)
    @ApiModelProperty(name = "putId" , value = "主键iD")
    private String putId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "出库时间")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @ApiModelProperty(name = "putNumber" , value = "出库数量")
    private Integer putNumber;

    @ApiModelProperty(name = "putType" , value = "出库类型")
    private String putType;

    @ApiModelProperty(name = "department" , value = "出库部门")
    private String department;

    @ApiModelProperty(name = "remarks" , value = "备注")
    private String remarks;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(name = "updateTime" , value = "修改日期")
    private String updateTime;



}
