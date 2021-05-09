package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyFileUpload;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JdyUserFileDto extends PageVO {


    private static final long serialVersionUID = 15871802875L;

    @TableId(value = "file_id", type = IdType.UUID)
    @ApiModelProperty(name = "fileId" , value = "")
    private String fileId;

    @ApiModelProperty(name = "userFileName" , value = "")
    private String userFileName;

    @ApiModelProperty(name = "fileName" , value = "")
    private String fileName;

    @ApiModelProperty(name = "fileDescribe" , value = "")
    private String fileDescribe;


    @ApiModelProperty(name = "userId" , value = "")
    private String userId;

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



//        private static final long serialVersionUID = 15868778778L;
//
//    @ApiModelProperty(name = "fileUploadId" , value = "")
//    private String fileUploadId;
//
//    @ApiModelProperty(name = "filename" , value = "文件名")
//    private String filename;
//
//    @ApiModelProperty(name = "fileurl1" , value = "对象存储")
//    private String fileurl1;
//
//    @ApiModelProperty(name = "fileurl2" , value = "")
//    private String fileurl2;
//
//    @ApiModelProperty(name = "fileurl3" , value = "")
//    private String fileurl3;
//
//    @ApiModelProperty(name = "fileurl4" , value = "")
//    private String fileurl4;
//
//    @ApiModelProperty(name = "parentId" , value = "")
//    private String parentId;
//
//    @ApiModelProperty(name = "deleteId" , value = "")
//    private Integer deleteId;
//
//    @ApiModelProperty(name = "userId" , value = "")
//    private String userId;
//
//    @ApiModelProperty(name = "fileDescribe" , value = "文件描述")
//    private String fileDescribe;




}
