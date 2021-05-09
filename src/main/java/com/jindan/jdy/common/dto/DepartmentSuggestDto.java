package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DepartmentSuggestDto   extends PageVO {

        private static final long serialVersionUID = 15877449685L;

        @ApiModelProperty(name = "suggestId" , value = "")
        private String suggestId;

        @ApiModelProperty(name = "suggestName" , value = "")
        private String suggestName;

        @ApiModelProperty(name = "suggestContent" , value = "")
        private String suggestContent;

        @ApiModelProperty(name = "deaprtment" , value = "")
        private String deaprtment;

        @TableLogic
        @ApiModelProperty(name = "deleteId" , value = "")
        private Integer deleteId;

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

        @ApiModelProperty(name = "status" , value = "")
        private Integer status;

        @ApiModelProperty(name = "resultContent" , value = "")
        private String resultContent;

        @ApiModelProperty(name = "userId" , value = "")
        private String userId;

        @ApiModelProperty(name = "resultPre" , value = "")
        private String resultPre;

        @ApiModelProperty(name = "resultTime" , value = "")
        private String resultTime;





}
