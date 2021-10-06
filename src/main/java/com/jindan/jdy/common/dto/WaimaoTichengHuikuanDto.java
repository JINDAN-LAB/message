package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 13348 on 2021/8/23.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class WaimaoTichengHuikuanDto  extends PageVO implements Serializable {

    private static final long serialVersionUID = 1592364751490L;

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

    @ApiModelProperty(name = "zhoubie" , value = "")
    private String zhoubie;

    @ApiModelProperty(name = "jine" , value = "")
    private String jine;

    @ApiModelProperty(name = "shijishiyong" , value = "")
    private String shijishiyong;

    @ApiModelProperty(name = "biaoshi" , value = "")
    private String biaoshi;

    @ApiModelProperty(name = "yuliu1" , value = "")
    private String yuliu1;

    @ApiModelProperty(name = "yuliu2" , value = "")
    private String yuliu2;

    @ApiModelProperty(name = "yuliu3" , value = "")
    private String yuliu3;

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
