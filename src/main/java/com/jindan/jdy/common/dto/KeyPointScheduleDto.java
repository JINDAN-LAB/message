package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class  KeyPointScheduleDto extends PageVO {

    private static final long serialVersionUID = 1588397218L;

    @ApiModelProperty(name = "sid" , value = "")
    private String sid;

    @ApiModelProperty(name = "stitles" , value = "标题")
    private String stitles;

    @ApiModelProperty(name = "scontents" , value = "内容")
    private String scontents;

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

    @ApiModelProperty(name = "spersons" , value = "提交人")
    private String spersons;

    @ApiModelProperty(name = "sstatus" , value = "装态信息")
    private String sstatus;

    @ApiModelProperty(name = "sremarks" , value = "备注")
    private String sremarks;

    @ApiModelProperty(name = "parentId" , value = "信息")
    private String parentId;

    @ApiModelProperty(name = "sfileId" , value = "状态信息")
    private String sfileId;


}
