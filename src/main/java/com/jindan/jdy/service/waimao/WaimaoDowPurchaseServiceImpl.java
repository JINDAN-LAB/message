package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.WaimaoDowBankExpendDto;
import com.jindan.jdy.mapper.WaimaoDowPurchaseMapper;
import com.jindan.jdy.common.pojo.WaimaoDowPurchase;
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
public class WaimaoDowPurchaseServiceImpl  extends ServiceImpl<WaimaoDowPurchaseMapper,WaimaoDowPurchase> implements WaimaoDowPurchaseService  {

    @Autowired
    WaimaoDowPurchaseMapper waimaoAreaDao;

    @Override
    public List<WaimaoDowPurchase> seletelist(WaimaoDowPurchase jdyRole) {
        QueryWrapper<WaimaoDowPurchase> queryWrapper =new QueryWrapper();
        if( jdyRole.getCid() !=null &&  !jdyRole.getCid().equals(" ") ){
            queryWrapper.eq("cid",jdyRole.getCid());
        }
        if( jdyRole.getSuppliers() !=null && !jdyRole.getSuppliers().isEmpty() ){
            queryWrapper.like("suppliers",jdyRole.getSuppliers());
        }
        if( jdyRole.getMaterialName() !=null && !jdyRole.getMaterialName().isEmpty() ){
            queryWrapper.like("material_name",jdyRole.getMaterialName());
        }
        if( jdyRole.getUnits() !=null && !jdyRole.getUnits().isEmpty() ){
            queryWrapper.like("units",jdyRole.getUnits());
        }
        if( jdyRole.getNums() !=null &&  !jdyRole.getNums().equals(" ") ){
            queryWrapper.eq("nums",jdyRole.getNums());
        }
        if( jdyRole.getJine() !=null && !jdyRole.getJine().isEmpty() ){
            queryWrapper.like("jine",jdyRole.getJine());
        }
        if( jdyRole.getTax() !=null && !jdyRole.getTax().isEmpty() ){
            queryWrapper.like("tax",jdyRole.getTax());
        }
        if( jdyRole.getRemarks() !=null && !jdyRole.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",jdyRole.getRemarks());
        }
        if( jdyRole.getJiahsuiheji() !=null &&  !jdyRole.getJiahsuiheji().equals(" ") ){
            queryWrapper.eq("jiahsuiheji",jdyRole.getJiahsuiheji());
        }
        if( jdyRole.getChetonghao() !=null && !jdyRole.getChetonghao().isEmpty() ){
            queryWrapper.like("chetonghao",jdyRole.getChetonghao());
        }
        return waimaoAreaDao.selectList(queryWrapper);
    }

    @Override
    public List<WaimaoDowBankExpendDto> getDetails(String id) {
        return waimaoAreaDao.getDetails(id);
    }

    @Override
    public List<WaimaoDowBankExpendDto> getDetailsAll() {
        return waimaoAreaDao.getDetailsAll();
    }

    /*外贸道氏采购信息导出*/
    @Override
    public List<WaimaoDowBankExpendDto> getWaimaoDowPurchaseDetailExportExcel() {
        return waimaoAreaDao.getWaimaoDowPurchaseDetailExportExcel();
    }
}