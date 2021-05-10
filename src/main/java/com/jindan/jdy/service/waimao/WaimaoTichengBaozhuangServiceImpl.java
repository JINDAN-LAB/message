package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.WaimaoTichengBaozhuangDao;
import com.jindan.jdy.common.pojo.WaimaoTichengBaozhuang;
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
public class WaimaoTichengBaozhuangServiceImpl  extends ServiceImpl<WaimaoTichengBaozhuangDao,WaimaoTichengBaozhuang> implements WaimaoTichengBaozhuangService  {

    @Autowired
    WaimaoTichengBaozhuangDao waimaoTichengBaozhuangDao;

    @Override
    public List<WaimaoTichengBaozhuang> seletelist(WaimaoTichengBaozhuang jdyRole) {

        QueryWrapper<WaimaoTichengBaozhuang>  queryWrapper =new QueryWrapper<>();
        if( jdyRole.getBzw() !=null && !jdyRole.getBzw().isEmpty() ){
            queryWrapper.like("bzw",jdyRole.getBzw());
        }
        if( jdyRole.getBaozhuangjia() !=null && jdyRole.getBaozhuangjia() >0  ){
            queryWrapper.like("baozhuangjia",jdyRole.getBaozhuangjia());
        }
        return waimaoTichengBaozhuangDao.selectList(queryWrapper);
    }


}