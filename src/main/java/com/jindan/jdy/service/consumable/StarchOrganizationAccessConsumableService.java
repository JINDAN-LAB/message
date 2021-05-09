package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAccessConsumable;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(耗材管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationAccessConsumableService extends IService<StarchOrganizationAccessConsumable> {

    PageInfo<StarchOrganizationAccessConsumableDto> queryRelevanceCatList(StarchOrganizationAccessConsumableDto jdyFlatalog);

    boolean removeDetailsById(String id);

    boolean updateStarchMaintainRegister(StarchOrganizationAccessConsumableDto warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationAccessConsumableDto departmggestDto);

    boolean addJdyQuerenFlowCatalog(StarchOrganizationAccessConsumableDto departmggestDto);
}