package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyFlow;
import com.jindan.jdy.common.pojo.JdyUser;
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
 public class JdyFlowDto extends PageVO {

        private static final long serialVersionUID = 1589580585086L;

        @TableId(value = "flow_id", type = IdType.UUID)
        @ApiModelProperty(name = "flowId" , value = "主键ID")
        private String flowId;

        @ApiModelProperty(name = "flowPersom" , value = "审批人")
        private String flowPersom;

        @ApiModelProperty(name = "flowRemarks" , value = "备注")
        private String flowRemarks;

        @ApiModelProperty(name = "flowResult" , value = "返回结果，同意或者拒绝")
        private String flowResult;

        @ApiModelProperty(name = "flowTime" , value = "操作时间")
        private String flowTime;

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

        @ApiModelProperty(name = "parentId" , value = "父类ID")
        private String parentId;


        @ApiModelProperty(name = "flowType" , value = "父类ID")
        private String flowType;

        JdyUser jdyUser;


    }

