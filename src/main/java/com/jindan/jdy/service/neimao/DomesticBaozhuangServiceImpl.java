package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.DomesticBaozhuang;
import com.jindan.jdy.common.mapper.DomesticBaozhuangDao;
import com.jindan.jdy.service.neimao.DomesticBaozhuangService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(内贸提成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class DomesticBaozhuangServiceImpl  extends ServiceImpl<DomesticBaozhuangDao,DomesticBaozhuang> implements DomesticBaozhuangService  {


    @Autowired
    DomesticBaozhuangDao domesticBaozhuangDao;


    @Override
    public List<DomesticBaozhuang> seletelist(DomesticBaozhuang domesticBaozhuang) {

        QueryWrapper<DomesticBaozhuang> queryWrapper =new QueryWrapper<>();
        if( domesticBaozhuang.getBaozhuangwu() !=null && !domesticBaozhuang.getBaozhuangwu().isEmpty()  ){
            queryWrapper.eq("baozhuangwu",domesticBaozhuang.getBaozhuangwu());
        }
        if( domesticBaozhuang.getBuhanbaozhuang() !=null && domesticBaozhuang.getBuhanbaozhuang() != null ){
            queryWrapper.eq("buhanbaozhuang",domesticBaozhuang.getBuhanbaozhuang());
        }
        queryWrapper.orderByAsc("baozhuangwu");
        return domesticBaozhuangDao.selectList(queryWrapper);
    }
}