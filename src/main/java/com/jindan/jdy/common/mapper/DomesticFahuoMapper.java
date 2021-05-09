package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.DomesticFahuoDto;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**   
 * @Description:TODO(内贸提成数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface DomesticFahuoMapper extends BaseMapper<DomesticFahuo> {
    //    根据发货ID获取发货信息
    List<DomesticFahuoDto> seleteInFaHui(List<String> list);
//    根据发货信息导出发货信息
    List<DomesticFahuoDto> seleteYijisuanexcle(DomesticFahuoDto list);

     void saveAllBatch(List<DomesticFahuo> list);

    List<DomesticFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);

    List<DomesticFahuoDto> getOneById(DomesticFahuo fahuo);

    List<DomesticFahuoDto> seleteInFaDetails( Set<String> collection);
}
