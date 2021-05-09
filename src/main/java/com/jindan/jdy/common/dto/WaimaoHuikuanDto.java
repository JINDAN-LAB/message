package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class WaimaoHuikuanDto extends PageVO {

    private static final long serialVersionUID = 1589518541688L;
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id" , value = "")
    private Integer id;

    @ApiModelProperty(name = "fapiaohao" , value = "")
    private String fapiaohao;

    @ApiModelProperty(name = "huikuanriqi" , value = "")
    private String huikuanriqi;

    @ApiModelProperty(name = "huikuanjine" , value = "")
    private String huikuanjine;

    @ApiModelProperty(name = "jiehuiyinhang" , value = "")
    private String jiehuiyinhang;


    @ApiModelProperty(name = "bizhong" , value = "")
    private String bizhong;

    @ApiModelProperty(name = "zhoubie" , value = "")
    private String zhoubie;

    @ApiModelProperty(name = "jine" , value = "")
    private String jine;

    @ApiModelProperty(name = "xiaoshouren" , value = "")
    private String xiaoshouren;

    @ApiModelProperty(name = "shouhuokehu" , value = "")
    private String shouhuokehu;

    @ApiModelProperty(name = "shijishiyong" , value = "")
    private String shijishiyong;

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

    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;



}

