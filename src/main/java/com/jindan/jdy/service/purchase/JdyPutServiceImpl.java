package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyPutTypeDetails;
import com.jindan.jdy.common.mapper.JdyPutMapper;
import com.jindan.jdy.common.pojo.JdyPut;
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
public class JdyPutServiceImpl  extends ServiceImpl<JdyPutMapper,JdyPut> implements JdyPutService  {

    @Autowired
    JdyPutMapper jdyPutMapper;

    @Override
    public List<JdyPut> seletelist(JdyPut jdyPut) {
        QueryWrapper<JdyPut> queryWrapper =new QueryWrapper<>();
        if(jdyPut.getPutId() !=null && !jdyPut.getPutId().isEmpty()){
            queryWrapper.eq("put_id",jdyPut.getPutId());
        }
        if(jdyPut.getPutNumber() !=null && !jdyPut.getPutNumber().equals(" ")){
            queryWrapper.eq("put_number",jdyPut.getPutNumber());
        }
        if(jdyPut.getPutType() !=null && !jdyPut.getPutType().isEmpty()){
            queryWrapper.eq("put_type",jdyPut.getPutType());
        }
        if(jdyPut.getDepartment() !=null && !jdyPut.getDepartment().isEmpty()){
            queryWrapper.eq("department",jdyPut.getDepartment());
        }
        if(jdyPut.getFacilityName() !=null && !jdyPut.getFacilityName().isEmpty()){
            queryWrapper.eq("facility_name",jdyPut.getFacilityName());
        }
        if(jdyPut.getCommodityName() !=null && !jdyPut.getCommodityName().isEmpty()){
            queryWrapper.eq("commodity_name",jdyPut.getCommodityName());
        }
        if(jdyPut.getFacilityTypes() !=null && !jdyPut.getFacilityTypes().isEmpty()){
            queryWrapper.eq("facility_types",jdyPut.getFacilityTypes());
        }
        if(jdyPut.getSpecification() !=null && !jdyPut.getSpecification().isEmpty()){
            queryWrapper.eq("specification",jdyPut.getSpecification());
        }
        if(jdyPut.getParentId() !=null && !jdyPut.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPut.getParentId());
        }
        List<JdyPut> jdyPurchases = jdyPutMapper.selectList(queryWrapper);
        return jdyPurchases;
    }


    @Override
    public boolean insertSave(JdyPut jdyPurchaseDto) {
        return jdyPutMapper.insert(jdyPurchaseDto) > 0;
    }

    @Override
    public List<JdyPutTypeDetails> seleteDetailslist(JdyPut jdyPurchase) {
        return jdyPutMapper.seleteDetailslist(jdyPurchase);
    }
}