package com.jindan.jdy.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("insertTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("insertTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }



//    @Override
//    public void insertFill(MetaObject metaObject) {
//        Date now = new Date();
//
//        Object crteTime = getFieldValByName("insertTime", metaObject);
//        if (Objects.isNull(crteTime)) {
//            setFieldValByName("insertTime", now, metaObject);
//        }
//
//        Object optTime = getFieldValByName("updateTime", metaObject);
//        if (Objects.isNull(optTime)) {
//            setFieldValByName("updateTime", now, metaObject);
//        }
////        setFieldValByName("updateTime", TpcConstant.INVD, metaObject);
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        Object optTime = getFieldValByName("updateTime", metaObject);
//        if (Objects.isNull(optTime)) {
//            setFieldValByName("updateTime", new Date(), metaObject);
//        }
//    }


}
