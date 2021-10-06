package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoTargetDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;
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
public interface WaimaoTichengFahuoMapper extends BaseMapper<WaimaoTichengFahuo> {

    List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list);

    List<WaimaoTichengFahuoDto> selectFaHuiCal(String fahuoDate);

    List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);

    List<WaimaoTichengFahuoTargetDto> selectFahuoShuliang();
}
