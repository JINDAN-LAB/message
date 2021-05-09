package com.jindan.jdy.service.department;

import com.jindan.jdy.common.dto.MaintainFacilityDto;
import com.jindan.jdy.common.pojo.MaintainFacility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface MaintainFacilityService extends IService<MaintainFacility> {

    List<MaintainFacility> seletelist(MaintainFacilityDto maintainFacilityDto);
}