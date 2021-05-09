package com.jindan.jdy.controller.utils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AppPush {

    @Value("${apppush.appId}")
    private static String appId ;
    @Value("${apppush.appKey}")
    private static String appKey ;
    @Value("${apppush.masterSecret}")
    private static String masterSecret ;
    @Value("${apppush.url}")
    private static String url ;

  public static void  main(String[] args) {


  }

    public static void mainAppPush(String title,String content,String imgurl) throws IOException {

        String result= UUID.randomUUID().toString().replace("-", "").toUpperCase();

        System.out.println(result);


        IGtPush push = new IGtPush(url, appKey, masterSecret);

        Style0 style = new Style0();
        style.setTitle(title);
        style.setText(content);
        style.setLogo(imgurl);
        style.setRing(true);  // 设置响铃
        style.setVibrate(true);  // 设置震动

        // STEP4：选择通知模板
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setStyle(style);

        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(template);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒

        // STEP6：执行推送
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());

    }

}
