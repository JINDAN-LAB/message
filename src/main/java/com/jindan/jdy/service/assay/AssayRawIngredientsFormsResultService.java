package com.jindan.jdy.service.assay;

import com.jindan.jdy.common.pojo.AssayRawIngredientsFormsResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(报表结果发货报表的结果集服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayRawIngredientsFormsResultService extends IService<AssayRawIngredientsFormsResult> {

    List<AssayRawIngredientsFormsResult> seleteDepartmentSubfacility(AssayRawIngredientsFormsResult departmentSuggestDto);
}