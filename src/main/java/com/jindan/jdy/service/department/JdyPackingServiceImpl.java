package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.mapper.JdyPackingMapper;
import com.jindan.jdy.common.pojo.JdyPacking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(领料单服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class JdyPackingServiceImpl  extends ServiceImpl<JdyPackingMapper,JdyPacking> implements JdyPackingService  {

    @Autowired
    JdyPackingMapper jdyPackingMapper;

    @Override
    public List<JdyPacking> seletelist(JdyPacking jdyPacking) {
        QueryWrapper<JdyPacking> queryWrapper =new QueryWrapper<>();
        if( jdyPacking.getPickingId() !=null && !jdyPacking.getPickingId().isEmpty() ){
            queryWrapper.eq("picking_id",jdyPacking.getPickingId());
        }
        if( jdyPacking.getPickTitle() !=null && !jdyPacking.getPickTitle().isEmpty() ){
            queryWrapper.like("pick_title",jdyPacking.getPickTitle());
        }
        if( jdyPacking.getPickContent() !=null && !jdyPacking.getPickContent().isEmpty() ){
            queryWrapper.like("pick_content",jdyPacking.getPickContent());
        }
        if( jdyPacking.getPickNum() !=null && jdyPacking.getPickNum() >0 ){
            queryWrapper.like("pick_num",jdyPacking.getPickNum());
        }
        if( jdyPacking.getRemarks() !=null && !jdyPacking.getRemarks().isEmpty() ){
            queryWrapper.like("remarks",jdyPacking.getRemarks());
        }
        if( jdyPacking.getInsertTime() !=null  ){
            queryWrapper.like("insert_time",jdyPacking.getInsertTime());
        }
        if( jdyPacking.getPackPerson() !=null && !jdyPacking.getPackPerson().isEmpty() ){
            queryWrapper.like("pack_person",jdyPacking.getPackPerson());
        }
        if( jdyPacking.getPackDepartment() !=null && !jdyPacking.getPackDepartment().isEmpty() ){
            queryWrapper.like("pack_department",jdyPacking.getPackDepartment());
        }
        if( jdyPacking.getPackUuid() !=null && !jdyPacking.getPackUuid().isEmpty() ){
            queryWrapper.like("pack_uuid",jdyPacking.getPackUuid());
        }
        if( jdyPacking.getStatus() !=null  ){
            queryWrapper.eq("status",jdyPacking.getStatus());
        }
        return jdyPackingMapper.selectList(queryWrapper);
    }


}