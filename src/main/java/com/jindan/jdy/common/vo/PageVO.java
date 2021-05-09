package com.jindan.jdy.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * description:
 * date: 2020/4/4 20:27
 * author: xbdyilin
 */
@Data
public class PageVO implements Serializable {

    @ApiModelProperty("当前页")
    private int currentPage = 1;

    @ApiModelProperty("每页显示数据")
    private int pageSize = 50;


}

