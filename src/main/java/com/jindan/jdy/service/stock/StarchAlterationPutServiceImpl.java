package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.StarchAccessPut;
import com.jindan.jdy.common.pojo.StarchAlterationPut;
import com.jindan.jdy.common.mapper.StarchAlterationPutDao;
import com.jindan.jdy.common.pojo.StarchMaintainRegister;
import com.jindan.jdy.service.stock.StarchAlterationPutService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(维保信息变更表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchAlterationPutServiceImpl  extends ServiceImpl<StarchAlterationPutDao,StarchAlterationPut> implements StarchAlterationPutService  {

    @Autowired
    StarchAlterationPutDao starchAlterationPutDao;


    @Override
    public List<StarchAlterationPut> queryCatList(StarchAlterationPut jdyRole) {

        QueryWrapper<StarchAlterationPut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty() ){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getAlterationId() !=null && !jdyRole.getAlterationId().isEmpty() ){
            queryWrapper.like("alteration_id",jdyRole.getAlterationId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchAlterationPutDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchAlterationPut warehouseDepository) {
        return starchAlterationPutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchAlterationPut departmentSuggestDto) {
        return starchAlterationPutDao.insert(departmentSuggestDto) > 0;
    }


}