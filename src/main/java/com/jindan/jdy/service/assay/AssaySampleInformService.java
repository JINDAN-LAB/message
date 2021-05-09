package com.jindan.jdy.service.assay;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.AssaySampleInformDto;
import com.jindan.jdy.common.pojo.AssaySampleInform;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(检测抽样样品服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface AssaySampleInformService extends IService<AssaySampleInform> {

    Page<AssaySampleInform> seleteDepartmentSubfacility(AssaySampleInformDto departmentSuggestDto);
}