package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.*;
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
public class JdyUserDto extends PageVO {

 private static final long serialVersionUID = 1586846725836L;

        @TableId(value = "user_id", type = IdType.UUID)
        @ApiModelProperty(name = "userId" , value = "")
        private String userId;

        @ApiModelProperty(name = "username" , value = "")
        private String username;

        @ApiModelProperty(name = "password" , value = "")
        private String password;

        @ApiModelProperty(name = "wecharId" , value = "")
        private String wecharId;

        @ApiModelProperty(name = "roleId" , value = "")
        private String roleId;

        @ApiModelProperty(name = "phone" , value = "")
        private String phone;

        @ApiModelProperty(name = "email" , value = "")
        private String email;

        @ApiModelProperty(name = "power" , value = "")
        private String power;

        @ApiModelProperty(name = "status" , value = "")
        private String status;

        @ApiModelProperty(name = "icon" , value = "")
        private String icon;

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

       @ApiModelProperty(name = "sex" , value = "性别")
       private String sex;

       private List<JdyRoleDto> roleList;

       private String departments;

        @ApiModelProperty(name = "gongsi" , value = "公司")
        private String gongsi;

       private List<JdyRoleDto> userJdyRoleDto;

        private List<JdyUserFile> jdyUserFileList;


 }

