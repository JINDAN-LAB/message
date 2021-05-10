package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyPutTypeDto;
import com.jindan.jdy.mapper.JdyPutTypeMapper;
import com.jindan.jdy.common.pojo.JdyPutType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:TODO(API应用KEY服务实现)
 * @version: V1.0
 * @author: kong
 */

@Component
public class JdyPutTypeServiceImpl  extends ServiceImpl<JdyPutTypeMapper,JdyPutType> implements JdyPutTypeService  {

    @Autowired
    JdyPutTypeMapper jdyPutTypeMapper;

    @Override
    public List<JdyPutType> seletelist(JdyPutType jdyPut) {

        QueryWrapper<JdyPutType> queryWrapper =new QueryWrapper<>();
        if(jdyPut.getPutTypeId() !=null && !jdyPut.getPutTypeId().isEmpty()){
            queryWrapper.eq("put_type_id",jdyPut.getPutTypeId());
        }
        if(jdyPut.getPutTypeName() !=null && !jdyPut.getPutTypeName().equals(" ")){
            queryWrapper.eq("put_type_name",jdyPut.getPutTypeName());
        }
        if(jdyPut.getPutTypeType() !=null && !jdyPut.getPutTypeType().isEmpty()){
            queryWrapper.eq("put_type_type",jdyPut.getPutTypeType());
        }
        if(jdyPut.getParentId() !=null && !jdyPut.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPut.getParentId());
        }
        if(jdyPut.getOrders() !=null && !jdyPut.getOrders().isEmpty()){
            queryWrapper.eq("orders",jdyPut.getOrders());
        }
        List<JdyPutType> jdyPurchases = jdyPutTypeMapper.selectList(queryWrapper);
        return jdyPurchases;
    }

    @Override
    public boolean insertSave(JdyPutType jdyPurchaseDto) {
        return jdyPutTypeMapper.insert(jdyPurchaseDto) > 0;
    }

    @Override
    public List<JdyPutTypeDto> seletelistOrder() {
        List<JdyPutTypeDto> data = jdyPutTypeMapper.selectAll();
        List<JdyPutTypeDto> categoriesList = new ArrayList<>();
        for(JdyPutTypeDto  category : data){
            if(category.getParentId().equals("0")){
                categoriesList.add(category);
            }
        }
        for(JdyPutTypeDto category : categoriesList){
            category.setTypeList(getChilde(category.getPutTypeId(), data));
        }
        return categoriesList;
    }

    private List<JdyPutTypeDto> getChilde(String id, List<JdyPutTypeDto> rootList){
        List<JdyPutTypeDto> childList = new ArrayList<>();
        for(JdyPutTypeDto category : rootList){
            if(category.getParentId().equals(id)){
                childList.add(category);
            }
        }
        for(JdyPutTypeDto category : childList){
            category.setTypeList(getChilde(category.getPutTypeId(), rootList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }
}