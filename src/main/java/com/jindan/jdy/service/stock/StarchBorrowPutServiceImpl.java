package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.StarchBorrowPutDao;
import com.jindan.jdy.common.pojo.StarchBorrowPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产解出退还商品关系表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchBorrowPutServiceImpl  extends ServiceImpl<StarchBorrowPutDao,StarchBorrowPut> implements StarchBorrowPutService  {

    @Autowired
    StarchBorrowPutDao starchBorrowPutDao;


    @Override
    public List<StarchBorrowPut> queryCatList(StarchBorrowPut jdyRole) {
        QueryWrapper<StarchBorrowPut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty() ){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getBorrowId() !=null && !jdyRole.getBorrowId().isEmpty() ){
            queryWrapper.like("borrow_id",jdyRole.getBorrowId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchBorrowPutDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchBorrowPut warehouseDepository) {
        return starchBorrowPutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchBorrowPut departmentSuggestDto) {
        return false;
    }
}