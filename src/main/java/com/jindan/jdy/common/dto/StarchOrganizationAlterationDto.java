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
public class StarchOrganizationAlterationDto extends PageVO   implements Serializable {

    private static final long serialVersionUID = 15982327512L;

    @TableId(value = "aid", type = IdType.UUID)
    @ApiModelProperty(name = "aid" , value = "主键ID")
    private String aid;

    @ApiModelProperty(name = "astatus" , value = "办理状态")
    private String astatus;

    @ApiModelProperty(name = "alterationNumber" , value = "变更单号")
    private String alterationNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "alterationDate" , value = "业务日期")
    private String alterationDate;

    @ApiModelProperty(name = "alterationPerson" , value = "处理人")
    private String alterationPerson;

    @ApiModelProperty(name = "alterationType" , value = "资产类别")
    private String alterationType;

    @ApiModelProperty(name = "alterationName" , value = "资产名称")
    private String alterationName;

    @ApiModelProperty(name = "alterationEares" , value = "区域")
    private String alterationEares;

    @ApiModelProperty(name = "depositRegion" , value = "存放地点")
    private String depositRegion;

    @ApiModelProperty(name = "useCompany" , value = "使用公司")
    private String useCompany;

    @ApiModelProperty(name = "useDepartment" , value = "使用部门")
    private String useDepartment;

    @ApiModelProperty(name = "usePerson" , value = "使用人")
    private String usePerson;

    @ApiModelProperty(name = "remarks" , value = "备注")
    private String remarks;

    @JsonIgnore
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    List<StarchOrganizationPut> putList;


}
