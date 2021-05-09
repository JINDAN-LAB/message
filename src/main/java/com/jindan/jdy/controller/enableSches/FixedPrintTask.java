package com.jindan.jdy.controller.enableSches;

import com.jindan.jdy.common.dto.RiskPointDtoDetails;
import com.jindan.jdy.common.pojo.RiskPointContentChaoqi;
import com.jindan.jdy.controller.utils.CommonUtils;
import com.jindan.jdy.service.risk.RiskPointContentChaoqiService;
import com.jindan.jdy.service.risk.RiskPointPersonsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FixedPrintTask {

    @Autowired
    RiskPointPersonsService riskPointMakeoverService;

    @Autowired
    RiskPointContentChaoqiService riskPointContentChaoqiService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    @Scheduled(cron = "0 30 23 * * ?")
    public void execute() throws Exception {
        List<RiskPointContentChaoqi>  chaoqiList  = new ArrayList<>();
        List<RiskPointDtoDetails> detailsList = riskPointMakeoverService.seleListchaoqiBaohanrenwuWapper(null);
        for (int i = 0; i <detailsList.size() ; i++) {
            for (int j = 0; j < detailsList.get(i).getList().size(); j++) {
                RiskPointContentChaoqi riskPointContentChaoqi = new RiskPointContentChaoqi();
                riskPointContentChaoqi.setChaoqiTime(CommonUtils.getPresenttime());
                riskPointContentChaoqi.setContentId(detailsList.get(i).getList().get(j).getRiskIds());
                riskPointContentChaoqi.setChaoqiPerson(detailsList.get(i).getList().get(j).getRiskContents());
                riskPointContentChaoqi.setNums("1");
                chaoqiList.add(riskPointContentChaoqi);
            }
        }
        //  输出超期的日期
        boolean b = riskPointContentChaoqiService.saveBatch(chaoqiList);
    }

}
