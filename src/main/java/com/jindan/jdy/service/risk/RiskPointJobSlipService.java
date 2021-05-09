package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.pojo.RiskPointJobSlip;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(作业票服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointJobSlipService extends IService<RiskPointJobSlip> {

    List<RiskPointJobSlip> seleList(RiskPointJobSlip riskPointContent);
}