package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.RiskPointJobSlipDetails;

import java.util.List;

/**   
 * @Description:TODO(作业票内容服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointJobSlipDetailsService extends IService<RiskPointJobSlipDetails> {

    List<RiskPointJobSlipDetails> seleList(RiskPointJobSlipDetails riskPointContent);
}