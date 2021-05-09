package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.WaimaoDowBankIncomeDao;
import com.jindan.jdy.common.pojo.WaimaoDowBankIncome;
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
public class WaimaoDowBankIncomeServiceImpl  extends ServiceImpl<WaimaoDowBankIncomeDao,WaimaoDowBankIncome> implements WaimaoDowBankIncomeService  {

    @Autowired
    WaimaoDowBankIncomeDao waimaoAreaDao;

    @Override
    public List<WaimaoDowBankIncome> seletelist(WaimaoDowBankIncome jdyRole) {
        QueryWrapper<WaimaoDowBankIncome> queryWrapper =new QueryWrapper();
        if( jdyRole.getYsid() !=null &&  !jdyRole.getYsid().equals(" ") ){
            queryWrapper.eq("ysid",jdyRole.getYsid());
        }
        if( jdyRole.getShouTime() !=null && !jdyRole.getShouTime().isEmpty() ){
            queryWrapper.like("shou_time",jdyRole.getShouTime());
        }
        if( jdyRole.getShouRen() !=null && !jdyRole.getShouRen().isEmpty() ){
            queryWrapper.like("shou_ren",jdyRole.getShouRen());
        }
        if( jdyRole.getShouJine() !=null && !jdyRole.getShouJine().isEmpty() ){
            queryWrapper.like("shou_jine",jdyRole.getShouJine());
        }
        if( jdyRole.getShouNature() !=null &&  !jdyRole.getShouNature().equals(" ") ){
            queryWrapper.eq("shou_nature",jdyRole.getShouNature());
        }
        if( jdyRole.getHuoTuishui() !=null && !jdyRole.getHuoTuishui().isEmpty() ){
            queryWrapper.like("huo_tuishui",jdyRole.getHuoTuishui());
        }
        if( jdyRole.getContractFapiao() !=null && !jdyRole.getContractFapiao().isEmpty() ){
            queryWrapper.like("contract_fapiao",jdyRole.getContractFapiao());
        }
        if( jdyRole.getYsremarks() !=null && !jdyRole.getYsremarks().isEmpty() ){
            queryWrapper.like("ysremarks",jdyRole.getYsremarks());
        }
        if( jdyRole.getShouJine2() !=null &&  !jdyRole.getShouJine2().equals(" ") ){
            queryWrapper.eq("shou_jine2",jdyRole.getShouJine2());
        }
        return waimaoAreaDao.selectList(queryWrapper);
    }

}