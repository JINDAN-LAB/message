package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyAppletFoodSafety;
import com.jindan.jdy.common.pojo.JdyAppletFootSafetyPerson;
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
public class JdyAppletFoodSafetyProblemsReultDto implements Serializable {

    private static final long serialVersionUID = 1595918691223L;

    @TableId(value = "rid", type = IdType.UUID)
    @ApiModelProperty(name = "rid" , value = "")
    private String rid;

    @ApiModelProperty(name = "imgurls" , value = "")
    private String imgurls;

    @ApiModelProperty(name = "normalTime" , value = "")
    private String normalTime;

    @ApiModelProperty(name = "contents" , value = "")
    private String contents;

    @ApiModelProperty(name = "rpersons" , value = "")
    private String rpersons;

    @ApiModelProperty(name = "status" , value = "0代表正常履职，3总监提交问题，4总监提交问题解决，1车间提交问题，2车间提交问题解决")
    private String status;

    @ApiModelProperty(name = "resultTimes" , value = "")
    private String resultTimes;

    @ApiModelProperty(name = "resultUrl" , value = "")
    private String resultUrl;

    @ApiModelProperty(name = "tijiaoMulv" , value = "提交人对应的目录")
    private String tijiaoMulv;

    @ApiModelProperty(name = "resultPerson" , value = "")
    private String resultPerson;

    @ApiModelProperty(name = "resultContent" , value = "")
    private String resultContent;

    @ApiModelProperty(name = "leibie" , value = "")
    private String leibie;

    @ApiModelProperty(name = "chejianName" , value = "")
    private String chejianName;

    @ApiModelProperty(name = "tijiaoTime" , value = "")
    private String tijiaoTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "insertTime" , value = "插入时间 不需要填入")
    @TableField(fill = FieldFill.INSERT)
    private Date insertTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "修改日期 不需要填入")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic
    @ApiModelProperty(name = "deleteId" , value = "")
    private Integer deleteId;

    @ApiModelProperty(name = "parentId" , value = "")
    private String parentId;

    @ApiModelProperty(name = "imgurl1" , value = "")
    private String imgurl1;

    @ApiModelProperty(name = "imgurl2" , value = "")
    private String imgurl2;

    @ApiModelProperty(name = "imgurl3" , value = "")
    private String imgurl3;

    @ApiModelProperty(name = "imgurl4" , value = "")
    private String imgurl4;

    @ApiModelProperty(name = "imgurlresult1" , value = "")
    private String imgurlresult1;

    @ApiModelProperty(name = "imgurlresult2" , value = "")
    private String imgurlresult2;

    @ApiModelProperty(name = "imgurlresult3" , value = "")
    private String imgurlresult3;

    @ApiModelProperty(name = "imgurlresult4" , value = "")
    private String imgurlresult4;


    JdyAppletFootSafetyPerson jdyAppletFootSafetyPerson;

    JdyAppletFoodSafety jdyAppletFoodSafety;



}
