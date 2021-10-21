package com.jindan.jdy.service.security;

import com.jindan.jdy.common.pojo.SecurityRiskcontent;
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
public interface SecurityRiskcontentService extends IService<SecurityRiskcontent> {

    /*根据风险点id和巡检分类id查询风险内容*/
    List<SecurityRiskcontent> selectRiskcontentList(List<SecurityRiskcontent> securityRiskcontentList);

}
