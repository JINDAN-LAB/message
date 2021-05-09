package com.jindan.jdy.service.stock;

import com.jindan.jdy.common.pojo.StockDepository;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(仓库类别服务层)
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
public interface StockDepositoryService extends IService<StockDepository> {

    List<StockDepository> stockDepositoryList();

    void saveStockDepository(String name);

    void updateStockDepository(String name, int id);

    void delectStockDepository(int id, int isDelect);
}