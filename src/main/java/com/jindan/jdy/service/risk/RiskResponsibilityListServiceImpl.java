package com.jindan.jdy.service.risk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.DepartmentAndDutiesDto;
import com.jindan.jdy.common.pojo.RiskResponsibilityList;
import com.jindan.jdy.mapper.RiskResponsibilityListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-06-17
 */

@Service
public class RiskResponsibilityListServiceImpl extends ServiceImpl<RiskResponsibilityListMapper, RiskResponsibilityList> implements RiskResponsibilityListService {

    @Autowired
    private RiskResponsibilityListMapper riskResponsibilityListMapper;

    @Override
    public RiskResponsibilityList insertSave(RiskResponsibilityList riskResponsibilityList) {
        if(riskResponsibilityListMapper.insert(riskResponsibilityList) > 0){
            return riskResponsibilityList;
        }
        return riskResponsibilityList;
    }

    @Override
    public List<RiskResponsibilityList> selectRiskResponsibilityList(DepartmentAndDutiesDto departmentAndDutiesDto) {
        QueryWrapper<RiskResponsibilityList> queryWrapper = new QueryWrapper<>();
        if (departmentAndDutiesDto.getJdyUserId() != null && !departmentAndDutiesDto.getJdyUserId().equals("")){
            queryWrapper.eq("jdy_user_id",departmentAndDutiesDto.getJdyUserId());
        }
        return riskResponsibilityListMapper.selectList(queryWrapper);
    }
}
