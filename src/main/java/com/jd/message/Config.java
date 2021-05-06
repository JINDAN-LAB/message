package com.jd.message;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
//@ImportResource({"classpath*:applicationContext-dubbo-producer.xml","core-datasource.xml"
//,"applicationContext-message.xml"})
@ImportResource({"classpath*:core-datasource.xml"})
public class Config {
}
