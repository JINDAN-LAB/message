package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchAccessPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(领用和退库关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchAccessPutService extends IService<StarchAccessPut> {

    List<StarchAccessPut> queryCatList(StarchAccessPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchAccessPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchAccessPut departmentSuggestDto);
}