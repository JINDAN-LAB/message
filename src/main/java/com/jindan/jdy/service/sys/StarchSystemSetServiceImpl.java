package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.StarchSystemSetDao;
import com.jindan.jdy.common.pojo.StarchSystemSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(系统-设置表服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class StarchSystemSetServiceImpl  extends ServiceImpl<StarchSystemSetDao,StarchSystemSet> implements StarchSystemSetService  {

    @Autowired
    StarchSystemSetDao starchSystemSetDao;

    @Override
    public List<StarchSystemSet> seletelist(StarchSystemSet jdySupplier) {

        QueryWrapper<StarchSystemSet> queryWrapper =new QueryWrapper<>();
        if(jdySupplier.getSid() !=null &&  !jdySupplier.getSid().isEmpty() ){
            queryWrapper.eq("sid",jdySupplier.getSid());
        }
        if(jdySupplier.getStatus() !=null && !jdySupplier.getStatus().isEmpty() ){
            queryWrapper.like("status",jdySupplier.getStatus());
        }
        if(jdySupplier.getNames() !=null && !jdySupplier.getNames().isEmpty() ){
            queryWrapper.like("names",jdySupplier.getNames());
        }
        return starchSystemSetDao.selectList(queryWrapper);
    }

}