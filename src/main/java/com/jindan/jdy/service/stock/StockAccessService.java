package com.jindan.jdy.service.stock;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.pojo.StockAccess;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.vo.StockAccessListVO;
import com.jindan.jdy.common.vo.StockAccessSaveVO;

/**   
 * @Description:TODO(进入库服务层)
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
public interface StockAccessService extends IService<StockAccess> {

    PageInfo<StockAccess> StockAccessList(StockAccessListVO stockAccessListVO);

    void saveStockAccess(StockAccessSaveVO stockAccessSaveVO) throws InterruptedException;
}