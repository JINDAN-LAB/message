package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.pojo.AssayFacilityRecord;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(设备使用记录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayFacilityRecordService extends IService<AssayFacilityRecord> {

    Page<AssayFacilityRecord> seleteDepartmentSubfacility(AssayFacilityRecordDto departmentSuggestDto);
}