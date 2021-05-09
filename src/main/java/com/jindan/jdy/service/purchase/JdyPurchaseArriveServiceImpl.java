package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.JdyPurchaseArriveDao;
import com.jindan.jdy.common.pojo.JdyPurchaseArrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(到货单表头信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyPurchaseArriveServiceImpl  extends ServiceImpl<JdyPurchaseArriveDao,JdyPurchaseArrive> implements JdyPurchaseArriveService  {

    @Autowired
    JdyPurchaseArriveDao jdyPurchaseArriveDao;


    @Override
    public List<JdyPurchaseArrive> seletelist(JdyPurchaseArrive jdyPurchaseDto) {

        QueryWrapper<JdyPurchaseArrive> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getPurchaseId() !=null && !jdyPurchaseDto.getPurchaseId().isEmpty()){
            queryWrapper.eq("purchase_id",jdyPurchaseDto.getPurchaseId());
        }
        if(jdyPurchaseDto.getInvoices() !=null && !jdyPurchaseDto.getInvoices().isEmpty()){
            queryWrapper.eq("invoices",jdyPurchaseDto.getInvoices());
        }
        if(jdyPurchaseDto.getSupplierId() !=null && !jdyPurchaseDto.getSupplierId().isEmpty()){
            queryWrapper.eq("supplier_id",jdyPurchaseDto.getSupplierId());
        }
        if(jdyPurchaseDto.getOperator() !=null && !jdyPurchaseDto.getOperator().isEmpty()){
            queryWrapper.eq("operator",jdyPurchaseDto.getOperator());
        }
        if(jdyPurchaseDto.getPurchaseType() !=null && !jdyPurchaseDto.getPurchaseType().isEmpty()){
            queryWrapper.eq("purchase_type",jdyPurchaseDto.getPurchaseType());
        }
        if(jdyPurchaseDto.getStatics() !=null && !jdyPurchaseDto.getStatics().isEmpty()){
            queryWrapper.eq("statics",jdyPurchaseDto.getStatics());
        }
        if(jdyPurchaseDto.getPurchaseTime() !=null && !jdyPurchaseDto.getPurchaseTime().isEmpty()){
                queryWrapper.eq("purchase_time",jdyPurchaseDto.getPurchaseTime());
        }
        if(jdyPurchaseDto.getDepartments() !=null && !jdyPurchaseDto.getDepartments().isEmpty()){
            queryWrapper.eq("departments",jdyPurchaseDto.getDepartments());
        }
        if(jdyPurchaseDto.getStatus() !=null && !jdyPurchaseDto.getStatus().isEmpty()){
            queryWrapper.eq("status",jdyPurchaseDto.getStatus());
        }
        if(jdyPurchaseDto.getTransports() !=null && !jdyPurchaseDto.getTransports().isEmpty()){
            queryWrapper.eq("transports",jdyPurchaseDto.getTransports());
        }
        if(jdyPurchaseDto.getPuuids() !=null && !jdyPurchaseDto.getPuuids().isEmpty()){
            queryWrapper.eq("puuids",jdyPurchaseDto.getPuuids());
        }
        if(jdyPurchaseDto.getAllMoney() !=null && !jdyPurchaseDto.getAllMoney().isEmpty()){
            queryWrapper.eq("all_money",jdyPurchaseDto.getAllMoney());
        }
        if(jdyPurchaseDto.getParentId() !=null && !jdyPurchaseDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPurchaseDto.getParentId());
        }
        List<JdyPurchaseArrive> jdyPurchases = jdyPurchaseArriveDao.selectList(queryWrapper);
        return jdyPurchases;
    }

    @Override
    public boolean insertSave(JdyPurchaseArrive jdyPurchaseDto) {
        return jdyPurchaseArriveDao.insert(jdyPurchaseDto) > 0;
    }


}