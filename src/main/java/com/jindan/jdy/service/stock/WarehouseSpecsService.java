package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.dto.WarehouseSpecsDto;
import com.jindan.jdy.common.pojo.JdyCommodity;
import com.jindan.jdy.common.pojo.WarehouseSpecs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(仓库管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseSpecsService extends IService<WarehouseSpecs> {

    List<WarehouseSpecs> seleteWarehouseSpecs(WarehouseSpecsDto departmentSuggestDto);

//    入库操作
    int updateNumSpecs(JdyCommodity jdyCommodity);

//      出库操作
    int updateOutNumSpecs(JdyCommodity jdyCommodity);
}