package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WarehouseSpecsDto;
import com.jindan.jdy.common.pojo.JdyCommodity;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WarehouseSpecs;

import java.util.List;

/**   
 * @Description:TODO(仓库管理数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WarehouseSpecsMapper extends BaseMapper<WarehouseSpecs> {


    List<WarehouseSpecs> selectListreload(WarehouseSpecsDto departmentSuggestDto);

    int updateNumSpecs(JdyCommodity jdyCommodity);

    int updateOutNumSpecs(JdyCommodity jdyCommodity);
}
