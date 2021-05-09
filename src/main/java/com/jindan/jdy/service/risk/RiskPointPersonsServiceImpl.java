package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskPointDtoDetails;
import com.jindan.jdy.common.mapper.RiskPointPersonsMapper;
import com.jindan.jdy.common.pojo.RiskPointPersons;
import com.jindan.jdy.service.config.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**   
 * @Description:TODO(风险控制人服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointPersonsServiceImpl  extends ServiceImpl<RiskPointPersonsMapper,RiskPointPersons> implements RiskPointPersonsService  {

    @Autowired
    RiskPointPersonsMapper riskPointPersonsDao;

    @Override
    public List<RiskPointPersons> seleListWapper(RiskPointPersons riskPointContent) {

        QueryWrapper<RiskPointPersons> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getRisId() != null && !riskPointContent.getRisId().equals("") ){
            queryWrapper.eq("ris_id",riskPointContent.getRisId());
        }
        if(riskPointContent.getRiskTaskType() != null && !riskPointContent.getRiskTaskType().equals("") ){
            queryWrapper.eq("risk_task_type",riskPointContent.getRiskTaskType());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getTaskPerson() != null && !riskPointContent.getTaskPerson().equals("") ){
            queryWrapper.eq("task_person",riskPointContent.getTaskPerson());
        }
        if(riskPointContent.getControlRank() != null && !riskPointContent.getControlRank().equals("") ){
            queryWrapper.eq("control_rank",riskPointContent.getControlRank());
        }
        if(riskPointContent.getFrequency() != null && !riskPointContent.getFrequency().equals("") ){
            queryWrapper.eq("frequency",riskPointContent.getFrequency());
        }
        if(riskPointContent.getLastTimes() != null && !riskPointContent.getLastTimes().equals("") ){
            queryWrapper.eq("last_times",riskPointContent.getLastTimes());
        }
        return riskPointPersonsDao.selectList(queryWrapper);
    }

    @Override
    public List<RiskPointDtoDetails> seleListBaohanrenwuWapper(RiskPointPersons riskPointContent) throws Exception {

        List<RiskPointDtoDetails> detailsList  = riskPointPersonsDao.seleListBaohanrenwuWapper(riskPointContent);
        for (int i = 0; i < detailsList.size(); i++) {
           if(detailsList.get(i).getList().size() > 0){
               for (int j = 0; j < detailsList.get(i).getList().size(); j++) {  // 查询出来的结果
                    if(detailsList.get(i).getList().get(j).getResultList().size() > 0){
                      if(Integer.valueOf((int) CommonUtils.getDateStringDistanceDays(CommonUtils.getPresenttime(),detailsList.get(i).getList().get(j).getResultList().get(0).getInsertTime())) < Integer.valueOf(detailsList.get(i).getPersonsList().get(0).getFrequency())){
                          detailsList.get(i).getList().get(j).setRcstatus("已巡检");
                      }
                   }
               }
           }
        }
        return detailsList;
    }

    @Override
    public List<RiskPointDtoDetails> seleteChaoqiPersons(RiskPointPersons riskPointContent) throws Exception {

        List<RiskPointDtoDetails> detailsList  = riskPointPersonsDao.seleteChaoqiPersons(riskPointContent);
        for (int i = 0; i < detailsList.size(); i++) {
            if(detailsList.get(i).getList().size() > 0){
                for (int j = 0; j < detailsList.get(i).getList().size(); j++) {  // 查询出来的结果
                    if(detailsList.get(i).getList().get(j).getResultList().size() > 0){
                        if(Integer.valueOf((int) CommonUtils.getDateStringDistanceDays(CommonUtils.getPresenttime(),detailsList.get(i).getList().get(j).getResultList().get(0).getInsertTime()))  <=  Integer.valueOf(detailsList.get(i).getPersonsList().get(0).getFrequency())){
                            detailsList.get(i).getList().get(j).setRcstatus("已巡检");
                        }
                    }
                }
            }
        }
        return detailsList;
    }

    @Override
    public List<RiskPointDtoDetails> seleListchaoqiBaohanrenwuWapper(RiskPointPersons riskPointContent) throws Exception {
        List<RiskPointDtoDetails> detailsLists= new ArrayList<>();
        List<RiskPointDtoDetails> detailsList  = riskPointPersonsDao.seleteChaoqiPersons(riskPointContent);
        for (int i = 0; i < detailsList.size(); i++) {
            if(detailsList.get(i).getList().size() > 0){
                for(int j = 0; j < detailsList.get(i).getList().size(); j++){  // 查询出来的结果
                    if(detailsList.get(i).getList().get(j).getResultList().size() > 0){
                        if(Integer.valueOf((int) CommonUtils.getDateStringDistanceDays(CommonUtils.getPresenttime(),detailsList.get(i).getList().get(j).getResultList().get(0).getInsertTime())) > Integer.valueOf(detailsList.get(i).getPersonsList().get(0).getFrequency())){
                            detailsLists.add(detailsList.get(i));
                        }
                        break;
                    }else{
                        if(detailsList.get(i).getPersonsList().size() > 0 ){
                            if(Integer.valueOf((int) CommonUtils.getDateStringDistanceDays(CommonUtils.getPresenttime(),detailsList.get(i).getInsertTime())) > Integer.valueOf(detailsList.get(i).getPersonsList().get(0).getFrequency())){
                                detailsLists.add(detailsList.get(i));
                            }
                        }
                        break;
                    }
                }
            }
        }
       return detailsLists;
    }
}