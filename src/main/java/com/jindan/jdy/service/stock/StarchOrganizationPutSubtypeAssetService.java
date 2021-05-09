package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtype;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtypeAsset;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(资产子设备信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutSubtypeAssetService extends IService<StarchOrganizationPutSubtypeAsset> {

    boolean updateStarchMaintainRegister(StarchOrganizationPutSubtypeAsset warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationPutSubtypeAsset departmentSuggestDto);

    List<StarchOrganizationPutSubtypeAsset> queryCatList(StarchOrganizationPutSubtypeAsset jdyFlowCatalog);
}