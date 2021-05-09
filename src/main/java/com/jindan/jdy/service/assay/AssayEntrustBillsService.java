package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayEntrustBillsDto;
import com.jindan.jdy.common.pojo.AssayEntrustBills;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(检测委托账单服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayEntrustBillsService extends IService<AssayEntrustBills> {

    Page<AssayEntrustBills> seleteDepartmentSubfacility(AssayEntrustBillsDto departmentSuggestDto);
}