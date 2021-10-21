package com.jindan.jdy.mapper;

import com.jindan.jdy.common.dto.SecurityRiskpointJudgedDto;
import com.jindan.jdy.common.pojo.SecurityRiskpointJudged;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liangfang
 * @since 2021-10-12
 */
@Mapper
public interface SecurityRiskpointJudgedMapper extends BaseMapper<SecurityRiskpointJudged> {

    /*查询单个风险点信息*/
    SecurityRiskpointJudgedDto getRiskpointJudgedByOne (SecurityRiskpointJudged securityRiskpointJudged);

    /*excel表格导出*/
    List<SecurityRiskpointJudgedDto> getRiskpointJudgedExcel(List<SecurityRiskpointJudged> list);

    /*批量设置风险点状态*/
    int updateRiskpointStatus(List<SecurityRiskpointJudged> list);
}
