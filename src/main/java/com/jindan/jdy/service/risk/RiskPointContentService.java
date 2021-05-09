package com.jindan.jdy.service.risk;

import com.jindan.jdy.common.dto.RiskPointContentDto;
import com.jindan.jdy.common.pojo.RiskPointContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.RiskPointPersons;

import java.util.List;

/**   
 * @Description:TODO(风险控制点内容服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointContentService extends IService<RiskPointContent> {

    List<RiskPointContent> seleListWapper(RiskPointContent riskPointContent);

    List<RiskPointContent> seletPersonsContent(RiskPointPersons riskPointContent) throws Exception;

    List<RiskPointContentDto> seleListJieGuoWapper(RiskPointContent riskPointContent);

    List<RiskPointContentDto> seleteSaoMaBaohan(String riskPointContent,String username);
}