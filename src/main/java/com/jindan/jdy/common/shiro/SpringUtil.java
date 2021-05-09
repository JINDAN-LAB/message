package com.jindan.jdy.common.shiro;

import org.apache.catalina.core.ApplicationContext;

public class SpringUtil {

    private static ApplicationContext applicationContext;



    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringUtil.applicationContext =applicationContext;
    }


}
