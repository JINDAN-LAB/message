package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.pojo.RiskRenewalHistory;
import com.jindan.jdy.mapper.RiskRenewalHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-07-02
 */
@Service
public class RiskRenewalHistoryServiceImpl extends ServiceImpl<RiskRenewalHistoryMapper, RiskRenewalHistory> implements RiskRenewalHistoryService {

    @Autowired
    private RiskRenewalHistoryMapper riskRenewalHistoryMapper;

    /*@Override
    public RiskRenewalHistory insert(RiskRenewalHistory riskRenewalHistory) {
        if(riskRenewalHistoryMapper.insert(riskRenewalHistory) > 0){
            return riskRenewalHistory;
        }
        return riskRenewalHistory;
    }*/
}
