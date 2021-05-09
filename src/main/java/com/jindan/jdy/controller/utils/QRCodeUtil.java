package com.jindan.jdy.controller.utils;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.internal.OSSUtils;
import com.aliyun.oss.model.PutObjectRequest;
import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.jindan.jdy.common.utils.exception.BusinessException;
import com.jindan.jdy.controller.fileupload.ConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class QRCodeUtil {

    private static final Map<EncodeHintType, ErrorCorrectionLevel> encodeMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
    private static final Map<DecodeHintType, ErrorCorrectionLevel> decodeMap=new HashMap<DecodeHintType, ErrorCorrectionLevel>();
    private static final String charset="UTF-8",format="png";
    private static final int size=150;//生成二维码图片的大小
    private static final int size1=400;//生成二维码图片的大小

    public static String createQRCode3(String content)  throws WriterException, IOException {

        Map hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置容错等级
        hints.put(EncodeHintType.MARGIN, 2);//设置边距默认是5
        // 定义一个输出流，生成二维码保存在输出流里
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(new MultiFormatWriter().encode(
                new String(content.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, size1, size1, hints), format, os);
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //OSS下文件夹名称
       String date = new DateTime().toString("yyyy/MM/dd");
       String bucket = date+"/zxing/"+ UUID.randomUUID()+".png";
       byte[] bytes = os.toByteArray();
//      上传到OSS是上传字节的形式
        InputStream ips = new ByteArrayInputStream(bytes);
//        ossClient.putObject(bucket, fullpath, ips);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ConstantPropertiesUtil.BUCKET_NAME, bucket, ips);
        ossClient.putObject(putObjectRequest);
        try {
            ips.close();
        }catch (IOException e){
//            Log.error("【生成二维码图片上传到OSS】:上传字节流失败",e);//打印日志
            e.printStackTrace();
        }
        ossClient.shutdown();
        String picUrl="https://"+ConstantPropertiesUtil.BUCKET_NAME+"."+ConstantPropertiesUtil.END_POINT+"/"+bucket;
//        Log.info("【下载二维码的地址:】"+picUrl);//打印日志
        return picUrl;
    }

//  设备生成二维码
    public static String createfaclity3(String content)  throws WriterException, IOException {

        Map hints=new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //设置编码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //设置容错等级
        hints.put(EncodeHintType.MARGIN, 2);//设置边距默认是5

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(new MultiFormatWriter().encode(
                new String(content.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, size1, size1, hints), format, os);
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //OSS下文件夹名称
        String date = new DateTime().toString("yyyy/MM/dd");
        String bucket = date+"/zxing/"+ UUID.randomUUID()+".png";
        byte[] bytes = os.toByteArray();
//      上传到OSS是上传字节的形式
        InputStream ips = new ByteArrayInputStream(bytes);
//        ossClient.putObject(bucket, fullpath, ips);
        PutObjectRequest putObjectRequest = new PutObjectRequest(ConstantPropertiesUtil.BUCKET_NAME, bucket, ips);
        ossClient.putObject(putObjectRequest);
        try {
            ips.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        ossClient.shutdown();
        String picUrl="https://"+ConstantPropertiesUtil.BUCKET_NAME+"."+ConstantPropertiesUtil.END_POINT+"/"+bucket;
        return picUrl;
    }


}
