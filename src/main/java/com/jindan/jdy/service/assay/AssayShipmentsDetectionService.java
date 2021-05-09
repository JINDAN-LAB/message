package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayShipmentsDetectionDto;
import com.jindan.jdy.common.pojo.AssayShipmentsDetection;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(产品发货信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayShipmentsDetectionService extends IService<AssayShipmentsDetection> {

    Page<AssayShipmentsDetection> seleteDepartmentSubfacility(AssayShipmentsDetectionDto departmentSuggestDto);

}