package com.jindan.jdy.service.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.dto.WarehouseCheckTitleDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.pojo.WarehouseCheckTitle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description:TODO(规则服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WarehouseCheckTitleService extends IService<WarehouseCheckTitle> {

    List<WarehouseCheckDto> seletelist(WarehouseCheck departmentSuggestDto);


    boolean removeAllAccess(String id);

    List<WarehouseCheckTitleDto> seletelistDepartmentSuggestDto(WarehouseCheckTitle departmentSuggestDto);

    List<WarehouseCheckTitle> seleteTile(WarehouseCheckTitle departmentSuggestDto);
}