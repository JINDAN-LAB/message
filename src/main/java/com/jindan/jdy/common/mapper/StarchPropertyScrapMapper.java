package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchPropertyScrapDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchPropertyScrap;

import java.util.List;

/**   
 * @Description:TODO(清理报废数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchPropertyScrapMapper extends BaseMapper<StarchPropertyScrap> {

    List<StarchPropertyScrapDto> queryRelevanceCatList(StarchPropertyScrapDto jdyFlowCatalog);
}
