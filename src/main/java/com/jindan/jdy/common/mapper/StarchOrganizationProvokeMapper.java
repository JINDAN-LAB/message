package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationProvokeDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchOrganizationProvoke;

import java.util.List;

/**   
 * @Description:TODO(资产调拨数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationProvokeMapper extends BaseMapper<StarchOrganizationProvoke> {

    List<StarchOrganizationProvokeDto> queryRelevanceCatList(StarchOrganizationProvokeDto jdyFlowCatalog);
}
