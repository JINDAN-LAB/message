package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.mapper.RiskPointContentMapper;
import com.jindan.jdy.mapper.RiskPointContentResultMapper;
import com.jindan.jdy.mapper.RiskPointPersonsMapper;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.service.config.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**   
 * @Description:TODO(风险控制点内容服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointContentServiceImpl  extends ServiceImpl<RiskPointContentMapper,RiskPointContent> implements RiskPointContentService  {

    @Autowired
    RiskPointContentMapper riskPointContentDao;

    @Autowired
    RiskPointPersonsMapper riskPointPersonsDao;

    @Autowired
    RiskPointContentResultMapper riskPointContentResultDao;


    @Override
    public List<RiskPointContent> seleListWapper(RiskPointContent riskPointContent) {

        QueryWrapper<RiskPointContent> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getRiskIds() != null && !riskPointContent.getRiskIds().equals("") ){
            queryWrapper.eq("risk_ids",riskPointContent.getRiskIds());
        }
        if(riskPointContent.getRiskContents() != null && !riskPointContent.getRiskContents().equals("") ){
            queryWrapper.eq("risk_contents",riskPointContent.getRiskContents());
        }
        if(riskPointContent.getAccidetCause() != null && !riskPointContent.getAccidetCause().equals("") ){
            queryWrapper.eq("accidet_cause",riskPointContent.getAccidetCause());
        }
        if(riskPointContent.getMayAccidet() != null && !riskPointContent.getMayAccidet().equals("") ){
            queryWrapper.eq("may_accidet",riskPointContent.getMayAccidet());
        }
        if(riskPointContent.getMeetMeasure() != null && !riskPointContent.getMeetMeasure().equals("") ){
            queryWrapper.eq("meet_measure",riskPointContent.getMeetMeasure());
        }
        if(riskPointContent.getInfluenceSphere() != null && !riskPointContent.getInfluenceSphere().equals("") ){
            queryWrapper.eq("influence_sphere",riskPointContent.getInfluenceSphere());
        }
        if(riskPointContent.getLatentResult() != null && !riskPointContent.getLatentResult().equals("") ){
            queryWrapper.eq("latent_result",riskPointContent.getLatentResult());
        }
        if(riskPointContent.getPazhao() != null && !riskPointContent.getPazhao().equals("") ){
            queryWrapper.eq("pazhao",riskPointContent.getPazhao());
        }
        if(riskPointContent.getShuju() != null && !riskPointContent.getShuju().equals("") ){
            queryWrapper.eq("shuju",riskPointContent.getShuju());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getJibie() != null && !riskPointContent.getJibie().equals("") ){
            queryWrapper.eq("jibie",riskPointContent.getJibie());
        }
        if(riskPointContent.getWeixianJibie() != null && !riskPointContent.getWeixianJibie().equals("") ){
            queryWrapper.eq("weixian_jibie",riskPointContent.getWeixianJibie());
        }
        if(riskPointContent.getRcstatus() != null && !riskPointContent.getRcstatus().equals("") ){
            queryWrapper.eq("rcstatus",riskPointContent.getRcstatus());
        }
        if(riskPointContent.getShebeiId() != null && !riskPointContent.getShebeiId().equals("") ){
            queryWrapper.eq("shebei_id",riskPointContent.getShebeiId());
        }
        if(riskPointContent.getShebeiName() != null && !riskPointContent.getShebeiName().equals("") ){
            queryWrapper.eq("shebei_name",riskPointContent.getShebeiName());
        }
        return riskPointContentDao.selectList(queryWrapper);
    }



    @Override
    public List<RiskPointContent> seletPersonsContent(RiskPointPersons riskPointContent) throws Exception {
        QueryWrapper<RiskPointPersons> queryWrapper = new QueryWrapper();
        queryWrapper.eq("task_person",riskPointContent.getTaskPerson());
        RiskPointPersons riskPointPersons = riskPointPersonsDao.selectOne(queryWrapper);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String daat = CommonUtils.getminus(df.format(new Date()),riskPointPersons.getFrequency());

//      查询培训的内容
        QueryWrapper<RiskPointContent> queryContent = new QueryWrapper();
        queryContent.eq("parent_id",riskPointContent.getParentId());
        queryContent.eq("jibie",riskPointContent.getControlRank());
        List<RiskPointContent> riskPointContents = riskPointContentDao.selectList(queryContent);

//      查询培训的结果
        QueryWrapper<RiskPointContentResult> queryResult = new QueryWrapper();
        queryResult.gt("tijiao_time",daat);
        queryResult.eq("people_name",riskPointContent.getTaskPerson());

        List<RiskPointContentResult> riskPointResult = riskPointContentResultDao.selectList(queryResult);

        for (int i = 0; i <riskPointContents.size() ; i++) {
            for (int j = 0; j < riskPointResult.size(); j++) {
                if(!riskPointContents.get(i).getRiskIds().equals(" ") && riskPointContents.get(i).getRiskIds().equals(riskPointResult.get(j).getParentId())){
                    riskPointContents.get(i).setRcstatus("已巡检");
                }
            }
        }
        return riskPointContents;
    }

    @Override
    public List<RiskPointContentDto> seleListJieGuoWapper(RiskPointContent riskPointContent) {
        return riskPointContentDao.seleListJieGuoWapper(riskPointContent);
    }


    @Override
    public List<RiskPointContentDto> seleteSaoMaBaohan(String riskPointContent,String username) {
        return riskPointContentDao.seleteSaoMaBaohan(riskPointContent,username);
    }


}