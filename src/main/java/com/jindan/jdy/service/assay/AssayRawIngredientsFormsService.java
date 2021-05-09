package com.jindan.jdy.service.assay;

import com.jindan.jdy.common.pojo.AssayRawIngredientsForms;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(报表发货报表的结果集服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayRawIngredientsFormsService extends IService<AssayRawIngredientsForms> {

    List<AssayRawIngredientsForms> seleteDepartmentSubfacility(AssayRawIngredientsForms departmentSuggestDto);
}