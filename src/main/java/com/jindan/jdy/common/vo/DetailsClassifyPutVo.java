package com.jindan.jdy.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DetailsClassifyPutVo implements Serializable {

    @ApiModelProperty("name")
    private String name;

    @ApiModelProperty("sums")
    private String sums;

    @ApiModelProperty("jines")
    private String jines;



}
