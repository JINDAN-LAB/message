package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchAlterationPut;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;

import java.util.List;

/**   
 * @Description:TODO(维保信息变更表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchAlterationPutService extends IService<StarchAlterationPut> {

    List<StarchAlterationPut> queryCatList(StarchAlterationPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchAlterationPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchAlterationPut departmentSuggestDto);
}