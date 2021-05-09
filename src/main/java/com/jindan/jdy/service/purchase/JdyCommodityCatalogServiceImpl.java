package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.JdyCommodityArrive;
import com.jindan.jdy.common.pojo.JdyCommodityCatalog;
import com.jindan.jdy.common.mapper.JdyCommodityCatalogDao;
import com.jindan.jdy.service.purchase.JdyCommodityCatalogService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(仓库目录信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyCommodityCatalogServiceImpl  extends ServiceImpl<JdyCommodityCatalogDao,JdyCommodityCatalog> implements JdyCommodityCatalogService  {


    @Autowired
    JdyCommodityCatalogDao jdyCommodityCatalogDao;


    @Override
    public List<JdyCommodityCatalog> seletelist(JdyCommodityCatalog jdyPurchaseDto) {
        QueryWrapper<JdyCommodityCatalog> queryWrapper =new QueryWrapper<>();
        if(jdyPurchaseDto.getCmid() !=null &&  !jdyPurchaseDto.getCmid().isEmpty()){
            queryWrapper.eq("cmid",jdyPurchaseDto.getCmid());
        }
        if(jdyPurchaseDto.getName() !=null && !jdyPurchaseDto.getName().isEmpty()){
            queryWrapper.eq("name",jdyPurchaseDto.getName());
        }
        if(jdyPurchaseDto.getState() !=null &&  !jdyPurchaseDto.getState().isEmpty()){
            queryWrapper.eq("state",jdyPurchaseDto.getState());
        }
        if(jdyPurchaseDto.getCreateName() !=null && !jdyPurchaseDto.getCreateName().isEmpty()){
            queryWrapper.eq("create_name",jdyPurchaseDto.getCreateName());
        }
        if(jdyPurchaseDto.getCreateId() !=null &&  !jdyPurchaseDto.getCreateId().isEmpty()){
            queryWrapper.eq("create_id",jdyPurchaseDto.getCreateId());
        }
        if(jdyPurchaseDto.getParentId() !=null &&  !jdyPurchaseDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyPurchaseDto.getParentId());
        }
        if(jdyPurchaseDto.getLevels() !=null && !jdyPurchaseDto.getLevels().isEmpty()){
            queryWrapper.eq("levels",jdyPurchaseDto.getLevels());
        }
        if(jdyPurchaseDto.getOrders() !=null &&  !jdyPurchaseDto.getOrders().isEmpty()){
            queryWrapper.eq("orders",jdyPurchaseDto.getOrders());
        }
        return jdyCommodityCatalogDao.selectList(queryWrapper);
    }

    @Override
    public boolean insertSave(JdyCommodityCatalog jdyPurchaseDto) {
        return jdyCommodityCatalogDao.insert(jdyPurchaseDto)>0;
    }
}