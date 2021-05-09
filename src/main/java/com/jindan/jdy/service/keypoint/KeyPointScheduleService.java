package com.jindan.jdy.service.keypoint;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.KeyPointScheduleDto;
import com.jindan.jdy.common.pojo.KeyPointSchedule;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(重点项目服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface KeyPointScheduleService extends IService<KeyPointSchedule> {

    Page<KeyPointSchedule> seleteDepartmentFacility(KeyPointScheduleDto departmentSuggestDto);
}