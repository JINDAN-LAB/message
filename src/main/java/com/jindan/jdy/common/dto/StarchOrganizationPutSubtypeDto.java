package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtypeAsset;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
public class StarchOrganizationPutSubtypeDto  implements Serializable {

    private static final long serialVersionUID = 160175402L;

    @TableId(value = "tid", type = IdType.UUID)
    @ApiModelProperty(name = "tid" , value = "")
    private String tid;

    @ApiModelProperty(name = "names" , value = "")
    private String names;

    @ApiModelProperty(name = "parentId" , value = "")
    private String parentId;

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


    List<StarchOrganizationPutSubtypeAsset> lists;
}
