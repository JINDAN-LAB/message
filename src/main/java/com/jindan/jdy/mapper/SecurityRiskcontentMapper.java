package com.jindan.jdy.mapper;

import com.jindan.jdy.common.pojo.SecurityRiskcontent;
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
public interface SecurityRiskcontentMapper extends BaseMapper<SecurityRiskcontent> {

    /*根据风险点id和巡检分类id查询风险内容*/
    List<SecurityRiskcontent> getRiskcontentList (List<SecurityRiskcontent> List);

}
