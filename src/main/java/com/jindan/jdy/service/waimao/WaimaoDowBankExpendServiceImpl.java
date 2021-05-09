package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WaimaoDowBankExpendDao;
import com.jindan.jdy.common.pojo.WaimaoDowBankExpend;
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
public class WaimaoDowBankExpendServiceImpl  extends ServiceImpl<WaimaoDowBankExpendDao,WaimaoDowBankExpend> implements WaimaoDowBankExpendService  {

    @Autowired
    WaimaoDowBankExpendDao waimaoAreaDao;

    @Override
    public List<WaimaoDowBankExpend> seletelist(WaimaoDowBankExpend jdyRole) {
        QueryWrapper<WaimaoDowBankExpend> queryWrapper =new QueryWrapper();
        if( jdyRole.getYid() !=null &&  !jdyRole.getYid().equals(" ") ){
            queryWrapper.eq("yid",jdyRole.getYid());
        }
        if( jdyRole.getPaymentTime() !=null && !jdyRole.getPaymentTime().isEmpty() ){
            queryWrapper.like("payment_time",jdyRole.getPaymentTime());
        }
        if( jdyRole.getShoukuanren() !=null && !jdyRole.getShoukuanren().isEmpty() ){
            queryWrapper.like("shoukuanren",jdyRole.getShoukuanren());
        }
        if( jdyRole.getYjine() !=null && !jdyRole.getYjine().isEmpty() ){
            queryWrapper.like("yjine",jdyRole.getYjine());
        }
        if( jdyRole.getYnature() !=null &&  !jdyRole.getYnature().equals(" ") ){
            queryWrapper.eq("ynature",jdyRole.getYnature());
        }
        if( jdyRole.getPaymentForGoods() !=null && !jdyRole.getPaymentForGoods().isEmpty() ){
            queryWrapper.like("payment_for_goods",jdyRole.getPaymentForGoods());
        }
        if( jdyRole.getContractNumber() !=null && !jdyRole.getContractNumber().isEmpty() ){
            queryWrapper.like("contract_number",jdyRole.getContractNumber());
        }
        if( jdyRole.getYremarks() !=null && !jdyRole.getYremarks().isEmpty() ){
            queryWrapper.like("yremarks",jdyRole.getYremarks());
        }
        if( jdyRole.getYjine2() !=null &&  !jdyRole.getYjine2().equals(" ") ){
            queryWrapper.eq("yjine2",jdyRole.getYjine2());
        }
        return waimaoAreaDao.selectList(queryWrapper);
    }


}