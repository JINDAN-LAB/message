package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyFlowCatalogDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyFlowCatalog;

import java.util.List;

/**   
 * @Description:TODO(流程目录信息数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyFlowCatalogMapper extends BaseMapper<JdyFlowCatalog> {

    List<JdyFlowCatalogDto> selectAll(JdyFlowCatalog jdyFlowCatalog);
}
