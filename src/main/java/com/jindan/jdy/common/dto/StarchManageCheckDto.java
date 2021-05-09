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
public class StarchManageCheckDto extends PageVO   implements Serializable {

    private static final long serialVersionUID = 15982395686L;


    @TableId(value = "cid", type = IdType.UUID)
    @ApiModelProperty(name = "cid" , value = "主键ID")
    private String cid;

    @ApiModelProperty(name = "checkName" , value = "盘点名称")
    private String checkName;

    @ApiModelProperty(name = "checkTakeStock" , value = "手工盘点")
    private String checkTakeStock;

    @ApiModelProperty(name = "creckPerson" , value = "创建人")
    private String creckPerson;

    @ApiModelProperty(name = "creationDate" , value = "创建日期")
    private String creationDate;

    @ApiModelProperty(name = "creationStatus" , value = "盘点状态")
    private String creationStatus;

    @JsonIgnore
    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "checkCompany" , value = "检查公司")
    private String checkCompany;

    @ApiModelProperty(name = "belongCompany" , value = "属于公司")
    private String belongCompany;

    @ApiModelProperty(name = "zichanType" , value = "资产类别")
    private String zichanType;

    @ApiModelProperty(name = "quyu" , value = "区域")
    private String quyu;

    @ApiModelProperty(name = "managers" , value = "管理人")
    private String managers;

    List<StarchOrganizationPut> putList;


}
