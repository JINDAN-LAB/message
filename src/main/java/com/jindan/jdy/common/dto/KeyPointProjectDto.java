package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.KeyPointEvaluate;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.common.pojo.KeyPointSchedule;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class KeyPointProjectDto extends PageVO {

        private static final long serialVersionUID = 1588819386503L;

        @TableId(value = "id", type = IdType.UUID)
        @ApiModelProperty(name = "id" , value = "")
        private String id;

        @ApiModelProperty(name = "titles" , value = "")
        private String titles;

        @ApiModelProperty(name = "contents" , value = "")
        private String contents;

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

        @ApiModelProperty(name = "status" , value = "")
        private String status;

        @ApiModelProperty(name = "parentFile" , value = "文档信息")
        private String parentFile;

        @ApiModelProperty(name = "deparement" , value = "部门信息")
        private String deparement;

        @ApiModelProperty(name = "persons" , value = "提交人")
        private String persons;

        @ApiModelProperty(name = "remartks" , value = "备注信息")
        private String remartks;


        @ApiModelProperty(name = "submitTime" , value = "提交时间")
        private String submitTime;

        @ApiModelProperty(name = "endsubmitTime" , value = "查询结束提交时间")
        private String endsubmitTime;

        private List<KeyPointPracticable> pointPracticables;

        private List<KeyPointSchedule>  keyPointSchedules;

        private List<KeyPointEvaluate>  keyPointEvaluates;

}
