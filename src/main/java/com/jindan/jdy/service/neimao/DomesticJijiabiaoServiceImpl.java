package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.DomesticJijiabiao;
import com.jindan.jdy.common.mapper.DomesticJijiabiaoDao;
import com.jindan.jdy.service.neimao.DomesticJijiabiaoService;

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
public class DomesticJijiabiaoServiceImpl  extends ServiceImpl<DomesticJijiabiaoDao,DomesticJijiabiao> implements DomesticJijiabiaoService  {

    @Autowired
    DomesticJijiabiaoDao domesticJijiabiaoDao;

    @Override
    public List<DomesticJijiabiao> seletelist(DomesticJijiabiao domesticJijiabiao) {

        QueryWrapper<DomesticJijiabiao> queryWrapper =new QueryWrapper<>();
        if( domesticJijiabiao.getName() !=null && !domesticJijiabiao.getName().isEmpty()  ){
            queryWrapper.eq("name",domesticJijiabiao.getName());
        }
        if( domesticJijiabiao.getDiyidang() !=null && domesticJijiabiao.getDiyidang() != null ){
            queryWrapper.eq("diyidang",domesticJijiabiao.getDiyidang());
        }
        if( domesticJijiabiao.getDierdang() !=null && domesticJijiabiao.getDierdang() != null ){
            queryWrapper.eq("dierdang",domesticJijiabiao.getDierdang());
        }
        if( domesticJijiabiao.getDisandang() !=null && domesticJijiabiao.getDisandang() != null ){
            queryWrapper.eq("disandang",domesticJijiabiao.getDisandang());
        }
        if( domesticJijiabiao.getDisidang() !=null && domesticJijiabiao.getDisidang() != null ){
            queryWrapper.eq("disidang",domesticJijiabiao.getDisidang());
        }
        if( domesticJijiabiao.getDiwudang() !=null && domesticJijiabiao.getDiwudang() != null ){
            queryWrapper.eq("diwudang",domesticJijiabiao.getDiwudang());
        }
        queryWrapper.orderByAsc("name");
        return domesticJijiabiaoDao.selectList(queryWrapper);
    }

}