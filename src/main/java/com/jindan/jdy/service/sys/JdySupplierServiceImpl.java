package com.jindan.jdy.service.sys;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdySupplierMapper;
import com.jindan.jdy.common.pojo.JdySupplier;
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
public class JdySupplierServiceImpl  extends ServiceImpl<JdySupplierMapper,JdySupplier> implements JdySupplierService  {

    @Autowired
    JdySupplierMapper jdySupplierMapper;


    @Override
    public List<JdySupplier> seletelist(JdySupplier jdySupplier) {

        QueryWrapper<JdySupplier> queryWrapper =new QueryWrapper<>();
        if(jdySupplier.getSupplierId() !=null &&  !jdySupplier.getSupplierId().isEmpty() ){
            queryWrapper.eq("supplier_id",jdySupplier.getSupplierId());
        }
        if(jdySupplier.getSupplierName() !=null && !jdySupplier.getSupplierName().isEmpty() ){
            queryWrapper.like("supplier_name",jdySupplier.getSupplierName());
        }
        if(jdySupplier.getSupplierLocation() !=null && !jdySupplier.getSupplierLocation().isEmpty() ){
            queryWrapper.like("supplier_location",jdySupplier.getSupplierLocation());
        }
        if(jdySupplier.getSupplierPerson() !=null && !jdySupplier.getSupplierPerson().isEmpty() ){
            queryWrapper.like("supplier_person",jdySupplier.getSupplierPerson());
        }
        if(jdySupplier.getSupplierNumber() !=null && !jdySupplier.getSupplierNumber().isEmpty() ){
            queryWrapper.like("supplier_number",jdySupplier.getSupplierNumber());
        }
        return jdySupplierMapper.selectList(queryWrapper);


    }
}