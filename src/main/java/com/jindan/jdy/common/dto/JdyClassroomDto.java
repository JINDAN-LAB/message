package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyClassroom;
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
public class JdyClassroomDto extends PageVO {

    private static final long serialVersionUID = 15879670791L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(name = "id" , value = "")
    private String id;

    @ApiModelProperty(name = "jiaoshi" , value = "职教室")
    private String jiaoshi;

    @ApiModelProperty(name = "contents" , value = "培训内容")
    private String contents;

    @ApiModelProperty(name = "person" , value = "培训人员")
    private String person;

    @ApiModelProperty(name = "bumen" , value = "部门")
    private String bumen;

    @ApiModelProperty(name = "number" , value = "手机号")
    private String number;

    @ApiModelProperty(name = "starttime" , value = "开始时间")
    private String starttime;

    @ApiModelProperty(name = "endtime" , value = "结束时间")
    private String endtime;

    @ApiModelProperty(name = "uptime" , value = "开始上午")
    private String uptime;

    @ApiModelProperty(name = "downtime" , value = "结束下午")
    private String downtime;

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

}

