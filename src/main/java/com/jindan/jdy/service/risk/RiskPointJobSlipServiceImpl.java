package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.RiskPointJobSlipDao;
import com.jindan.jdy.common.pojo.RiskPointJobSlip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(作业票服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointJobSlipServiceImpl  extends ServiceImpl<RiskPointJobSlipDao,RiskPointJobSlip> implements RiskPointJobSlipService  {

    @Autowired
    RiskPointJobSlipDao riskPointJobSlipDao;

    @Override
    public List<RiskPointJobSlip> seleList(RiskPointJobSlip riskPointContent) {

        QueryWrapper<RiskPointJobSlip> queryWrapper = new QueryWrapper<>();
        if(riskPointContent.getZid() != null && !riskPointContent.getZid().equals("") ){
            queryWrapper.eq("zid",riskPointContent.getZid());
        }
        if(riskPointContent.getZuoyeType() != null && !riskPointContent.getZuoyeType().equals("") ){
            queryWrapper.eq("zuoye_type",riskPointContent.getZuoyeType());
        }
        if(riskPointContent.getStatus() != null && !riskPointContent.getStatus().equals("") ){
            queryWrapper.eq("status",riskPointContent.getStatus());
        }
        if(riskPointContent.getRemarks() != null && !riskPointContent.getRemarks().equals("") ){
            queryWrapper.eq("remarks",riskPointContent.getRemarks());
        }
        return riskPointJobSlipDao.selectList(queryWrapper);
    }


}