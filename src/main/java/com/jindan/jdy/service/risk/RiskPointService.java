package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.dto.RiskPointDto;
import com.jindan.jdy.common.pojo.RiskPoint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.RiskPointContent;

import java.util.List;

/**   
 * @Description:TODO(风险控制服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointService extends IService<RiskPoint> {

    List<RiskPoint> seleListWapper(RiskPoint riskPointContent);

    List<RiskPointDto> seleListBaohanWapper(RiskPoint riskPointContent);

    RiskPoint insertSave(RiskPoint riskPoint);
}