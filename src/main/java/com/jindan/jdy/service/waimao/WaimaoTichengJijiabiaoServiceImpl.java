package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.WaimaoTichengJijiabiaoDao;
import com.jindan.jdy.common.pojo.WaimaoTichengJijiabiao;
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
public class WaimaoTichengJijiabiaoServiceImpl  extends ServiceImpl<WaimaoTichengJijiabiaoDao,WaimaoTichengJijiabiao> implements WaimaoTichengJijiabiaoService  {

    @Autowired
    WaimaoTichengJijiabiaoDao waimaoTichengJijiabiaoDao;

    @Override
    public List<WaimaoTichengJijiabiao> seletelist(WaimaoTichengJijiabiao jdyRole) {
        QueryWrapper<WaimaoTichengJijiabiao> queryWrapper =new QueryWrapper<>();
        if( jdyRole.getTitlename() !=null && !jdyRole.getTitlename().isEmpty() ){
            queryWrapper.like("titlename",jdyRole.getTitlename());
        }
        if( jdyRole.getFifth() !=null && !jdyRole.getFifth().isEmpty()){
            queryWrapper.like("fifth",jdyRole.getFifth());
        }
        if( jdyRole.getFourthly() !=null &&  !jdyRole.getFourthly().isEmpty() ){
            queryWrapper.like("fourthly",jdyRole.getFourthly());
        }
        if( jdyRole.getThirdly() !=null &&  !jdyRole.getThirdly().isEmpty()){
            queryWrapper.like("thirdly",jdyRole.getThirdly());
        }
        if( jdyRole.getSecond() !=null &&  !jdyRole.getSecond().isEmpty() ){
            queryWrapper.like("second",jdyRole.getSecond());
        }
        if( jdyRole.getFirst() !=null &&  !jdyRole.getFirst().isEmpty()){
            queryWrapper.like("first",jdyRole.getFirst());
        }
        return waimaoTichengJijiabiaoDao.selectList(queryWrapper);
    }
}