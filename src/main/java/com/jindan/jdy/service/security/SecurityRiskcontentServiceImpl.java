package com.jindan.jdy.service.security;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.pojo.SecurityRiskcontent;
import com.jindan.jdy.mapper.SecurityRiskcontentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-10-12
 */
@Service
public class SecurityRiskcontentServiceImpl extends ServiceImpl<SecurityRiskcontentMapper, SecurityRiskcontent> implements SecurityRiskcontentService {

    @Autowired
    private SecurityRiskcontentMapper securityRiskcontentMapper;

    @Override
    public List<SecurityRiskcontent> selectRiskcontentList(List<SecurityRiskcontent> securityRiskcontentList) {
        return securityRiskcontentMapper.getRiskcontentList(securityRiskcontentList);
    }
}
