package com.jindan.jdy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * description:
 * date: 2020/4/6 16:32
 * author: xbdyilin
 */
@Data
@ApiModel(value = "货物单save")
public class StockGoodsVO implements Serializable {

    private static final long serialVersionUID = 1586084791425L;

    @ApiModelProperty(name = "depositoryId" , value = "类别id")
    private Integer depositoryId;

    @ApiModelProperty(name = "depositoryName" , value = "类别名称")
    private String depositoryName;

    @ApiModelProperty(name = "name" , value = "货物名称")
    private String name;

    @ApiModelProperty(name = "remarks" , value = "备注")
    private String remarks;

    @ApiModelProperty(name = "brand" , value = "品牌")
    private String brand;

    @ApiModelProperty(name = "model" , value = "型号")
    private String model;

    @ApiModelProperty(name = "unit" , value = "单位")
    private String unit;

    @ApiModelProperty(name = "price" , value = "价格")
    private String price;

}

