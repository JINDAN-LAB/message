package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WaimaoAreaDao;
import com.jindan.jdy.common.pojo.WaimaoArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoAreaServiceImpl  extends ServiceImpl<WaimaoAreaDao,WaimaoArea> implements WaimaoAreaService  {

    @Autowired
    WaimaoAreaDao waimaoAreaDao;

    @Override
    public List<WaimaoArea> seletelist(WaimaoArea jdyRole) {
        QueryWrapper<WaimaoArea> queryWrapper =new QueryWrapper();
        if( jdyRole.getSeid() !=null &&  !jdyRole.getSeid().equals(" ") ){
            queryWrapper.eq("id",jdyRole.getSeid());
        }
        if( jdyRole.getRegions() !=null && !jdyRole.getRegions().isEmpty() ){
            queryWrapper.like("regions",jdyRole.getRegions());
        }
        if( jdyRole.getDestinations() !=null && !jdyRole.getDestinations().isEmpty() ){
            queryWrapper.like("destinations",jdyRole.getDestinations());
        }
        if( jdyRole.getProducts() !=null && !jdyRole.getProducts().isEmpty() ){
            queryWrapper.like("products",jdyRole.getProducts());
        }
        if( jdyRole.getInsertTime() !=null  ){
            queryWrapper.like("insert_time",jdyRole.getInsertTime());
        }
        if( jdyRole.getUpdateTime() !=null ){
            queryWrapper.like("update_time",jdyRole.getUpdateTime());
        }
        return waimaoAreaDao.selectList(queryWrapper);
    }

    @Override
    public List<WaimaoArea> seleteQuyulist() {
        return waimaoAreaDao.selectList(null);
    }
}