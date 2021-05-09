package com.jindan.jdy.controller.fileupload;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.jindan.jdy.common.pojo.JdyFileUpload;
import com.jindan.jdy.service.fileupload.JdyFileUploadService;
import com.jindan.jdy.common.utils.api.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
*
* <p>说明： API应用KEYAPI接口层</P>
* @version: V1.0
* @author: kong
* @time    2019年10月16日
*/
@CrossOrigin(origins = "http://118.24.255.51:20201")
@Api(tags = "文件上传")
@RestController
@RequestMapping("/jdyFileUpload")
public class JdyFileUploadController{

    @ApiOperation(value = "设备维修查询", notes = "参数:角色信息")
    @PostMapping("/upload")
    public  String uploadfile(@ApiParam(value = "医院图片", required = true) MultipartFile file, @RequestParam(value = "content",defaultValue ="file",required = true) String content) throws IOException {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            originalFilename = uuid+originalFilename;
            String date = new DateTime().toString("yyyy/MM/dd");
            originalFilename=content+"/"+date+"/adjunct/"+originalFilename;
            //创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(ConstantPropertiesUtil.BUCKET_NAME, originalFilename, inputStream);

            ossClient.putObject(putObjectRequest);
            ossClient.shutdown();
            String path="https://"+ConstantPropertiesUtil.BUCKET_NAME+"."+ConstantPropertiesUtil.END_POINT+"/"+originalFilename;
            Map<String,String> map =new HashMap<>();
            map.put("data",path);
            map.put("filename",file.getOriginalFilename());
            return path;
        }


    @ApiOperation(value = "删除OSS文件", notes = "参数:删除OSS文件")
    @PostMapping("/deleteUpdate")
    public  String uploadfile( String objectName ) throws IOException {
        if(!objectName.isEmpty()){
            String[] strArr = objectName.split("https://kong15.oss-cn-beijing.aliyuncs.com/");
            System.out.println(strArr.length); //这里输出3
            for (int i = 0; i < strArr.length; ++i){
                System.out.println(strArr[i]);//这里输出a b c
                objectName = strArr[i];
            }
            String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
            OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
            ossClient.deleteObject(bucketName, objectName);
            // 关闭OSSClient。
            ossClient.shutdown();
        }
        return "结束";
    }





}