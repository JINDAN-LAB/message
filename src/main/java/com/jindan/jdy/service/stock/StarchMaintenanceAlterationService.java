package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchMaintenanceAlterationDto;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(资产维保信息变更服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchMaintenanceAlterationService extends IService<StarchMaintenanceAlteration> {

//    查询信息
    PageInfo<StarchMaintenanceAlterationDto> queryCatList(StarchMaintenanceAlterationDto jdyFlowCatalog);
//修改信息
    boolean updateStarchMaintainRegister(StarchMaintenanceAlterationDto warehouseDepository);

//    新增日期
    boolean addJdyFlowCatalog(StarchMaintenanceAlterationDto departmentSuggestDto);

    boolean removeDetailsById(String id);
}