package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jindan.jdy.common.pojo.JdyRole;
import com.jindan.jdy.common.pojo.UserPermission;
import com.jindan.jdy.common.pojo.UserRolePermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyRoleDto extends Model<com.jindan.jdy.common.pojo.JdyRole> implements Serializable {

        private static final long serialVersionUID = 158621846L;

        @TableId(value = "role_id", type = IdType.UUID)
        @ApiModelProperty(name = "roleId" , value = "")
        private String roleId;

        @ApiModelProperty(name = "roleName" , value = "")
        private String roleName;

        @ApiModelProperty(name = "roleDescribe" , value = "")
        private String roleDescribe;

        private List<UserPermission> userPermissions;


}
