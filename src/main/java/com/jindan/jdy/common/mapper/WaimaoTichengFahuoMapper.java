package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;

import java.util.List;

/**   
 * @Description:TODO(外贸提成数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WaimaoTichengFahuoMapper extends BaseMapper<WaimaoTichengFahuo> {

    List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list);

    List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);
}
