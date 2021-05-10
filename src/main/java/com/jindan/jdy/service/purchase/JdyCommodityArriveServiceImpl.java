package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.mapper.JdyCommodityArriveDao;
import com.jindan.jdy.common.pojo.JdyCommodityArrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(到货单表体信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyCommodityArriveServiceImpl  extends ServiceImpl<JdyCommodityArriveDao,JdyCommodityArrive> implements JdyCommodityArriveService  {

    @Autowired
    JdyCommodityArriveDao jdyCommodityArriveDao;

    @Override
    public List<JdyCommodityArrive> seletelist(JdyCommodityArrive jdyCommodityDto) {
        QueryWrapper<JdyCommodityArrive> queryWrapper =new QueryWrapper<>();
        if(jdyCommodityDto.getCommodityId() !=null &&  !jdyCommodityDto.getCommodityId().isEmpty()){
            queryWrapper.eq("commodity_id",jdyCommodityDto.getCommodityId());
        }
        if(jdyCommodityDto.getFacilityName() !=null && !jdyCommodityDto.getFacilityName().isEmpty()){
            queryWrapper.eq("facility_name",jdyCommodityDto.getFacilityName());
        }
        if(jdyCommodityDto.getCommodityName() !=null &&  !jdyCommodityDto.getCommodityName().isEmpty()){
            queryWrapper.eq("commodity_name",jdyCommodityDto.getCommodityName());
        }
        if(jdyCommodityDto.getPerson() !=null && !jdyCommodityDto.getPerson().isEmpty()){
            queryWrapper.eq("person",jdyCommodityDto.getPerson());
        }
        if(jdyCommodityDto.getStatus() !=null &&  !jdyCommodityDto.getStatus().isEmpty()){
            queryWrapper.eq("status",jdyCommodityDto.getStatus());
        }
        if(jdyCommodityDto.getParentId() !=null &&  !jdyCommodityDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyCommodityDto.getParentId());
        }
        return jdyCommodityArriveDao.selectList(queryWrapper);
    }

    @Override
    public boolean insertSave(JdyCommodityArrive jdyPurchaseDto) {
        return jdyCommodityArriveDao.insert(jdyPurchaseDto) > 0;
    }
}