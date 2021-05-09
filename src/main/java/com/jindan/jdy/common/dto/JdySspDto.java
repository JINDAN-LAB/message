package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdySsp;
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
public class JdySspDto extends PageVO {

    private static final long serialVersionUID = 1587638326028L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(name = "id" , value = "")
    private String id;

    @ApiModelProperty(name = "content" , value = "内容")
    private String content;

    @ApiModelProperty(name = "locations" , value = "地址")
    private String locations;

    @ApiModelProperty(name = "imgurl" , value = "图片地址")
    private String imgurl;



    @ApiModelProperty(name = "userId" , value = "用户ID")
    private String userId;

    @ApiModelProperty(name = "status" , value = "状态")
    private String status;

    @ApiModelProperty(name = "chuliTime" , value = "处理时长")
    private String chuliTime;

    @ApiModelProperty(name = "resultPer" , value = "处理人")
    private String resultPer;

    @ApiModelProperty(name = "exceedTime" , value = "是否逾期")
    private String exceedTime;

    @ApiModelProperty(name = "resultUrl" , value = "结束图片")
    private String resultUrl;

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
    @ApiModelProperty(name = "deleteId" , value = "是否删除")
    private Integer deleteId;

    @ApiModelProperty(name = "resultContent" , value = "反馈内容")
    private String resultContent;


}
