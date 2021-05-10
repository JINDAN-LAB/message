package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.StockDepository;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(仓库类别数据访问层)
 *
 * @version: V 1.0
 * @author: xbdyilin
 * 
 */
@Mapper
public interface StockDepositoryMapper extends BaseMapper<StockDepository> {

    List<StockDepository> stockDepositoryList();

}
