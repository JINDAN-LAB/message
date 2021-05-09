package com.jindan.jdy.service.stock;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.pojo.StarchOrganizationPutSubtype;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(资产子标题分类服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutSubtypeService extends IService<StarchOrganizationPutSubtype> {

    List<StarchOrganizationPutSubtype> queryCatList(StarchOrganizationPutSubtype jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchOrganizationPutSubtype warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationPutSubtype departmentSuggestDto);

}