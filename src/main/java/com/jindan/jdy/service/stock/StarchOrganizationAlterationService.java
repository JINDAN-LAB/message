package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAlterationDto;
import com.jindan.jdy.common.pojo.StarchOrganizationAlteration;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(资产信息变更服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationAlterationService extends IService<StarchOrganizationAlteration> {

    Page<StarchOrganizationAlteration> queryCatList(StarchOrganizationAlterationDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchOrganizationAlteration warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationAlteration departmentSuggestDto);

    PageInfo<StarchOrganizationAlterationDto> queryRelevanceCatList(StarchOrganizationAlterationDto jdyFlowCatalog);
}