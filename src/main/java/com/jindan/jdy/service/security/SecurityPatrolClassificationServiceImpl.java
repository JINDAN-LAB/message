package com.jindan.jdy.service.security;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.SecurityPatrolClassificationDto;
import com.jindan.jdy.common.pojo.SecurityPatrolClassification;
import com.jindan.jdy.mapper.SecurityPatrolClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liangfang
 * @since 2021-10-14
 */
@Service
public class SecurityPatrolClassificationServiceImpl extends ServiceImpl<SecurityPatrolClassificationMapper, SecurityPatrolClassification> implements SecurityPatrolClassificationService {

    @Autowired
    private SecurityPatrolClassificationMapper securityPatrolClassificationMapper;

    @Override
    public List<SecurityPatrolClassificationDto> selectPatrolClassificationList(SecurityPatrolClassification securityPatrolClassification) {
        return securityPatrolClassificationMapper.getPatrolClassificationList(securityPatrolClassification);
    }
}
