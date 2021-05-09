package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointPracticableDto;
import com.jindan.jdy.common.pojo.KeyPointPracticable;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**   
 * @Description:TODO(重点项目服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface KeyPointPracticableService extends IService<KeyPointPracticable> {

    Page<KeyPointPracticable> seleteDepartmentFacility(KeyPointPracticableDto departmentSuggestDto);
}