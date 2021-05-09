package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.RiskPointContentChaoqiDto;
import com.jindan.jdy.common.pojo.RiskPointContentChaoqi;
import com.jindan.jdy.common.mapper.RiskPointContentChaoqiDao;
import com.jindan.jdy.common.pojo.RiskPointContentResult;
import com.jindan.jdy.service.risk.RiskPointContentChaoqiService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(风险控制任务超期服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class RiskPointContentChaoqiServiceImpl  extends ServiceImpl<RiskPointContentChaoqiDao,RiskPointContentChaoqi> implements RiskPointContentChaoqiService  {

    @Autowired
    RiskPointContentChaoqiDao riskPointContentChaoqiDao;



    @Override
    public Page<RiskPointContentChaoqi> seleListWapper(RiskPointContentChaoqiDto riskPointContent) {

        if(riskPointContent.getCurrentPage() <= 0  ){
            riskPointContent.setCurrentPage(1);
        }
        Page<RiskPointContentChaoqi> page =new Page<>(riskPointContent.getCurrentPage(),riskPointContent.getPageSize());

        QueryWrapper<RiskPointContentChaoqi> queryWrapper =new QueryWrapper<>();
        if(riskPointContent.getCqId() != null && !riskPointContent.getCqId().equals("")){
            queryWrapper.eq("cq_id",riskPointContent.getCqId());
        }
        if(riskPointContent.getContentId() != null && !riskPointContent.getContentId().equals("") ){
            queryWrapper.eq("content_id",riskPointContent.getContentId());
        }
        if(riskPointContent.getChaoqiTime() != null && !riskPointContent.getChaoqiTime().equals("") ){
            queryWrapper.eq("chaoqi_time",riskPointContent.getChaoqiTime());
        }
        if(riskPointContent.getChaoqiPerson() != null && !riskPointContent.getChaoqiPerson().equals("") ){
            queryWrapper.eq("chaoqi_person",riskPointContent.getChaoqiPerson());
        }
        if(riskPointContent.getNums() != null && !riskPointContent.getNums().equals("") ){
            queryWrapper.eq("nums",riskPointContent.getNums());
        }
        queryWrapper.orderByAsc("insert_time");
        return (Page<RiskPointContentChaoqi>) riskPointContentChaoqiDao.selectPage(page,queryWrapper);
    }
}