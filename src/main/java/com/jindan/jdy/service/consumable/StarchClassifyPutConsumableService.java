package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchClassifyPutConsumable;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(耗材资产分类服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchClassifyPutConsumableService extends IService<StarchClassifyPutConsumable> {

    boolean updateStarchMaintainRegister(StarchClassifyPutConsumable warehouseDepository);

    boolean addJdyFlowCatalog(StarchClassifyPutConsumable departmggestDto);

    boolean removeDetailsById(String id);

    List<StarchClassifyPutConsumableDto> queryRelevanceCatList(StarchClassifyPutConsumable jdyFlatalog);
}