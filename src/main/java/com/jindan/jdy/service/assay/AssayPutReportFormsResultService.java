package com.jindan.jdy.service.assay;

import com.jindan.jdy.common.pojo.AssayPutReportForms;
import com.jindan.jdy.common.pojo.AssayPutReportFormsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(每天发货报表的结果集服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayPutReportFormsResultService extends IService<AssayPutReportFormsResult> {

    List<AssayPutReportFormsResult> seleteDepartmentSubfacility(AssayPutReportFormsResult departmentSuggestDto);
}