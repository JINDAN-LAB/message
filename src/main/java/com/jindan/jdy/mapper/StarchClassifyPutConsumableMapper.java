package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.StarchClassifyPutConsumableDto;
import com.jindan.jdy.common.pojo.StarchClassifyPutConsumable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(耗材资产分类数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface StarchClassifyPutConsumableMapper extends BaseMapper<StarchClassifyPutConsumable> {

    List<StarchClassifyPutConsumableDto> selectAllList();
}
