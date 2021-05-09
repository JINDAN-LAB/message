package com.jindan.jdy.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RankUtils {

    STAFF(1, "员工"),
    MINISTER(2, "部长"),
    MAJOR(3, "总监"),
    MANAGER(4, "总经理"),
    CHAIRMAN(5, "董事长"),
    SHENHE(6, "审核结束");
    @JsonCreator
    RankUtils(Integer status, String value)  {
        this.status = status;
        this.value = value;
    }

    //添加成员变量的原因是，方便够着方法赋值，使用SeasonEunm.SPRING.getName就能获取对应的值
    @EnumValue
    private    Integer status;
    @JsonValue
    private   String value;



}
