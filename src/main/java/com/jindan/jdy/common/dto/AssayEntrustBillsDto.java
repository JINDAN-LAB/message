package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AssayEntrustBillsDto extends PageVO {

    private static final long serialVersionUID = 15980110287L;
    @TableId(value = "eid", type = IdType.UUID)
    @ApiModelProperty(name = "eid" , value = "")
    private String eid;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

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

    @ApiModelProperty(name = "ename" , value = "产品名称")
    private String ename;

    @ApiModelProperty(name = "epihao" , value = "批号")
    private String epihao;

    @ApiModelProperty(name = "eguige" , value = "产品规格")
    private String eguige;

    @ApiModelProperty(name = "enums" , value = "产品数量")
    private String enums;

    @ApiModelProperty(name = "eunit" , value = "委托单位")
    private String eunit;

    @ApiModelProperty(name = "eperson" , value = "委托人")
    private String eperson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "dateTime" , value = "委托日期")
    private Date dateTime;

    @ApiModelProperty(name = "remarks" , value = "备注")
    private String remarks;


}
