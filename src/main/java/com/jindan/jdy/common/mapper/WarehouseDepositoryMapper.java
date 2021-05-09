package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WarehouseDepositoryDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WarehouseDepository;

import java.util.List;

/**   
 * @Description:TODO(仓库管理数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WarehouseDepositoryMapper extends BaseMapper<WarehouseDepository> {

//    仓库目录
    List<WarehouseDepositoryDto> selectListWarehouseDepositoryDao(WarehouseDepositoryDto departmentSuggestDto);

    List<WarehouseDepositoryDto> selectAllList();

    List<WarehouseDepositoryDto> selectAllTiaojianList(WarehouseDepository warehouseDepository);
}
