package com.jindan.jdy.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * description:
 * date: 2020/4/6 15:19
 * author: xbdyilin
 */
@Data
@ApiModel(value = "仓库类别列表")
public class StockDepositoryDTO implements Serializable {

    private static final long serialVersionUID = 1586084756075L;

    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty(name = "id" , value = "id")
    private Integer id;

    @ApiModelProperty(name = "parentId" , value = "0为根节点")
    private Integer parentId;

    @ApiModelProperty(name = "name" , value = "parent_id为0时为 仓库,parent_id非0时为 仓库下分类")
    private String name;

    @ApiModelProperty(name = "state" , value = "状态： 0删除  1正常")
    private Integer state;

    @ApiModelProperty(name = "createName" , value = "创建人姓名")
    private String createName;

    @ApiModelProperty(name = "createId" , value = "创建人id")
    private Integer createId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "createTime" , value = "创建时间")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "updateTime" , value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(name = "dname" , value = "仓库name")
    private String dname;


}

