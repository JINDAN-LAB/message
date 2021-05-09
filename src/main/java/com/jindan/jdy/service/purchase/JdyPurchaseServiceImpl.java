package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.pojo.DepartmentSubfacility;
import com.jindan.jdy.common.pojo.JdyPurchase;
import com.jindan.jdy.common.mapper.JdyPurchaseMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyPurchaseServiceImpl  extends ServiceImpl<JdyPurchaseMapper,JdyPurchase> implements JdyPurchaseService  {

    @Autowired
    JdyPurchaseMapper jdyPurchaseMapper;


    @Override
    public List<JdyPurchase> seletelist(JdyPurchaseDto jdyPurchaseDto) {
        QueryWrapper<JdyPurchase> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getPurchaseId() !=null && !jdyPurchaseDto.getPurchaseId().isEmpty()){
            queryWrapper.eq("purchase_id",jdyPurchaseDto.getPurchaseId());
        }
        if(jdyPurchaseDto.getInvoices() !=null && !jdyPurchaseDto.getInvoices().isEmpty()){
            queryWrapper.eq("invoices",jdyPurchaseDto.getInvoices());
        }
        List<JdyPurchase> jdyPurchases = jdyPurchaseMapper.selectList(queryWrapper);
        return jdyPurchases;
    }


    @Override
    public List<JdyPurchaseDto> seleteAllJdyPurchase(JdyPurchaseDto jdyPurchaseDto) {

        List<JdyPurchaseDto> jdyPurchases = jdyPurchaseMapper.seleteAllJdyPurchase(jdyPurchaseDto);
        return jdyPurchases;
    }

    @Override
    public JdyPurchase insertSave(JdyPurchase jdyPurchase) {
        int insert = jdyPurchaseMapper.insert(jdyPurchase);
       if(insert > 0){
          return jdyPurchase ;
       }
       return  null;
    }

}