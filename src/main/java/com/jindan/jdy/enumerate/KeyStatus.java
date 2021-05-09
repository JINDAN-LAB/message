package com.jindan.jdy.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

 @Getter
 public enum KeyStatus {

        WEI(0, "未确认"),
        QUEREN(1, "确认"),
        XULUOSHI(2, "需落实"),
        XUJINDU(3, "需进度"),
        JIESHU(4, "项目结束");

        @JsonCreator
        KeyStatus(Integer status, String value)  {
            this.status = status;
            this.value = value;
        }

        //添加成员变量的原因是，方便够着方法赋值，使用SeasonEunm.SPRING.getName就能获取对应的值
        @EnumValue
        private    Integer status;
        @JsonValue
        private   String value;



    }

