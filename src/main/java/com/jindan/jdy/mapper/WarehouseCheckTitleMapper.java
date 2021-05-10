package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WarehouseCheckDto;
import com.jindan.jdy.common.dto.WarehouseCheckTitleDto;
import com.jindan.jdy.common.pojo.WarehouseCheck;
import com.jindan.jdy.common.pojo.WarehouseCheckTitle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(规则数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WarehouseCheckTitleMapper extends BaseMapper<WarehouseCheckTitle> {

    List<WarehouseCheckDto> seletelist(WarehouseCheck departmentSuggestDto);

    List<WarehouseCheckTitleDto> seletelistDepartmentSuggestDto(WarehouseCheckTitle departmentSuggestDto);
}
