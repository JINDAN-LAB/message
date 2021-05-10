package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.WaimaoTargetAccomplishDao;
import com.jindan.jdy.common.pojo.WaimaoTargetAccomplish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(外贸目标完成服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoTargetAccomplishServiceImpl  extends ServiceImpl<WaimaoTargetAccomplishDao,WaimaoTargetAccomplish> implements WaimaoTargetAccomplishService  {

    @Autowired
    WaimaoTargetAccomplishDao waimaoFahuoDao;

    @Override
    public List<WaimaoTargetAccomplish> seletelist(WaimaoTargetAccomplish waimaoHuikuan) {
        QueryWrapper<WaimaoTargetAccomplish> queryWrapper = new QueryWrapper<>();

        if( waimaoHuikuan.getYiyue() !=null && !waimaoHuikuan.getYiyue().isEmpty() ){
            queryWrapper.like("yiyue",waimaoHuikuan.getYiyue());
        }
        if( waimaoHuikuan.getEryue() !=null && !waimaoHuikuan.getEryue().isEmpty() ){
            queryWrapper.like("eryue",waimaoHuikuan.getEryue());
        }
        if( waimaoHuikuan.getSanyue() !=null && !waimaoHuikuan.getSanyue().isEmpty() ){
            queryWrapper.like("sanyue",waimaoHuikuan.getSanyue());
        }
        if( waimaoHuikuan.getSiyue() !=null && !waimaoHuikuan.getSiyue().isEmpty() ){
            queryWrapper.like("siyue",waimaoHuikuan.getSiyue());
        }
        if( waimaoHuikuan.getWuyue() !=null && !waimaoHuikuan.getWuyue().isEmpty() ){
            queryWrapper.like("wuyue",waimaoHuikuan.getWuyue());
        }
        if( waimaoHuikuan.getLiuyue() !=null && !waimaoHuikuan.getLiuyue().isEmpty() ){
            queryWrapper.like("liuyue",waimaoHuikuan.getLiuyue());
        }
        if( waimaoHuikuan.getQiyue() !=null && !waimaoHuikuan.getQiyue().isEmpty() ){
            queryWrapper.like("qiyue",waimaoHuikuan.getQiyue());
        }
        if( waimaoHuikuan.getBayue() !=null && !waimaoHuikuan.getBayue().isEmpty() ){
            queryWrapper.like("bayue",waimaoHuikuan.getBayue());
        }
        if( waimaoHuikuan.getJiuyue() !=null && !waimaoHuikuan.getJiuyue().isEmpty() ){
            queryWrapper.like("jiuyue",waimaoHuikuan.getJiuyue());
        }
        if( waimaoHuikuan.getShiyue() !=null && !waimaoHuikuan.getShiyue().isEmpty() ){
            queryWrapper.like("shiyue",waimaoHuikuan.getShiyue());
        }
        if( waimaoHuikuan.getShiyiyue() !=null && !waimaoHuikuan.getShiyiyue().isEmpty() ){
            queryWrapper.like("shiyiyue",waimaoHuikuan.getShiyiyue());
        }
        if( waimaoHuikuan.getShieryue() !=null && !waimaoHuikuan.getShieryue().isEmpty() ){
            queryWrapper.like("shieryue",waimaoHuikuan.getShieryue());
        }
        return waimaoFahuoDao.selectList(queryWrapper);
    }

    @Override
    public List<WaimaoTargetAccomplish> seletehuilvlist() {
        return waimaoFahuoDao.selectList(null);
    }


}