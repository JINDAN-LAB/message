package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.UserRole;
import com.jindan.jdy.common.pojo.UserRolePermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRolePermissionDto  extends Model<com.jindan.jdy.common.pojo.UserRole> {


        private static final long serialVersionUID = 1549588L;

        @ApiModelProperty(name = "userRoleId" , value = "")
        private String userRoleId;

        @ApiModelProperty(name = "userId" , value = "")
        private String userId;

        @ApiModelProperty(name = "roleId" , value = "")
        private String roleId;

        private List<UserPermission> userPermissions;

        private List<UserRolePermission> permissionList;

        private List<JdyRole> jdyRoles;

    }
