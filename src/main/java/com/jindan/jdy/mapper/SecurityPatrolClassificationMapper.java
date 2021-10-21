package com.jindan.jdy.mapper;

import com.jindan.jdy.common.dto.SecurityPatrolClassificationDto;
import com.jindan.jdy.common.pojo.SecurityPatrolClassification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangfang
 * @since 2021-10-14
 */
@Mapper
public interface SecurityPatrolClassificationMapper extends BaseMapper<SecurityPatrolClassification> {

    /*根据风险点id查询巡检分类*/
    List<SecurityPatrolClassificationDto> getPatrolClassificationList (SecurityPatrolClassification securityPatrolClassification);
}
