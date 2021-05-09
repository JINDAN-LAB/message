package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchRegisterPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(维修登记商品关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchRegisterPutService extends IService<StarchRegisterPut> {

    List<StarchRegisterPut> queryCatList(StarchRegisterPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchRegisterPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchRegisterPut departmentSuggestDto);
}