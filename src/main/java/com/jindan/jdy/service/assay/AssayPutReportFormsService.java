package com.jindan.jdy.service.assay;

import com.jindan.jdy.common.pojo.AssayPutReportForms;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(产品发货信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayPutReportFormsService extends IService<AssayPutReportForms> {

    List<AssayPutReportForms> seleteDepartmentSubfacility(AssayPutReportForms departmentSuggestDto);
}