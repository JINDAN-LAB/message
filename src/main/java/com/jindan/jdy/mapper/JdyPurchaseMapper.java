package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.pojo.JdyPurchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyPurchaseMapper extends BaseMapper<JdyPurchase> {

    List<JdyPurchaseDto> seleteAllJdyPurchase(JdyPurchaseDto queryWrapper);

}
