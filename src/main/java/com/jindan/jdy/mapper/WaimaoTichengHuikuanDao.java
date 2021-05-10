package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.pojo.WaimaoTichengHuikuan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(外贸提成数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WaimaoTichengHuikuanDao extends BaseMapper<WaimaoTichengHuikuan> {

    List<WaimaoTichengHuikuan> seleteYuebiaoHuikuan();
}
