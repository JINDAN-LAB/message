package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskPointContentChaoqiDto;
import com.jindan.jdy.common.pojo.RiskPointContentChaoqi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(风险控制任务超期服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface RiskPointContentChaoqiService extends IService<RiskPointContentChaoqi> {

    Page<RiskPointContentChaoqi> seleListWapper(RiskPointContentChaoqiDto riskPointContent);

}