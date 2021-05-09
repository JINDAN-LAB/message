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
public class StarchOrganizationBorrowReturnDto extends PageVO  implements Serializable {

    private static final long serialVersionUID = 1598231608786L;

    @TableId(value = "brid", type = IdType.UUID)
    @ApiModelProperty(name = "brid" , value = "主键ID")
    private String brid;

    @ApiModelProperty(name = "status" , value = "办理状态")
    private String status;

    @ApiModelProperty(name = "borrowNumber" , value = "借用单号")
    private String borrowNumber;

    @ApiModelProperty(name = "borrowTime" , value = "借用时间")
    private String borrowTime;

    @ApiModelProperty(name = "borrowPerson" , value = "借用人")
    private String borrowPerson;

    @ApiModelProperty(name = "predictReturnTime" , value = "预计归还时间")
    private String predictReturnTime;

    @ApiModelProperty(name = "returnTime" , value = "归还时间")
    private String returnTime;

    @ApiModelProperty(name = "propertyParentId" , value = "资产编号")
    private String propertyParentId;

    @JsonIgnore
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    @ApiModelProperty(name = "departments" , value = "部门")
    private String departments;

    List<StarchOrganizationPut> putList;


}
