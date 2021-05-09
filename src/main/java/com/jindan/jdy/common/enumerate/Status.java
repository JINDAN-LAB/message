package com.jindan.jdy.common.enumerate;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;


@Getter
public enum  Status {

    STARTSTATIC(0, "未提交"),
    PURCHASE(1, "审批中"),
    PURCHASEING(2, "采购中"),
    INPUTSTATIC(3, "入库"),
    OUTPUTSTATIC(4, "出库"),
    OPERATION(5, "运行"),
    MAINTAIN(6, "维修"),
    SCRAP(7, "报废"),
    ERROR(8, "驳回");

    @JsonCreator
    Status(Integer status, String value)  {
        this.status = status;
        this.value = value;
    }

    //添加成员变量的原因是，方便够着方法赋值，使用SeasonEunm.SPRING.getName就能获取对应的值
    @EnumValue
    private    Integer status;
    @JsonValue
    private   String value;


}
