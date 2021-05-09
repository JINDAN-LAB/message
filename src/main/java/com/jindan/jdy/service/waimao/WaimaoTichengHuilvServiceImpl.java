package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WaimaoTichengHuilvDao;
import com.jindan.jdy.common.pojo.WaimaoTichengHuilv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoTichengHuilvServiceImpl  extends ServiceImpl<WaimaoTichengHuilvDao,WaimaoTichengHuilv> implements WaimaoTichengHuilvService  {

    @Autowired
    WaimaoTichengHuilvDao waimaoTichengHuilvDao;

    @Override
    public List<WaimaoTichengHuilv> seletelist(WaimaoTichengHuilv jdyRole) {

        QueryWrapper<WaimaoTichengHuilv> queryWrapper =new QueryWrapper<>();
        if( jdyRole.getBz() !=null && !jdyRole.getBz().isEmpty() ){
            queryWrapper.like("bz",jdyRole.getBz());
        }
        if( jdyRole.getBili() !=null &&  jdyRole.getBili() > 0 ){
            queryWrapper.like("bili",jdyRole.getBili());
        }
        return waimaoTichengHuilvDao.selectList(queryWrapper);
    }

}