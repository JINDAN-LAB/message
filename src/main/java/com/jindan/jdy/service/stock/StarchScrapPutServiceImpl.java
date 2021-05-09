package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.StarchRegisterPut;
import com.jindan.jdy.common.pojo.StarchScrapPut;
import com.jindan.jdy.common.mapper.StarchScrapPutDao;
import com.jindan.jdy.service.stock.StarchScrapPutService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(清理报废商品关系表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchScrapPutServiceImpl  extends ServiceImpl<StarchScrapPutDao,StarchScrapPut> implements StarchScrapPutService  {

    @Autowired
    StarchScrapPutDao starchScrapPutDao;

    @Override
    public List<StarchScrapPut> queryCatList(StarchScrapPut jdyRole) {

        QueryWrapper<StarchScrapPut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty()){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if( jdyRole.getScrapId() !=null && !jdyRole.getScrapId().isEmpty() ){
            queryWrapper.like("scrap_id",jdyRole.getScrapId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchScrapPutDao.selectList(queryWrapper);
    }


    @Override
    public boolean updateStarchMaintainRegister(StarchScrapPut warehouseDepository) {
        return starchScrapPutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchScrapPut departmentSuggestDto) {
        return starchScrapPutDao.insert(departmentSuggestDto) > 0;
    }


}