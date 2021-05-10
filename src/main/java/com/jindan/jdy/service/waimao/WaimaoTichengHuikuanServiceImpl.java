package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.WaimaoTichengHuikuanDao;
import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;
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
public class WaimaoTichengHuikuanServiceImpl  extends ServiceImpl<WaimaoTichengHuikuanDao,WaimaoTichengHuikuan> implements WaimaoTichengHuikuanService  {

    @Autowired
    WaimaoTichengHuikuanDao waimaoTichengHuikuanDao;


    @Override
    public List<WaimaoTichengHuikuan> seletelist(WaimaoTichengHuikuan jdyRole) {

        QueryWrapper<WaimaoTichengHuikuan> queryWrapper =new QueryWrapper<>();
        if( jdyRole.getFapiaohao() !=null && !jdyRole.getFapiaohao().isEmpty() ){
            queryWrapper.like("fapiaohao",jdyRole.getFapiaohao());
        }
        if( jdyRole.getHuikuanriqi() !=null && !jdyRole.getHuikuanriqi().isEmpty() ){
            queryWrapper.like("huikuanriqi",jdyRole.getHuikuanriqi());
        }
        if( jdyRole.getHuikuanjine() !=null && !jdyRole.getHuikuanjine().isEmpty() ){
            queryWrapper.like("huikuanjine",jdyRole.getHuikuanjine());
        }
        if( jdyRole.getJiehuiyinhang() !=null && !jdyRole.getJiehuiyinhang().isEmpty() ){
            queryWrapper.like("jiehuiyinhang",jdyRole.getJiehuiyinhang());
        }
        if( jdyRole.getZhoubie() !=null && !jdyRole.getZhoubie().isEmpty() ){
            queryWrapper.like("zhoubie",jdyRole.getZhoubie());
        }

        if( jdyRole.getShijishiyong() !=null && !jdyRole.getShijishiyong().isEmpty() ){
            queryWrapper.like("shijishiyong",jdyRole.getShijishiyong());
        }
        if( jdyRole.getYuliu3() !=null && !jdyRole.getYuliu3().isEmpty() ){
            queryWrapper.like("yuliu3",jdyRole.getYuliu3());
        }
        queryWrapper.gt("jine","0");
        return waimaoTichengHuikuanDao.selectList(queryWrapper);
    }

    @Override
    public List<WaimaoTichengHuikuan> seleteYuebiaoHuikuan() {
        return waimaoTichengHuikuanDao.seleteYuebiaoHuikuan();
    }
}