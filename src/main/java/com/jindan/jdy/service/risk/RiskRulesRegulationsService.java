package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskRulesRegulationsDto;
import com.jindan.jdy.common.pojo.RiskRulesRegulations;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-08-31
 */
public interface RiskRulesRegulationsService extends IService<RiskRulesRegulations> {

    String updateFileUrl(MultipartFile file) throws Exception ;

    Page<RiskRulesRegulations> selectRiskRulesByPage(RiskRulesRegulationsDto riskRulesRegulationsDto);

    RiskRulesRegulations selectRiskRules(RiskRulesRegulationsDto riskRulesRegulationsDto);
}
