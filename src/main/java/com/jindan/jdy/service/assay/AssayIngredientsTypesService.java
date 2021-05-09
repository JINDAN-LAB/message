package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssayFacilityRecordDto;
import com.jindan.jdy.common.dto.AssayIngredientsTypesDto;
import com.jindan.jdy.common.pojo.AssayIngredientsTypes;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(检测-分类表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssayIngredientsTypesService extends IService<AssayIngredientsTypes> {

    Page<AssayIngredientsTypes> seleteAssayIngredientsTypes(AssayIngredientsTypesDto departmentSuggestDto);

    boolean updateDetailsById(AssayIngredientsTypes userPermission);

    boolean saveInsert(AssayIngredientsTypes userPermission);

    boolean removeDetailsById(String id);
}