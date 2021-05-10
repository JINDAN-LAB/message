package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.StarchProvokePutDao;
import com.jindan.jdy.common.pojo.StarchProvokePut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(资产调拨商品关系表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchProvokePutServiceImpl  extends ServiceImpl<StarchProvokePutDao,StarchProvokePut> implements StarchProvokePutService  {

    @Autowired
    StarchProvokePutDao starchProvokePutDao;

    @Override
    public List<StarchProvokePut> queryCatList(StarchProvokePut jdyRole) {

        QueryWrapper<StarchProvokePut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty()){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getProvokeId() !=null && !jdyRole.getProvokeId().isEmpty() ){
            queryWrapper.like("provoke_id",jdyRole.getProvokeId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchProvokePutDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchProvokePut warehouseDepository) {
        return starchProvokePutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchProvokePut departmentSuggestDto) {
        return starchProvokePutDao.insert(departmentSuggestDto) > 0;
    }


}