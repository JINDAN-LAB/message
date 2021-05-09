package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.StarchManageCheckDto;
import com.jindan.jdy.common.pojo.StarchManageCheck;
/**   
 * @Description:TODO(盘点管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchManageCheckService extends IService<StarchManageCheck> {

    Page<StarchManageCheck> queryCatList(StarchManageCheckDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchManageCheck warehouseDepository);

    boolean addJdyFlowCatalog(StarchManageCheck departmentSuggestDto);
}