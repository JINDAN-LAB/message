package com.jindan.jdy.service.security;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.SecurityRiskpointJudgedDto;
import com.jindan.jdy.common.pojo.SecurityRiskpointJudged;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangfang
 * @since 2021-10-12
 */
public interface SecurityRiskpointJudgedService extends IService<SecurityRiskpointJudged> {

    /*分页查询风险点研判*/
    Page<SecurityRiskpointJudged> selectRiskpointJudgedByPage(SecurityRiskpointJudgedDto securityRiskpointJudgedDto);

    /*查询单个风险点研判*/
    SecurityRiskpointJudgedDto selectRiskpointJudgedByOne(SecurityRiskpointJudged securityRiskpointJudged);

    /*excel表格导出*/
    List<SecurityRiskpointJudgedDto> getRiskpointJudgedExcel(List<SecurityRiskpointJudged> securityRiskpointJudgedList);

    /*批量设置风险点状态*/
    boolean setRiskpointStatus(List<SecurityRiskpointJudged> securityRiskpointJudgedList);
}
