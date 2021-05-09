package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.pojo.KeyPointEvaluate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(重点工作评价服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface KeyPointEvaluateService extends IService<KeyPointEvaluate> {

    List<KeyPointEvaluate> seleteDepartmentFacility(KeyPointEvaluate departmentSuggestDto);
}