package com.jindan.jdy.service.risk;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskRulesRegulationsDto;
import com.jindan.jdy.common.pojo.RiskRulesRegulations;
import com.jindan.jdy.controller.fileupload.ConstantPropertiesUtil;
import com.jindan.jdy.controller.utils.FileToByteArray;
import com.jindan.jdy.controller.utils.MultipartFileToFile;
import com.jindan.jdy.mapper.RiskRulesRegulationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-31
 */
@Service
public class RiskRulesRegulationsServiceImpl extends ServiceImpl<RiskRulesRegulationsMapper, RiskRulesRegulations> implements RiskRulesRegulationsService {

    @Autowired
    private RiskRulesRegulationsMapper riskRulesRegulationsMapper;

    @Override
    public String updateFileUrl(MultipartFile file) throws Exception {

        //将MultipartFile文件转为File文件
        File file1 = MultipartFileToFile.multipartFileToFile(file);

        FileInputStream fis = new FileInputStream(file1);

        MultipartFileToFile.delteTempFile(file1);

        //将文件转为字节流
        byte[] bytes = FileToByteArray.toByteArray(fis);

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(ConstantPropertiesUtil.END_POINT, ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        //OSS下文件夹名称
        String date = new DateTime().toString("yyyy/MM/dd");
        String bucket = date+"/rulesRegulations/"+ UUID.randomUUID()+".png";

        //上传到OSS是上传字节的形式
        InputStream ips = new ByteArrayInputStream(bytes);
        //ossClient.putObject(bucket, fullpath, ips);
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

    @Override
    public Page<RiskRulesRegulations> selectRiskRulesByPage(RiskRulesRegulationsDto riskRulesRegulationsDto) {

        if (riskRulesRegulationsDto.getCurrentPage() <= 0){
            riskRulesRegulationsDto.setCurrentPage(1);
        }
        Page<RiskRulesRegulations> page = new Page<>(riskRulesRegulationsDto.getCurrentPage(),riskRulesRegulationsDto.getPageSize());

        String startTime = riskRulesRegulationsDto.getStartTime()+" 00:00:00";
        String endTime = riskRulesRegulationsDto.getEndTime()+" 23:59:59";

        QueryWrapper<RiskRulesRegulations> queryWrapper = new QueryWrapper<>();
        if (riskRulesRegulationsDto.getSystemName() != null && !riskRulesRegulationsDto.getSystemName().equals("")){
            queryWrapper.eq("systemName",riskRulesRegulationsDto.getSystemName());
        }
        if (riskRulesRegulationsDto.getState() != null && !riskRulesRegulationsDto.getState().equals("")){
            queryWrapper.eq("state",riskRulesRegulationsDto.getState());
        }
        if (riskRulesRegulationsDto.getStartTime() != null && riskRulesRegulationsDto.getEndTime() != null){
            queryWrapper.between("developmentDate",startTime,endTime);
        }else if (riskRulesRegulationsDto.getStartTime() != null && riskRulesRegulationsDto.getEndTime() == null){
            queryWrapper.ge("developmentDate",startTime);
        }else if (riskRulesRegulationsDto.getStartTime() == null && riskRulesRegulationsDto.getEndTime() != null){
            queryWrapper.le("developmentDate",endTime);
        }
        return (Page<RiskRulesRegulations>) riskRulesRegulationsMapper.selectPage(page,queryWrapper);
    }

    @Override
    public RiskRulesRegulations selectRiskRules(RiskRulesRegulationsDto riskRulesRegulationsDto) {
        QueryWrapper<RiskRulesRegulations> queryWrapper = new QueryWrapper<>();
        if (riskRulesRegulationsDto.getRulesId() != null && !riskRulesRegulationsDto.getRulesId().equals("")){
            queryWrapper.eq("rsca_id",riskRulesRegulationsDto.getRulesId());
        }
        return riskRulesRegulationsMapper.selectOne(queryWrapper);
    }
}
