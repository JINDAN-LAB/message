package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAccess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(资产领用与退库数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationAccessMapper extends BaseMapper<StarchOrganizationAccess> {

    List<StarchOrganizationAccessDto> queryRelevanceCatList(StarchOrganizationAccessDto jdyFlowCatalog);
}
