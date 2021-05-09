package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.StarchCheckPut;

import java.util.List;

/**   
 * @Description:TODO(资产盘点商品关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchCheckPutService extends IService<StarchCheckPut> {

    List<StarchCheckPut> queryCatList(StarchCheckPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchCheckPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchCheckPut departmentSuggestDto);
}