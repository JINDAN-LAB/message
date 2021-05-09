package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.DepartmentFacility;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MaintainReportDto  extends PageVO implements Serializable {

    private static final long serialVersionUID = 15906391585L;

    @ApiModelProperty(name = "mpid" , value = "")
    private String mpid;

    @ApiModelProperty(name = "submitContent" , value = "")
    private String submitContent;

    @ApiModelProperty(name = "submitPerson" , value = "")
    private String submitPerson;

    @ApiModelProperty(name = "submitTime" , value = "")
    private String submitTime;

    @ApiModelProperty(name = "parentId" , value = "")
    private String parentId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;


    @ApiModelProperty(name = "facililtyPerson" , value = "")
    private String facililtyPerson;

    DepartmentFacility departmentFacility;


}
