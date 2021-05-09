package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import com.jindan.jdy.common.pojo.StarchOrganizationPut;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class StarchMaintenanceAlterationDto extends PageVO   implements Serializable {

    private static final long serialVersionUID = 15982421180L;

    @TableId(value = "wid", type = IdType.UUID)
    @ApiModelProperty(name = "wid" , value = "主键ID")
    private String wid;

    @ApiModelProperty(name = "wstatus" , value = "办理状态")
    private String wstatus;

    @ApiModelProperty(name = "changeNumber" , value = "变更单号")
    private String changeNumber;

    @ApiModelProperty(name = "changePerson" , value = "变更人")
    private String changePerson;

    @ApiModelProperty(name = "suppliers" , value = "供应商")
    private String suppliers;

    @ApiModelProperty(name = "persons" , value = "联系人")
    private String persons;

    @ApiModelProperty(name = "changeDate" , value = "业务日期")
    private String changeDate;

    @ApiModelProperty(name = "phoneType" , value = "联系方式")
    private String phoneType;

    @ApiModelProperty(name = "chargePerson" , value = "负责人")
    private String chargePerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "maintenanceDate" , value = "维保到期日")
    private String maintenanceDate;

    @ApiModelProperty(name = "maintenanceType" , value = "维保周期")
    private String maintenanceType;

    @JsonIgnore
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    @ApiModelProperty(name = "remarks" , value = "维保说明")
    private String remarks;

    @ApiModelProperty(name = "ptypes" , value = "类型 1维保变更，2正常维保信息")
    private String ptypes;

    List<StarchMaintenanceAlteration> starchMaintenanceAlterations;

    List<StarchOrganizationPut> putList;


}
