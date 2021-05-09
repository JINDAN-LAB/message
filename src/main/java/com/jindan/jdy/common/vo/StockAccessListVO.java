package com.jindan.jdy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 * date: 2020/4/5 19:23
 * author: xbdyilin
 */
@Data
@ApiModel(value = "出入库列表")
public class StockAccessListVO extends PageVO{

    private static final long serialVersionUID = 1586084791425L;

    @ApiModelProperty(name = "type" , value = "类型：1入库  2出库")
    @NotNull(message = "类型不能为空！")
    private Integer type;

    @ApiModelProperty(name = "name" , value = "类别名称")
    private String name;

}

