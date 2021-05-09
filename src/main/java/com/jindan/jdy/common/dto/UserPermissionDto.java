package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.JdyUser;
import com.jindan.jdy.common.pojo.UserPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.management.relation.Role;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserPermissionDto {

        private static final long serialVersionUID = 15867138953L;

        @TableId(value = "permissionId", type = IdType.UUID)
        @ApiModelProperty(name = "permissionId" , value = "")
        private String permissionId;

        @ApiModelProperty(name = "permission" , value = "")
        private String permission;

        @ApiModelProperty(name = "permissionName" , value = "")
        private String permissionName;

        @ApiModelProperty(value = "插入时间")
        @TableField(fill = FieldFill.INSERT)
        private Date insertTime;

        @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date updateTime;

        @TableLogic
        private Integer deleteId;


        private JdyRole jdyRole;


        private JdyUser jdyUser;


        private UserPermission userPermission;


}
