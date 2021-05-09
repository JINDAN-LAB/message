package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseAccessDto;
import com.jindan.jdy.common.pojo.KeyPointProject;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WarehouseAccess;

import java.util.List;

/**   
 * @Description:TODO(仓库管理数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WarehouseAccessMapper extends BaseMapper<WarehouseAccess> {

    List<KeyPointProject> seleteKeyPointProject(WarehouseAccessDto departmentSuggestDto);
}
