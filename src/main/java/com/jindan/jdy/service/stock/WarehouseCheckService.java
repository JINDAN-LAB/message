package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(盘点单服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseCheckService extends IService<WarehouseCheck> {

    Page<WarehouseCheck> seletelist(WarehouseCheckDto departmentSuggestDto);
}