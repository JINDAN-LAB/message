package com.jindan.jdy.service.security;

import com.jindan.jdy.common.dto.SecurityPatrolClassificationDto;
import com.jindan.jdy.common.pojo.SecurityPatrolClassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-10-14
 */
public interface SecurityPatrolClassificationService extends IService<SecurityPatrolClassification> {

    /*根据风险点id查询巡检分类*/
    List<SecurityPatrolClassificationDto> selectPatrolClassificationList(SecurityPatrolClassification securityPatrolClassification);

}
