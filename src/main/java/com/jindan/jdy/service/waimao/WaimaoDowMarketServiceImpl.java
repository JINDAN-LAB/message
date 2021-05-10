package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.WaimaoDowMarketDao;
import com.jindan.jdy.common.pojo.WaimaoDowMarket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(外贸道氏服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class WaimaoDowMarketServiceImpl  extends ServiceImpl<WaimaoDowMarketDao,WaimaoDowMarket> implements WaimaoDowMarketService  {

    @Autowired
    WaimaoDowMarketDao waimaoAreaDao;

    @Override
    public List<WaimaoDowMarket> seletelist(WaimaoDowMarket jdyRole) {
        QueryWrapper<WaimaoDowMarket> queryWrapper =new QueryWrapper();
        if( jdyRole.getXid() !=null &&  !jdyRole.getXid().equals(" ") ){
            queryWrapper.eq("xid",jdyRole.getXid());
        }
        if( jdyRole.getShouhuokehu() !=null && !jdyRole.getShouhuokehu().isEmpty() ){
            queryWrapper.like("shouhuokehu",jdyRole.getShouhuokehu());
        }
        if( jdyRole.getMaterialNames() !=null && !jdyRole.getMaterialNames().isEmpty() ){
            queryWrapper.like("material_names",jdyRole.getMaterialNames());
        }
        if( jdyRole.getNums() !=null && !jdyRole.getNums().isEmpty() ){
            queryWrapper.like("nums",jdyRole.getNums());
        }
        if( jdyRole.getUnitx() !=null &&  !jdyRole.getUnitx().equals(" ") ){
            queryWrapper.eq("unitx",jdyRole.getUnitx());
        }
        if( jdyRole.getXtax() !=null && !jdyRole.getXtax().isEmpty() ){
            queryWrapper.like("xtax",jdyRole.getXtax());
        }
        if( jdyRole.getDrawback() !=null && !jdyRole.getDrawback().isEmpty() ){
            queryWrapper.like("drawback",jdyRole.getDrawback());
        }
        if( jdyRole.getRefundRates() !=null && !jdyRole.getRefundRates().isEmpty() ){
            queryWrapper.like("refund_rates",jdyRole.getRefundRates());
        }
        if( jdyRole.getRemarksx() !=null &&  !jdyRole.getRemarksx().equals(" ") ){
            queryWrapper.eq("remarksx",jdyRole.getRemarksx());
        }
        if( jdyRole.getXhetonghao() !=null && !jdyRole.getXhetonghao().isEmpty() ){
            queryWrapper.like("xhetonghao",jdyRole.getXhetonghao());
        }
        return waimaoAreaDao.selectList(queryWrapper);
    }
}