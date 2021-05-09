package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class StarchOrganizationProvokeDto extends PageVO implements Serializable {

    private static final long serialVersionUID = 15982179899L;

    @TableId(value = "tid", type = IdType.UUID)
    @ApiModelProperty(name = "tid" , value = "主键ID")
    private String tid;

    @ApiModelProperty(name = "status" , value = "办理状态")
    private String status;

    @ApiModelProperty(name = "allotNumber" , value = "调拨单号")
    private String allotNumber;

    @ApiModelProperty(name = "allotTime" , value = "调拨日期")
    private String allotTime;

    @ApiModelProperty(name = "allotManager" , value = "调出管理员")
    private String allotManager;

    @ApiModelProperty(name = "allotCompany" , value = "调拨公司")
    private String allotCompany;

    @ApiModelProperty(name = "foldDate" , value = "调入日期")
    private String foldDate;

    @ApiModelProperty(name = "foldManagers" , value = "调入管理员")
    private String foldManagers;

    @ApiModelProperty(name = "foldCompany" , value = "调拨公司")
    private String foldCompany;

    @ApiModelProperty(name = "foldExplain" , value = "调拨说明")
    private String foldExplain;

    @ApiModelProperty(name = "foldPropertyId" , value = "相关ID")
    private String foldPropertyId;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    @JsonIgnore
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    List<StarchOrganizationPut> putList;

}
