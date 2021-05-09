package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StarchBorrowPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(资产解出退还商品关系表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchBorrowPutService extends IService<StarchBorrowPut> {

    List<StarchBorrowPut> queryCatList(StarchBorrowPut jdyFlowCatalog);

    boolean updateStarchMaintainRegister(StarchBorrowPut warehouseDepository);

    boolean addJdyFlowCatalog(StarchBorrowPut departmentSuggestDto);
}