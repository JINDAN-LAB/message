package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumable;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(耗材资产服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutConsumableService extends IService<StarchOrganizationPutConsumable> {

    boolean addJdyFlowCatalog(StarchOrganizationPutConsumable departmentSuggestDto);

    boolean updateStarchMaintainRegister(StarchOrganizationPutConsumable warehouseDepository);

    PageInfo<StarchOrganizationPutConsumableDto> queryCatList(StarchOrganizationPutConsumableDto jdyFlowCatalog);
}