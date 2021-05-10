package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskPointJobSlipDetailsDto;
import com.jindan.jdy.common.dto.RiskPointJobSlipDto;
import com.jindan.jdy.mapper.RiskPointJobSlipApplyMapper;
import com.jindan.jdy.common.pojo.RiskPointJobSlipApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(作业票批准服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointJobSlipApplyServiceImpl  extends ServiceImpl<RiskPointJobSlipApplyMapper,RiskPointJobSlipApply> implements RiskPointJobSlipApplyService  {

    @Autowired
    RiskPointJobSlipApplyMapper riskPointJobSlipApplyDao;

    @Override
    public List<RiskPointJobSlipApply> seleList(RiskPointJobSlipApply riskPointContent) {

        QueryWrapper<RiskPointJobSlipApply> queryWrapper = new QueryWrapper<>();
        if(riskPointContent.getZdid() != null && !riskPointContent.getZdid().equals("") ){
            queryWrapper.eq("zdid",riskPointContent.getZdid());
        }
        if(riskPointContent.getZnumber() != null && !riskPointContent.getZnumber().equals("") ){
            queryWrapper.eq("znumber",riskPointContent.getZnumber());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getZstartTime() != null && !riskPointContent.getZstartTime().equals("") ){
            queryWrapper.eq("zstart_time",riskPointContent.getZstartTime());
        }
        if(riskPointContent.getZendTime() != null && !riskPointContent.getZendTime().equals("") ){
            queryWrapper.eq("zend_time",riskPointContent.getZendTime());
        }
        if(riskPointContent.getScenePersons() != null && !riskPointContent.getScenePersons().equals("") ){
            queryWrapper.eq("scene_persons",riskPointContent.getScenePersons());
        }
        if(riskPointContent.getSafetyPersons() != null && !riskPointContent.getSafetyPersons().equals("") ){
            queryWrapper.eq("safety_persons",riskPointContent.getSafetyPersons());
        }
        if(riskPointContent.getRiskAssess() != null && !riskPointContent.getRiskAssess().equals("") ){
            queryWrapper.eq("risk_assess",riskPointContent.getRiskAssess());
        }
        if(riskPointContent.getZuoyeLocation() != null && !riskPointContent.getZuoyeLocation().equals("") ){
            queryWrapper.eq("zuoye_location",riskPointContent.getZuoyeLocation());
        }
        if(riskPointContent.getZstatus() != null && !riskPointContent.getZstatus().equals("") ){
            queryWrapper.eq("zstatus",riskPointContent.getZstatus());
        }
        if(riskPointContent.getApplyDepartment() != null && !riskPointContent.getApplyDepartment().equals("") ){
            queryWrapper.eq("apply_department",riskPointContent.getApplyDepartment());
        }
        if(riskPointContent.getApplyPersons() != null && !riskPointContent.getApplyPersons().equals("") ){
            queryWrapper.eq("apply_persons",riskPointContent.getApplyPersons());
        }
        if(riskPointContent.getPtypes() != null && !riskPointContent.getPtypes().equals("") ){
            queryWrapper.eq("ptypes",riskPointContent.getPtypes());
        }
        if(riskPointContent.getScenePersonsSignature() != null && !riskPointContent.getScenePersonsSignature().equals("") ){
            queryWrapper.eq("scene_persons_signature",riskPointContent.getScenePersonsSignature());
        }
        if(riskPointContent.getTheirPersonsSignature() != null && !riskPointContent.getTheirPersonsSignature().equals("") ){
            queryWrapper.eq("their_persons_signature",riskPointContent.getTheirPersonsSignature());
        }
        if(riskPointContent.getSafetyPersonSingnature() != null && !riskPointContent.getSafetyPersonSingnature().equals("") ){
            queryWrapper.eq("safety_person_singnature",riskPointContent.getSafetyPersonSingnature());
        }
        if(riskPointContent.getWorkshopPersons() != null && !riskPointContent.getWorkshopPersons().equals("") ){
            queryWrapper.eq("workshop_persons",riskPointContent.getWorkshopPersons());
        }
        if(riskPointContent.getTheirPersons() != null && !riskPointContent.getTheirPersons().equals("") ){
            queryWrapper.eq("their_persons",riskPointContent.getTheirPersons());
        }
        if(riskPointContent.getRemarks1() != null && !riskPointContent.getRemarks1().equals("") ){
            queryWrapper.eq("remarks1",riskPointContent.getRemarks1());
        }
        if(riskPointContent.getRemarks2() != null && !riskPointContent.getRemarks2().equals("") ){
            queryWrapper.eq("remarks2",riskPointContent.getRemarks2());
        }
        if(riskPointContent.getRemarks3() != null && !riskPointContent.getRemarks3().equals("") ){
            queryWrapper.eq("remarks3",riskPointContent.getRemarks3());
        }
        if(riskPointContent.getNotifyPersons() != null && !riskPointContent.getNotifyPersons().equals("") ){
            queryWrapper.eq("notify_persons",riskPointContent.getNotifyPersons());
        }
        if(riskPointContent.getNotifyPersons2() != null && !riskPointContent.getNotifyPersons2().equals("") ){
            queryWrapper.eq("notify_persons2",riskPointContent.getNotifyPersons2());
        }
        return riskPointJobSlipApplyDao.selectList(queryWrapper);
    }


    @Override
    public boolean seleDetails(RiskPointJobSlipApply userPermission) {
     List<RiskPointJobSlipDto>  riskPointJobSlips =  riskPointJobSlipApplyDao.seleDetails(userPermission);
        for (int i = 0; i < riskPointJobSlips.size(); i++) {
            for (int j = 0; j <riskPointJobSlips.get(i).getDetailsList().size() ; j++) {
                for (int k = 0; k < riskPointJobSlips.get(i).getDetailsList().get(j).getResultList().size(); k++) {
                    if((riskPointJobSlips.get(i).getDetailsList().get(j).getXtitle()
                     .equals(userPermission.getZstatus()) && riskPointJobSlips.get(i).getDetailsList().get(j).getResultList().get(k).getPatrolResult().equals("已检查无隐患")
                    )||
                     riskPointJobSlips.get(i).getDetailsList().get(j).getXtitle()
                            .equals(userPermission.getZstatus()) && !riskPointJobSlips.get(i).getDetailsList().get(j).getResultList().get(k).getPatrolResult().equals("未整改")
                    ){
                       break;
                    }else{
                      if(k == riskPointJobSlips.get(i).getDetailsList().get(j).getResultList().size() ){
                          return false;
                      }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<RiskPointJobSlipDto> seleteDetailsContent(RiskPointJobSlipApply userPermission) {
       return riskPointJobSlipApplyDao.seleDetails(userPermission);
    }

    @Override
    public List<RiskPointJobSlipDetailsDto> seleteZhenggaiRiskPoint(RiskPointJobSlipApply riskPointContent) {
        return riskPointJobSlipApplyDao.seleteZhenggaiRiskPoint(riskPointContent);
    }

}