package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchProvokePut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(资产调拨商品关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchProvokePutService extends IService<StarchProvokePut> {

    List<StarchProvokePut> queryCatList(StarchProvokePut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchProvokePut warehouseDepository);

    boolean addJdyFlowCatalog(StarchProvokePut departmentSuggestDto);
}