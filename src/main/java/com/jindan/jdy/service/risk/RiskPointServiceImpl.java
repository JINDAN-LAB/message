package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskPointDto;
import com.jindan.jdy.common.mapper.RiskPointMapper;
import com.jindan.jdy.common.pojo.RiskPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(风险控制服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointServiceImpl  extends ServiceImpl<RiskPointMapper,RiskPoint> implements RiskPointService  {

    @Autowired
    RiskPointMapper riskPointDao;

    @Override
    public List<RiskPoint> seleListWapper(RiskPoint riskPointContent) {
        QueryWrapper<RiskPoint> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getRid() != null && !riskPointContent.getRid().equals("") ){
            queryWrapper.eq("rid",riskPointContent.getRid());
        }
        if(riskPointContent.getRiskType() != null && !riskPointContent.getRiskType().equals("") ){
            queryWrapper.eq("risk_type",riskPointContent.getRiskType());
        }
        if(riskPointContent.getRiskName() != null && !riskPointContent.getRiskName().equals("") ){
            queryWrapper.eq("risk_name",riskPointContent.getRiskName());
        }
        if(riskPointContent.getRiskLocation() != null && !riskPointContent.getRiskLocation().equals("") ){
            queryWrapper.eq("risk_location",riskPointContent.getRiskLocation());
        }
        if(riskPointContent.getRiskPerson() != null && !riskPointContent.getRiskPerson().equals("") ){
            queryWrapper.eq("risk_person",riskPointContent.getRiskPerson());
        }
        if(riskPointContent.getExamineType() != null && !riskPointContent.getExamineType().equals("") ){
            queryWrapper.eq("examine_type",riskPointContent.getExamineType());
        }
        if(riskPointContent.getIsTeshu() != null && !riskPointContent.getIsTeshu().equals("") ){
            queryWrapper.eq("is_teshu",riskPointContent.getIsTeshu());
        }
        if(riskPointContent.getAccidentMay() != null && !riskPointContent.getAccidentMay().equals("") ){
            queryWrapper.eq("accident_may",riskPointContent.getAccidentMay());
        }
        if(riskPointContent.getRiskOften() != null && !riskPointContent.getRiskOften().equals("") ){
            queryWrapper.eq("risk_often",riskPointContent.getRiskOften());
        }
        if(riskPointContent.getAccidentResult() != null && !riskPointContent.getAccidentResult().equals("") ){
            queryWrapper.eq("accident_result",riskPointContent.getAccidentResult());
        }
        if(riskPointContent.getRiskRank() != null && !riskPointContent.getRiskRank().equals("") ){
            queryWrapper.eq("risk_rank",riskPointContent.getRiskRank());
        }
        if(riskPointContent.getRiskExplain() != null && !riskPointContent.getRiskExplain().equals("") ){
            queryWrapper.eq("risk_explain",riskPointContent.getRiskExplain());
        }
        if(riskPointContent.getRiskExplainResult() != null && !riskPointContent.getRiskExplainResult().equals("") ){
            queryWrapper.eq("risk_explain_result",riskPointContent.getRiskExplainResult());
        }
        if(riskPointContent.getRiskDetailLocation() != null && !riskPointContent.getRiskDetailLocation().equals("") ){
            queryWrapper.eq("risk_detail_location",riskPointContent.getRiskDetailLocation());
        }
        if(riskPointContent.getAccidentTypes() != null && !riskPointContent.getAccidentTypes().equals("") ){
            queryWrapper.eq("accident_types",riskPointContent.getAccidentTypes());
        }
        if(riskPointContent.getLossForecast() != null && !riskPointContent.getLossForecast().equals("") ){
            queryWrapper.eq("loss_forecast",riskPointContent.getLossForecast());
        }
        if(riskPointContent.getControlMeasure() != null && !riskPointContent.getControlMeasure().equals("") ){
            queryWrapper.eq("control_measure",riskPointContent.getControlMeasure());
        }
        if(riskPointContent.getPotentialRisk() != null && !riskPointContent.getPotentialRisk().equals("") ){
            queryWrapper.eq("potential_risk",riskPointContent.getPotentialRisk());
        }
        if(riskPointContent.getMeasureMeet() != null && !riskPointContent.getMeasureMeet().equals("") ){
            queryWrapper.eq("measure_meet",riskPointContent.getMeasureMeet());
        }
        if(riskPointContent.getTechnologySafeguard() != null && !riskPointContent.getTechnologySafeguard().equals("") ){
            queryWrapper.eq("technology_safeguard",riskPointContent.getTechnologySafeguard());
        }
        if(riskPointContent.getRemarks() != null && !riskPointContent.getRemarks().equals("") ){
            queryWrapper.eq("remarks",riskPointContent.getRemarks());
        }
        return riskPointDao.selectList(queryWrapper);
    }

    @Override
    public List<RiskPointDto> seleListBaohanWapper(RiskPoint riskPointContent) {
        return riskPointDao.seleListBaohanWapper(riskPointContent);
    }


    @Override
    public RiskPoint insertSave(RiskPoint riskPoint) {
         if(riskPointDao.insert(riskPoint) > 0){
             return riskPoint;
         }
        return riskPoint ;
    }

}