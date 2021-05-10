package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.StarchRegisterPutMapper;
import com.jindan.jdy.common.pojo.StarchRegisterPut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(维修登记商品关系表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchRegisterPutServiceImpl  extends ServiceImpl<StarchRegisterPutMapper,StarchRegisterPut> implements StarchRegisterPutService  {

    @Autowired
    StarchRegisterPutMapper starchRegisterPutDao;

    @Override
    public List<StarchRegisterPut> queryCatList(StarchRegisterPut jdyRole) {
        QueryWrapper<StarchRegisterPut> queryWrapper = new QueryWrapper<>();
        if( jdyRole.getId() !=null &&  !jdyRole.getId().isEmpty()){
            queryWrapper.eq("id",jdyRole.getId());
        }
        if(jdyRole.getRegisterId() !=null && !jdyRole.getRegisterId().isEmpty() ){
            queryWrapper.like("register_id",jdyRole.getRegisterId());
        }
        if( jdyRole.getPutId() !=null && !jdyRole.getPutId().isEmpty() ){
            queryWrapper.like("put_id",jdyRole.getPutId());
        }
        return starchRegisterPutDao.selectList(queryWrapper);
    }

    @Override
    public boolean updateStarchMaintainRegister(StarchRegisterPut warehouseDepository) {
        return starchRegisterPutDao.updateById(warehouseDepository) > 0;
    }

    @Override
    public boolean addJdyFlowCatalog(StarchRegisterPut departmentSuggestDto) {
        return starchRegisterPutDao.insert(departmentSuggestDto) > 0;
    }


}