package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.StarchCheckPutDao;
import com.jindan.jdy.common.pojo.StarchCheckPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产盘点商品关系表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchCheckPutServiceImpl  extends ServiceImpl<StarchCheckPutDao,StarchCheckPut> implements StarchCheckPutService  {

    @Autowired
    StarchCheckPutDao starchCheckPutDao;

    @Override
    public List<StarchCheckPut> queryCatList(StarchCheckPut jdyRole) {
        QueryWrapper<StarchCheckPut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty() ){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getCheckId() !=null && !jdyRole.getCheckId().isEmpty() ){
            queryWrapper.like("check_id",jdyRole.getCheckId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchCheckPutDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchCheckPut warehouseDepository) {
        return starchCheckPutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchCheckPut departmentSuggestDto) {
        return starchCheckPutDao.insert(departmentSuggestDto) > 0;
    }


}