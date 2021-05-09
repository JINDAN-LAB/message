package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseAccessDto;
import com.jindan.jdy.common.pojo.KeyPointProject;
import com.jindan.jdy.common.pojo.WarehouseAccess;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(仓库管理服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseAccessService extends IService<WarehouseAccess> {

    List<KeyPointProject> seleteDepartmentFacility(WarehouseAccessDto departmentSuggestDto);
}