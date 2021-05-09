package com.jindan.jdy.service.department;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DepartmentSuggestDto;
import com.jindan.jdy.common.pojo.DepartmentSuggest;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DepartmentSuggestService extends IService<DepartmentSuggest> {

    List<DepartmentSuggest> seletelist(DepartmentSuggestDto departmentSuggestDto);

    Page<DepartmentSuggest> seletePagelist(DepartmentSuggestDto departmentSuggestDto);
}