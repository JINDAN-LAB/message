package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.StarchOrganizationAlterationDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchOrganizationAlteration;

import java.util.List;

/**   
 * @Description:TODO(资产信息变更数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationAlterationMapper extends BaseMapper<StarchOrganizationAlteration> {

    List<StarchOrganizationAlterationDto> queryRelevanceCatList(StarchOrganizationAlterationDto jdyFlowCatalog);
}
