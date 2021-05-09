package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationProvokeDto;
import com.jindan.jdy.common.pojo.StarchOrganizationProvoke;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(资产调拨服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationProvokeService extends IService<StarchOrganizationProvoke> {

    Page<StarchOrganizationProvoke> queryCatList(StarchOrganizationProvokeDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchOrganizationProvokeDto warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationProvokeDto departmentSuggestDto);

    PageInfo<StarchOrganizationProvokeDto> queryRelevanceCatList(StarchOrganizationProvokeDto jdyFlowCatalog);

    boolean removeDetailsById(String id);
}