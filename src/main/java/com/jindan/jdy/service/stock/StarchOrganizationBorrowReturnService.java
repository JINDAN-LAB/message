package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationBorrowReturnDto;
import com.jindan.jdy.common.pojo.StarchOrganizationBorrowReturn;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(资产借用与归还服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationBorrowReturnService extends IService<StarchOrganizationBorrowReturn> {

    Page<StarchOrganizationBorrowReturn> queryCatList(StarchOrganizationBorrowReturnDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchOrganizationBorrowReturnDto warehouseDepository);

    boolean addJdyFlowCatalog(StarchOrganizationBorrowReturnDto departmentSuggestDto);

    PageInfo<StarchOrganizationBorrowReturnDto> queryRelevanceCatList(StarchOrganizationBorrowReturnDto jdyFlowCatalog);

    boolean removePointPracticableById(String id);

    boolean updateStarchGuihuanMaintainRegister(StarchOrganizationBorrowReturnDto warehouseDepository);
}