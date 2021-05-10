package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(耗材资产数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationPutConsumableMapper extends BaseMapper<StarchOrganizationPutConsumable> {

    List<StarchOrganizationPutConsumableDto> queryRelevanceCatList(StarchOrganizationPutConsumableDto jdyFlowCatalog);
}
