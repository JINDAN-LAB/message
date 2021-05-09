package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.dto.WarehouseDepositoryDto;
import com.jindan.jdy.common.pojo.WarehouseDepository;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.WarehouseSpecs;

import java.util.List;

/**   
 * @Description:TODO(仓库管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseDepositoryService extends IService<WarehouseDepository> {

    List<WarehouseDepository> seleteWarehouseDepository(WarehouseDepositoryDto departmentSuggestDto);

    List<WarehouseDepositoryDto> seleteWarehouseDepositoryDto(WarehouseDepositoryDto departmentSuggestDto);

    List<WarehouseDepositoryDto> queryFenleiCatList();

    List<WarehouseSpecs> queryFenleiIDCatList(WarehouseDepository warehouseDepository);

    List<WarehouseDepository> queryList();

}