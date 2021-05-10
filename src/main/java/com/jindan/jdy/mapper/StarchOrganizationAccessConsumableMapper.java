package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAccessConsumable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(耗材管理数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchOrganizationAccessConsumableMapper extends BaseMapper<StarchOrganizationAccessConsumable> {

    List<StarchOrganizationAccessConsumableDto> queryRelevanceCatList(StarchOrganizationAccessConsumableDto jdyFlatalog);

}
