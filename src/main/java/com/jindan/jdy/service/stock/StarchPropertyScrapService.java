package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchPropertyScrapDto;
import com.jindan.jdy.common.pojo.StarchPropertyScrap;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(清理报废服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchPropertyScrapService extends IService<StarchPropertyScrap> {

    Page<StarchPropertyScrap> queryCatList(StarchPropertyScrapDto jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchPropertyScrapDto warehouseDepository);

    boolean addJdyFlowCatalog(StarchPropertyScrapDto departmentSuggestDto);

    PageInfo<StarchPropertyScrapDto> queryRelevanceCatList(StarchPropertyScrapDto jdyFlowCatalog);

    boolean removeDetailsById(String id);
}