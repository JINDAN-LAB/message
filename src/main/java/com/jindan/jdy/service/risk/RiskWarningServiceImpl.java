package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.RiskWarningDto;
import com.jindan.jdy.common.pojo.RiskWarning;
import com.jindan.jdy.mapper.RiskWarningDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-04
 */
@Service
public class RiskWarningServiceImpl extends ServiceImpl<RiskWarningDao, RiskWarning> implements RiskWarningService {

    @Autowired
    private RiskWarningDao riskWarningDao;

    @Override
    public Page<RiskWarning> seleteRiskWarning(RiskWarningDto riskWarningDto) {

        if(riskWarningDto.getCurrentPage() <= 0  ){
            riskWarningDto.setCurrentPage(1);
        }
        Page<RiskWarning> page =new Page<>(riskWarningDto.getCurrentPage(),riskWarningDto.getPageSize());

        QueryWrapper<RiskWarning> queryWrapper =new QueryWrapper<>();

        if( riskWarningDto.getWarningTime() !=null && riskWarningDto.getEndWarningTime() != null){
            queryWrapper.between("warning_time",riskWarningDto.getWarningTime(),riskWarningDto.getEndWarningTime());
        }else if(riskWarningDto.getWarningTime() != null && riskWarningDto.getEndWarningTime() == null){
            queryWrapper.ge("warning_time",riskWarningDto.getWarningTime());
        }else if(riskWarningDto.getWarningTime() == null && riskWarningDto.getEndWarningTime() != null){
            queryWrapper.le("warning_time",riskWarningDto.getEndWarningTime());
        }

        return (Page<RiskWarning>) riskWarningDao.selectPage(page,queryWrapper);

    }
}
