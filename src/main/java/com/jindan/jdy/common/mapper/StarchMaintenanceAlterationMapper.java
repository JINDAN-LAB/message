package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchMaintainRegisterDto;
import com.jindan.jdy.common.dto.StarchMaintenanceAlterationDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.StarchMaintenanceAlteration;

import java.util.List;

/**   
 * @Description:TODO(资产维保信息变更数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchMaintenanceAlterationMapper extends BaseMapper<StarchMaintenanceAlteration> {
//    int insert(UserInfo record);

    List<StarchMaintenanceAlterationDto> queryRelevanceCatList(StarchMaintenanceAlterationDto jdyRole);
}
