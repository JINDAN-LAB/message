package com.jindan.jdy.service.department;

import com.jindan.jdy.common.dto.MaintainReportDto;
import com.jindan.jdy.common.pojo.MaintainReport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(设备维修申报服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface MaintainReportService extends IService<MaintainReport> {

    List<MaintainReport> seletelist(MaintainReportDto maintainFacilityDto);

    List<MaintainReport> seleteAlllist(MaintainReportDto maintainFacilityDto);
}