package com.jindan.jdy.service.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

import java.io.UnsupportedEncodingException;

public class SentSmsUtils {

          public static void SentFasongSms ( String session,String appid,String templateID,String[] phoneNumbers, String[] templateParams ){
              try {
                  Credential cred = new Credential("AKIDoDNIcqW47xXs9EolAL9hX7QcXDsGPV5u", "nTtB7mBKCsAYfxlJZeTs40ipf86nDOIw");

                  ClientProfile clientProfile = new ClientProfile();
                  clientProfile.setSignMethod("HmacSHA256");
                  SmsClient client = new SmsClient(cred, "",clientProfile);
                  SendSmsRequest req = new SendSmsRequest();
                  req.setSmsSdkAppid(appid);

                  try {
                    String gbkStr = "河南金丹乳酸科技股份公司"; //源码文件是GBK格式，或者这个字符串是从GBK文件中读取出来的, 转换为string 变成unicode格式
                   byte[] utf8Bytes = gbkStr.getBytes("UTF-8");
                    req.setSign(new String(utf8Bytes, "UTF-8"));
                  } catch (UnsupportedEncodingException e) {
                      e.printStackTrace();
                  }
                  req.setSessionContext(session);
                  req.setTemplateID(templateID);
                  req.setPhoneNumberSet(phoneNumbers);
                  req.setTemplateParamSet(templateParams);

                  SendSmsResponse res = client.SendSms(req);

              } catch (TencentCloudSDKException e) {
                  e.printStackTrace();
              }

          }

}
