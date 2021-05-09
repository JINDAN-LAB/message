package com.jindan.jdy.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyDepartments;
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
public class JdyCompanyDto extends PageVO {

    @ApiModelProperty(name = "ids" , value = "")
    private String ids;

    @ApiModelProperty(name = "companyName" , value = "公司名称")
    private String companyName;

    @ApiModelProperty(name = "companyPerson" , value = "公司法人")
    private String companyPerson;

    @ApiModelProperty(name = "yingyeZhizhao" , value = "营业执照")
    private String yingyeZhizhao;

    @ApiModelProperty(name = "personsNumbers" , value = "电话")
    private String personsNumbers;

    @ApiModelProperty(name = "shuihao" , value = "税号")
    private String shuihao;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "")
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "")
    private Date updateTime;

    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    private List<JdyDepartments> departmentsList;

}
