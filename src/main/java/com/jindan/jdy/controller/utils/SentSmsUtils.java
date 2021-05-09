package com.jindan.jdy.controller.utils;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;

public class SentSmsUtils {

          public static void SentFasongSms ( String session,String appid,String templateID,String[] phoneNumbers, String[] templateParams ){
              try {

                  Credential cred = new Credential("AKIDoDNIcqW47xXs9EolAL9hX7QcXDsGPV5u", "nTtB7mBKCsAYfxlJZeTs40ipf86nDOIw");
                  // 实例化一个 http 选项，可选，无特殊需求时可以跳过
                  HttpProfile httpProfile = new HttpProfile();

                  ClientProfile clientProfile = new ClientProfile();

                  clientProfile.setSignMethod("HmacSHA256");
                  SmsClient client = new SmsClient(cred, "",clientProfile);
                  SendSmsRequest req = new SendSmsRequest();
                  req.setSmsSdkAppid(appid);
                  String sign = "河南金丹乳酸科技股份公司";
                 req.setSign(sign);
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
