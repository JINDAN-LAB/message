package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jindan.jdy.common.pojo.JdyPut;
import com.jindan.jdy.common.vo.PageVO;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value="类型",description="类型")
public class JdyPutTypeDetails  extends PageVO {

    private static final long serialVersionUID = 18931725L;

    @TableId(value = "putTypeId", type = IdType.UUID)
    @ApiModelProperty(name = "putTypeId" , value = "主键ID")
    private String putTypeId;

    @ApiModelProperty(name = "putTypeName" , value = "名称")
    private String putTypeName;

    @ApiModelProperty(name = "putTypeType" , value = "出库  、入库")
    private String putTypeType;

    @ApiModelProperty(name = "parentId" , value = "父类ID")
    private String parentId;

    @ApiModelProperty(name = "orders" , value = "排序")
    private String orders;

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

    private List<JdyPut> putList;


}
