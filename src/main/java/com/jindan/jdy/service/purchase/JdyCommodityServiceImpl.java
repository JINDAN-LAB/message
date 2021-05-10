package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.JdyCommodityDto;
import com.jindan.jdy.mapper.JdyCommodityDao;
import com.jindan.jdy.common.pojo.JdyCommodity;
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
public class JdyCommodityServiceImpl  extends ServiceImpl<JdyCommodityDao,JdyCommodity> implements JdyCommodityService  {


    @Autowired
    JdyCommodityDao jdyCommodityDao;

    @Override
    public boolean deleteremove(String purchaseId) {
        QueryWrapper<JdyCommodity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",purchaseId);
        return jdyCommodityDao.delete(queryWrapper) > 0;
    }

    @Override
    public boolean deleteremoveByid(String commodityId) {
        return jdyCommodityDao.deleteById(commodityId) > 0;
    }

    @Override
    public List<JdyCommodity> seleteListJdyCommodity(JdyCommodityDto jdyCommodityDto) {

        QueryWrapper<JdyCommodity> queryWrapper =new QueryWrapper<>();
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
        if(jdyCommodityDto.getStatus() !=null &&  jdyCommodityDto.getStatus() >= 0 ){
            queryWrapper.eq("status",jdyCommodityDto.getStatus());
        }
        if(jdyCommodityDto.getParentId() !=null &&  !jdyCommodityDto.getParentId().isEmpty()){
            queryWrapper.eq("parent_id",jdyCommodityDto.getParentId());
        }
        return jdyCommodityDao.selectList(queryWrapper);
    }


}