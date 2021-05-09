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
public class KeyPointPracticableDto extends PageVO {

    private static final long serialVersionUID = 15888193513L;

    @TableId(value = "pid", type = IdType.UUID)
    @ApiModelProperty(name = "pid" , value = "")
    private String pid;

    @ApiModelProperty(name = "ptitles" , value = "标题")
    private String ptitles;

    @ApiModelProperty(name = "pcontents" , value = "内容")
    private String pcontents;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "premarks" , value = "备注信息")
    private String premarks;

    @ApiModelProperty(name = "pstatus" , value = "状态信息")
    private String pstatus;

    @ApiModelProperty(name = "parentId" , value = "信息")
    private String parentId;


    @ApiModelProperty(name = "pfileId" , value = "状态信息")
    private String pfileId;

    @ApiModelProperty(name = "ppersons" , value = "人员信息")
    private String ppersons;




}
