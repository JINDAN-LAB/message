package com.jindan.jdy.common.dto;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyFlowCatalog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyFlowCatalogDto  implements Serializable {

    private static final long serialVersionUID = 15918610770L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id" , value = "")
    private Integer id;

    @ApiModelProperty(name = "name" , value = "")
    private String name;

    @ApiModelProperty(name = "parentId" , value = "")
    private Integer parentId;

    @ApiModelProperty(name = "fileUrl" , value = "")
    private String fileUrl;

    @ApiModelProperty(name = "persons" , value = "")
    private String persons;

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
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "departments" , value = "")
    private String departments;

    @ApiModelProperty(name = "fileContents" , value = "")
    private String fileContents;

    private List<JdyFlowCatalogDto> lists;


}
