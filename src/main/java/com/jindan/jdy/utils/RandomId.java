package com.jindan.jdy.utils;

import java.util.UUID;

public class RandomId {

    public static String getUUID() {
        String id =null;
        UUID uuid = UUID.randomUUID();
        id = uuid.toString();

        //去掉随机ID的短横线
        id = id.replace("-", "");

        /*//将随机ID换成数字
        int num = id.hashCode();
        //去绝对值
        num = num < 0 ? -num : num;

        id = String.valueOf(num);*/

        return id;
    }

}
