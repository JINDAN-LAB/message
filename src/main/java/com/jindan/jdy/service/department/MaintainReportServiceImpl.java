package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.MaintainReportDto;
import com.jindan.jdy.common.mapper.MaintainReportMapper;
import com.jindan.jdy.common.pojo.MaintainReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class MaintainReportServiceImpl  extends ServiceImpl<MaintainReportMapper,MaintainReport> implements MaintainReportService  {

    @Autowired
    MaintainReportMapper maintainReportDao;

    @Override
    public List<MaintainReport> seletelist(MaintainReportDto maintainFacilityDto) {

        QueryWrapper<MaintainReport> queryWrapper =new QueryWrapper<>();
        if( maintainFacilityDto.getInsertTime() !=null &&  maintainFacilityDto.getInsertTime().getTime()  > 0 ){
            queryWrapper.like("insert_time",maintainFacilityDto.getInsertTime());
        }
        if( maintainFacilityDto.getSubmitContent() !=null && !maintainFacilityDto.getSubmitContent().isEmpty() ){
            queryWrapper.eq("submit_content",maintainFacilityDto.getSubmitContent());
        }
        if( maintainFacilityDto.getSubmitPerson() !=null && !maintainFacilityDto.getSubmitPerson().isEmpty() ){
            queryWrapper.eq("submit_person",maintainFacilityDto.getSubmitPerson());
        }
        if( maintainFacilityDto.getSubmitTime() !=null &&  !maintainFacilityDto.getSubmitTime().isEmpty()){
            queryWrapper.eq("submit_time",maintainFacilityDto.getSubmitTime());
        }
        if( maintainFacilityDto.getParentId() !=null && !maintainFacilityDto.getParentId().isEmpty() ){
            queryWrapper.eq("parent_id",maintainFacilityDto.getParentId());
        }
        if( maintainFacilityDto.getFacililtyPerson() !=null && !maintainFacilityDto.getFacililtyPerson().isEmpty() ){
            queryWrapper.eq("facililty_person",maintainFacilityDto.getFacililtyPerson());
        }
        return  maintainReportDao.selectList(queryWrapper);
    }

    @Override
    public List<MaintainReport> seleteAlllist(MaintainReportDto maintainFacilityDto) {

        return maintainReportDao.seleteAlllist(maintainFacilityDto);
    }

}