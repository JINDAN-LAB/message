package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.mapper.AssayEntrustBillsDao;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import com.jindan.jdy.common.pojo.AssayFacilityRecord;
import com.jindan.jdy.common.mapper.AssayFacilityRecordDao;
import com.jindan.jdy.service.assay.AssayFacilityRecordService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(设备使用记录服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class AssayFacilityRecordServiceImpl  extends ServiceImpl<AssayFacilityRecordDao,AssayFacilityRecord> implements AssayFacilityRecordService  {


    @Autowired
    AssayFacilityRecordDao assayEntrustBillsDao;

    @Override
    public Page<AssayFacilityRecord> seleteDepartmentSubfacility(AssayFacilityRecordDto departmentSuggestDto) {

        if(departmentSuggestDto.getCurrentPage() <= 0  ){
            departmentSuggestDto.setCurrentPage(1);
        }
        Page<AssayFacilityRecord> page =new Page<>(departmentSuggestDto.getCurrentPage(),departmentSuggestDto.getPageSize());

        QueryWrapper<AssayFacilityRecord> queryWrapper =new QueryWrapper<>();
        if( departmentSuggestDto.getEqid() !=null &&  !departmentSuggestDto.getEqid().isEmpty()  ){
            queryWrapper.eq("eqid",departmentSuggestDto.getEqid());
        }
        if( departmentSuggestDto.getDetectionTime() !=null && !departmentSuggestDto.getDetectionTime().isEmpty() ){
            queryWrapper.like("detection_time",departmentSuggestDto.getDetectionTime());
        }
        if( departmentSuggestDto.getShouSample() !=null && !departmentSuggestDto.getShouSample().isEmpty() ){
            queryWrapper.like("shou_sample",departmentSuggestDto.getShouSample());
        }
        if( departmentSuggestDto.getDetectionItem() !=null && !departmentSuggestDto.getDetectionItem().isEmpty() ){
            queryWrapper.like("detection_item",departmentSuggestDto.getDetectionItem());
        }
        if( departmentSuggestDto.getSampleIdentifying() !=null &&  !departmentSuggestDto.getSampleIdentifying().isEmpty() ){
            queryWrapper.like("sample_identifying",departmentSuggestDto.getSampleIdentifying());
        }
        if( departmentSuggestDto.getUseTime() !=null &&  !departmentSuggestDto.getUseTime().isEmpty() ){
            queryWrapper.like("use_time",departmentSuggestDto.getUseTime());
        }
        if( departmentSuggestDto.getFacilityStatus() !=null &&  !departmentSuggestDto.getFacilityStatus().isEmpty() ){
            queryWrapper.like("facility_status",departmentSuggestDto.getFacilityStatus());
        }
        if( departmentSuggestDto.getUsePerson() !=null ){
            queryWrapper.like("use_person",departmentSuggestDto.getUsePerson());
        }
        return (Page<AssayFacilityRecord>) assayEntrustBillsDao.selectPage(page,queryWrapper);
    }

}