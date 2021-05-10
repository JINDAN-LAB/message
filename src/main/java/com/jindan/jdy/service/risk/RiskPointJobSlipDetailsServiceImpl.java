package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.RiskPointJobSlipDetailsDao;
import com.jindan.jdy.common.pojo.RiskPointJobSlipDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(作业票内容服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointJobSlipDetailsServiceImpl  extends ServiceImpl<RiskPointJobSlipDetailsDao,RiskPointJobSlipDetails> implements RiskPointJobSlipDetailsService  {

    @Autowired
    RiskPointJobSlipDetailsDao riskPointJobSlipDetailsDao;

    @Override
    public List<RiskPointJobSlipDetails> seleList(RiskPointJobSlipDetails riskPointContent) {
        QueryWrapper<RiskPointJobSlipDetails> queryWrapper = new QueryWrapper<>();
        if(riskPointContent.getDid() != null && !riskPointContent.getDid().equals("") ){
            queryWrapper.eq("did",riskPointContent.getDid());
        }
        if(riskPointContent.getParentId() != null && !riskPointContent.getParentId().equals("") ){
            queryWrapper.eq("parent_id",riskPointContent.getParentId());
        }
        if(riskPointContent.getXtitle() != null && !riskPointContent.getXtitle().equals("") ){
            queryWrapper.eq("xtitle",riskPointContent.getXtitle());
        }
        if(riskPointContent.getDcontent() != null && !riskPointContent.getDcontent().equals("") ){
            queryWrapper.eq("dcontent",riskPointContent.getDcontent());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getRemarks() != null && !riskPointContent.getRemarks().equals("") ){
            queryWrapper.eq("remarks",riskPointContent.getRemarks());
        }
        return riskPointJobSlipDetailsDao.selectList(queryWrapper);
    }



}