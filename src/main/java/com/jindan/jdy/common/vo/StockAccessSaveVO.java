package com.jindan.jdy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * description:
 * date: 2020/4/5 19:42
 * author: xbdyilin
 */
@Data
@ApiModel(value = "出入库单")
public class StockAccessSaveVO implements Serializable {

    private static final long serialVersionUID = 1586084791425L;

    @ApiModelProperty(name = "type" , value = "类型：1入库  2出库")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty(name = "specsId" , value = "规格id")
    private String specsId;

    @ApiModelProperty(name = "number" , value = "数量")
    private Integer number;

    @ApiModelProperty(name = "depaId" , value = "部门id")
    private Integer depaId;

    @ApiModelProperty(name = "depaName" , value = "部门name")
    private String depaName;

}

