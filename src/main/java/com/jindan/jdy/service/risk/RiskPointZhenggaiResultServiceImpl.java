package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.jindan.jdy.common.mapper.RiskPointDao;
import com.jindan.jdy.common.pojo.RiskPointMakeover;
import com.jindan.jdy.common.pojo.RiskPointZhenggaiResult;
import com.jindan.jdy.common.mapper.RiskPointZhenggaiResultDao;
import com.jindan.jdy.service.risk.RiskPointZhenggaiResultService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(整改返回结果服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointZhenggaiResultServiceImpl  extends ServiceImpl<RiskPointZhenggaiResultDao,RiskPointZhenggaiResult> implements RiskPointZhenggaiResultService  {

    @Autowired
    RiskPointZhenggaiResultDao riskPointZhenggaiResultDao;

    @Override
    public List<RiskPointZhenggaiResult> seleListWapper(RiskPointZhenggaiResult riskPointContent) {

        QueryWrapper<RiskPointZhenggaiResult> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getZid() != null && !riskPointContent.getZid().equals("")){
            queryWrapper.eq("zid",riskPointContent.getZid());
        }
        if(riskPointContent.getZhenggaiTime() != null && !riskPointContent.getZhenggaiTime().equals("")){
            queryWrapper.eq("zhenggai_time",riskPointContent.getZhenggaiTime());
        }
        if(riskPointContent.getZhenggaiRiqi() != null && !riskPointContent.getZhenggaiRiqi().equals("")){
            queryWrapper.eq("zhenggai_riqi",riskPointContent.getZhenggaiRiqi());
        }
        if(riskPointContent.getZhenggaiCuoshi() != null && !riskPointContent.getZhenggaiCuoshi().equals("")){
            queryWrapper.eq("zhenggai_cuoshi",riskPointContent.getZhenggaiCuoshi());
        }
        if(riskPointContent.getZijin() != null && !riskPointContent.getZijin().equals("")){
            queryWrapper.eq("zijin",riskPointContent.getZijin());
        }
        if(riskPointContent.getFuzePerson() != null && !riskPointContent.getFuzePerson().equals("")){
            queryWrapper.eq("fuze_person",riskPointContent.getFuzePerson());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("")){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getYanshou() != null && !riskPointContent.getYanshou().equals("")){
            queryWrapper.eq("yanshou",riskPointContent.getYanshou());
        }
        if(riskPointContent.getYanshouTime() != null && !riskPointContent.getYanshouTime().equals("")){
            queryWrapper.eq("yanshou_time",riskPointContent.getYanshouTime());
        }
        if(riskPointContent.getYanshouName() != null && !riskPointContent.getYanshouName().equals("")){
            queryWrapper.eq("yanshou_name",riskPointContent.getYanshouName());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("")){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        return riskPointZhenggaiResultDao.selectList(queryWrapper);
    }

}