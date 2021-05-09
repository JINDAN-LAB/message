package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.JdyPutTypeDetails;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.JdyPut;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface JdyPutMapper extends BaseMapper<JdyPut> {

    List<JdyPutTypeDetails> seleteDetailslist(JdyPut jdyPurchase);
}
