package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAccess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;

/**   
 * @Description:TODO(资产领用与退库服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationAccessService extends IService<StarchOrganizationAccess> {

    boolean addJdyFlowCatalog(StarchOrganizationAccessDto departmentSuggestDto);

    boolean updateStarchMaintainRegister(StarchOrganizationAccessDto warehouseDepository);

    Page<StarchOrganizationAccess> queryCatList(StarchOrganizationAccessDto jdyFlowCatalog);

    PageInfo<StarchOrganizationAccessDto> queryRelevanceCatList(StarchOrganizationAccessDto jdyFlowCatalog);

    boolean removeDetailsById(String id);

    boolean updateZhuangtaiStarchMaintainRegister(StarchOrganizationAccessDto warehouseDepository);


    boolean updateSinglesStarchManageCheck(StarchOrganizationPutConsumableChuru warehouseDepository);
}