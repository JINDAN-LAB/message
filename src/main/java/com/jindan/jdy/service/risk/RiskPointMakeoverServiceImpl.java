package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.RiskPointMakeoverMapper;
import com.jindan.jdy.common.pojo.RiskPointMakeover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(风险控制转让服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointMakeoverServiceImpl  extends ServiceImpl<RiskPointMakeoverMapper,RiskPointMakeover> implements RiskPointMakeoverService  {

    @Autowired
    RiskPointMakeoverMapper riskPointMakeoverDao;

    @Override
    public List<RiskPointMakeover> seleListWapper(RiskPointMakeover riskPointContent) {

        QueryWrapper<RiskPointMakeover> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getZhuanId() != null && !riskPointContent.getZhuanId().equals("") ){
            queryWrapper.eq("zhuan_id",riskPointContent.getZhuanId());
        }
        if(riskPointContent.getRiskPoint() != null && !riskPointContent.getRiskPoint().equals("") ){
            queryWrapper.eq("risk_point",riskPointContent.getRiskPoint());
        }
        if(riskPointContent.getStartTime() != null && !riskPointContent.getStartTime().equals("") ){
            queryWrapper.eq("start_time",riskPointContent.getStartTime());
        }
        if(riskPointContent.getEndTime() != null && !riskPointContent.getEndTime().equals("") ){
            queryWrapper.eq("end_time",riskPointContent.getEndTime());
        }
        if(riskPointContent.getTransformPerson() != null && !riskPointContent.getTransformPerson().equals("") ){
            queryWrapper.eq("transform_person",riskPointContent.getTransformPerson());
        }
        if(riskPointContent.getReceptionPerson() != null && !riskPointContent.getReceptionPerson().equals("") ){
            queryWrapper.eq("reception_person",riskPointContent.getReceptionPerson());
        }
        if(riskPointContent.getZstatus() != null && !riskPointContent.getZstatus().equals("") ){
            queryWrapper.eq("zstatus",riskPointContent.getZstatus());
        }
        return riskPointMakeoverDao.selectList(queryWrapper);
    }


}