package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.dto.MaintainFacilityDto;
import com.jindan.jdy.common.pojo.MaintainFacility;
import com.jindan.jdy.common.mapper.MaintainFacilityMapper;

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
public class MaintainFacilityServiceImpl  extends ServiceImpl<MaintainFacilityMapper,MaintainFacility> implements MaintainFacilityService  {

    @Autowired
    MaintainFacilityMapper maintainFacilityDao;


    @Override
    public List<MaintainFacility> seletelist(MaintainFacilityDto maintainFacility) {

        QueryWrapper<MaintainFacility> queryWrapper =new QueryWrapper<>();
        if( maintainFacility.getInsertTime() !=null &&  maintainFacility.getInsertTime().getTime()  > 0 ){
            queryWrapper.eq("insert_time",maintainFacility.getInsertTime());
        }
        if( maintainFacility.getMaintainPerson() !=null && !maintainFacility.getMaintainPerson().isEmpty() ){
            queryWrapper.eq("maintain_person",maintainFacility.getMaintainPerson());
        }
        if( maintainFacility.getMaintainType() !=null && !maintainFacility.getMaintainType().isEmpty() ){
            queryWrapper.eq("maintain_type",maintainFacility.getMaintainType());
        }
        if( maintainFacility.getDepartFacilityId() !=null &&  !maintainFacility.getDepartFacilityId().isEmpty()){
            queryWrapper.eq("depart_facility_id",maintainFacility.getDepartFacilityId());
        }
        return maintainFacilityDao.selectList(queryWrapper);
    }
}