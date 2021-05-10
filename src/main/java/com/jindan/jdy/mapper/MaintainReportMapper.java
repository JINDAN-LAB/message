package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.MaintainReportDto;
import com.jindan.jdy.common.pojo.MaintainReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface MaintainReportMapper extends BaseMapper<MaintainReport> {

    List<MaintainReport> seleteAlllist(MaintainReportDto maintainFacilityDto);
}
