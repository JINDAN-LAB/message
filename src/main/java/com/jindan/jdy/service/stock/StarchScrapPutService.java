package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchScrapPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(清理报废商品关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchScrapPutService extends IService<StarchScrapPut> {

    List<StarchScrapPut> queryCatList(StarchScrapPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchScrapPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchScrapPut departmentSuggestDto);
}