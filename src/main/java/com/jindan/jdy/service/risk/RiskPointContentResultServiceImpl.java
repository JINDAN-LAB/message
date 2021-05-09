package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskPointContentResultDto;
import com.jindan.jdy.common.dto.RiskPointDetails;
import com.jindan.jdy.common.mapper.RiskPointContentResultMapper;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(风险控制点内容返回值服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointContentResultServiceImpl  extends ServiceImpl<RiskPointContentResultMapper,RiskPointContentResult> implements RiskPointContentResultService  {

    @Autowired
    RiskPointContentResultMapper riskPointContentResultDao;

    @Override
    public List<RiskPointContentResult> seleListWapper(RiskPointContentResult riskPointContent) {
        QueryWrapper<RiskPointContentResult> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("") ){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getRisPcr() != null && !riskPointContent.getRisPcr().equals("") ){
            queryWrapper.eq("ris_pcr",riskPointContent.getRisPcr());
        }
        if(riskPointContent.getPatrolResult() != null && !riskPointContent.getPatrolResult().equals("") ){
            queryWrapper.eq("patrol_result",riskPointContent.getPatrolResult());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getPatrolResultDetails() != null && !riskPointContent.getPatrolResultDetails().equals("") ){
            queryWrapper.eq("patrol_result_details",riskPointContent.getPatrolResultDetails());
        }
        if(riskPointContent.getPatrolResultJibie() != null && !riskPointContent.getPatrolResultJibie().equals("") ){
            queryWrapper.eq("patrol_result_jibie",riskPointContent.getPatrolResultJibie());
        }
        if(riskPointContent.getHiddenHeading() != null && !riskPointContent.getHiddenHeading().equals("") ){
            queryWrapper.eq("hidden_heading",riskPointContent.getHiddenHeading());
        }
        if(riskPointContent.getHiddenSubclass() != null && !riskPointContent.getHiddenSubclass().equals("") ){
            queryWrapper.eq("hidden_subclass",riskPointContent.getHiddenSubclass());
        }
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("") ){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getAbarbeitungPerson() != null && !riskPointContent.getAbarbeitungPerson().equals("") ){
            queryWrapper.eq("abarbeitung_person",riskPointContent.getAbarbeitungPerson());
        }
        if(riskPointContent.getDuplicatePerson() != null && !riskPointContent.getDuplicatePerson().equals("") ){
            queryWrapper.eq("duplicate_person",riskPointContent.getDuplicatePerson());
        }
        if(riskPointContent.getPeopleName() != null && !riskPointContent.getPeopleName().equals("") ){
            queryWrapper.eq("people_name",riskPointContent.getPeopleName());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getHiddenLocation() != null && !riskPointContent.getHiddenLocation().equals("") ){
            queryWrapper.eq("hidden_location",riskPointContent.getHiddenLocation());
        }
        if(riskPointContent.getHiddenJibie() != null && !riskPointContent.getHiddenJibie().equals("") ){
            queryWrapper.eq("hidden_jibie",riskPointContent.getHiddenJibie());
        }
        if(riskPointContent.getZhuanrangTime() != null && !riskPointContent.getZhuanrangTime().equals("") ){
            queryWrapper.eq("zhuanrang_time",riskPointContent.getZhuanrangTime());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getRshebei() != null && !riskPointContent.getRshebei().equals("") ){
            queryWrapper.eq("rshebei",riskPointContent.getRshebei());
        }
        queryWrapper.orderByAsc("insert_time");
        return riskPointContentResultDao.selectList(queryWrapper);
    }


    @Override
    public Page<RiskPointContentResult> seleFenyeListWapper(RiskPointContentResultDto riskPointContent) {
        if(riskPointContent.getCurrentPage() <= 0  ){
            riskPointContent.setCurrentPage(1);
        }
        Page<RiskPointContentResult> page =new Page<>(riskPointContent.getCurrentPage(),riskPointContent.getPageSize());
        QueryWrapper<RiskPointContentResult> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("")){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getRisPcr() != null && !riskPointContent.getRisPcr().equals("") ){
            queryWrapper.eq("ris_pcr",riskPointContent.getRisPcr());
        }
        if(riskPointContent.getPatrolResult() != null && !riskPointContent.getPatrolResult().equals("") ){
            queryWrapper.eq("patrol_result",riskPointContent.getPatrolResult());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getPatrolResultDetails() != null && !riskPointContent.getPatrolResultDetails().equals("") ){
            queryWrapper.eq("patrol_result_details",riskPointContent.getPatrolResultDetails());
        }
        if(riskPointContent.getPatrolResultJibie() != null && !riskPointContent.getPatrolResultJibie().equals("") ){
            queryWrapper.eq("patrol_result_jibie",riskPointContent.getPatrolResultJibie());
        }
        if(riskPointContent.getHiddenHeading() != null && !riskPointContent.getHiddenHeading().equals("") ){
            queryWrapper.eq("hidden_heading",riskPointContent.getHiddenHeading());
        }
        if(riskPointContent.getHiddenSubclass() != null && !riskPointContent.getHiddenSubclass().equals("") ){
            queryWrapper.eq("hidden_subclass",riskPointContent.getHiddenSubclass());
        }
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("") ){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getAbarbeitungPerson() != null && !riskPointContent.getAbarbeitungPerson().equals("") ){
            queryWrapper.eq("abarbeitung_person",riskPointContent.getAbarbeitungPerson());
        }
        if(riskPointContent.getDuplicatePerson() != null && !riskPointContent.getDuplicatePerson().equals("") ){
            queryWrapper.eq("duplicate_person",riskPointContent.getDuplicatePerson());
        }
        if(riskPointContent.getPeopleName() != null && !riskPointContent.getPeopleName().equals("") ){
            queryWrapper.eq("people_name",riskPointContent.getPeopleName());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getHiddenLocation() != null && !riskPointContent.getHiddenLocation().equals("") ){
            queryWrapper.eq("hidden_location",riskPointContent.getHiddenLocation());
        }
        if(riskPointContent.getHiddenJibie() != null && !riskPointContent.getHiddenJibie().equals("") ){
            queryWrapper.eq("hidden_jibie",riskPointContent.getHiddenJibie());
        }
        if(riskPointContent.getZhuanrangTime() != null && !riskPointContent.getZhuanrangTime().equals("") ){
            queryWrapper.eq("zhuanrang_time",riskPointContent.getZhuanrangTime());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getRshebei() != null && !riskPointContent.getRshebei().equals("") ){
            queryWrapper.eq("rshebei",riskPointContent.getRshebei());
        }
        queryWrapper.orderByAsc("insert_time");
        return (Page<RiskPointContentResult>) riskPointContentResultDao.selectPage(page,queryWrapper);
    }

    @Override
    public List<RiskPointDetails> seleListDetailsWapper(RiskPointContentResult riskPointContent) throws Exception {
        QueryWrapper<RiskPointContentResult> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("") ){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getRisPcr() != null && !riskPointContent.getRisPcr().equals("") ){
            queryWrapper.eq("ris_pcr",riskPointContent.getRisPcr());
        }
        if(riskPointContent.getPatrolResult() != null && !riskPointContent.getPatrolResult().equals("") ){
            queryWrapper.eq("patrol_result",riskPointContent.getPatrolResult());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getPatrolResultDetails() != null && !riskPointContent.getPatrolResultDetails().equals("") ){
            queryWrapper.eq("patrol_result_details",riskPointContent.getPatrolResultDetails());
        }
        if(riskPointContent.getPatrolResultJibie() != null && !riskPointContent.getPatrolResultJibie().equals("") ){
            queryWrapper.eq("patrol_result_jibie",riskPointContent.getPatrolResultJibie());
        }
        if(riskPointContent.getHiddenHeading() != null && !riskPointContent.getHiddenHeading().equals("") ){
            queryWrapper.eq("hidden_heading",riskPointContent.getHiddenHeading());
        }
        if(riskPointContent.getHiddenSubclass() != null && !riskPointContent.getHiddenSubclass().equals("") ){
            queryWrapper.eq("hidden_subclass",riskPointContent.getHiddenSubclass());
        }
        if(riskPointContent.getAbarbeitungDeadline() != null && !riskPointContent.getAbarbeitungDeadline().equals("") ){
            queryWrapper.eq("abarbeitung_deadline",riskPointContent.getAbarbeitungDeadline());
        }
        if(riskPointContent.getAbarbeitungPerson() != null && !riskPointContent.getAbarbeitungPerson().equals("") ){
            queryWrapper.eq("abarbeitung_person",riskPointContent.getAbarbeitungPerson());
        }
        if(riskPointContent.getDuplicatePerson() != null && !riskPointContent.getDuplicatePerson().equals("") ){
            queryWrapper.eq("duplicate_person",riskPointContent.getDuplicatePerson());
        }
        if(riskPointContent.getPeopleName() != null && !riskPointContent.getPeopleName().equals("") ){
            queryWrapper.eq("people_name",riskPointContent.getPeopleName());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getHiddenLocation() != null && !riskPointContent.getHiddenLocation().equals("") ){
            queryWrapper.eq("hidden_location",riskPointContent.getHiddenLocation());
        }
        if(riskPointContent.getHiddenJibie() != null && !riskPointContent.getHiddenJibie().equals("") ){
            queryWrapper.eq("hidden_jibie",riskPointContent.getHiddenJibie());
        }
        if(riskPointContent.getZhuanrangTime() != null && !riskPointContent.getZhuanrangTime().equals("") ){
            queryWrapper.eq("zhuanrang_time",riskPointContent.getZhuanrangTime());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getRshebei() != null && !riskPointContent.getRshebei().equals("") ){
            queryWrapper.eq("rshebei",riskPointContent.getRshebei());
        }
        queryWrapper.orderByAsc("insert_time");
        List<RiskPointContentResult> riskPointContentResults = riskPointContentResultDao.selectList(queryWrapper);
        List<String> lists = new ArrayList<>();
        Map<String,String> stringMap = new HashMap<>();
        for (int i = 0; i < riskPointContentResults.size(); i++) {
            if(!stringMap.containsKey(riskPointContentResults.get(i).getRisPcr())){
                stringMap.put(riskPointContentResults.get(i).getRisPcr(),riskPointContentResults.get(i).getRisPcr());
                lists.add(riskPointContentResults.get(i).getRisPcr());
            }
        }
        if(lists.size() > 0){
            return riskPointContentResultDao.seleListDetailsWapper(lists);
        }
        return null;
    }

}