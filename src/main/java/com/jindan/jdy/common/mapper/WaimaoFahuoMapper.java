package com.jindan.jdy.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.WaimaoFahuoDto;
import com.jindan.jdy.common.dto.WaimaoFahuoHuikuan;
import org.apache.ibatis.annotations.Mapper;
import com.jindan.jdy.common.pojo.WaimaoFahuo;

import java.util.List;

/**   
 * @Description:TODO(规则数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface WaimaoFahuoMapper extends BaseMapper<WaimaoFahuo> {


    List<WaimaoFahuoHuikuan> seleteAlllist(WaimaoFahuo param);

//    产品分类统计表
    List<WaimaoFahuo> seleteAlllistgroupby(WaimaoFahuoDto param);

//    销售区域排名表
    List<WaimaoFahuoDto> seleteMarketgroupby(WaimaoFahuoDto jdyRole);

//    客户销量排名表
    List<WaimaoFahuoDto> seleteTranking(WaimaoFahuoDto jdyRole);

//    销售人员完成情况表
    List<WaimaoFahuoDto> seleteCompletion(WaimaoFahuoDto jdyRole);

//    根据年份进行查询
    List<WaimaoFahuoDto> seleteYearCompletion(WaimaoFahuoDto jdyRole);

// 销售统计统计表
    List<WaimaoFahuo> seleteYuefenTongji(WaimaoFahuo jdyRole);

    List<WaimaoFahuo> seleteAnzhaoQuyuYuefenTongji(WaimaoFahuoDto jdyRole);

    List<WaimaoFahuo> seleteYuefenDayuAlllist(WaimaoFahuo param);

    List<WaimaoFahuo> seleteDangyuefenDayuAlllist(WaimaoFahuo param);

    List<WaimaoFahuoDto> seleteDangyueCompletion(WaimaoFahuo  jdyRole);

    List<WaimaoFahuoDto> seleteShangyueCompletion(WaimaoFahuo  jdyRole);

    List<WaimaoFahuoDto> seleteLeijiCompletion(WaimaoFahuo  jdyRole);

    List<WaimaoFahuo> seletePaimingTranking(WaimaoFahuo jdyRole);

    List<WaimaoFahuo> seleteFahuoDanjuhao(WaimaoFahuo param);

    List<WaimaoFahuo> seleteWaimaoAlllist(WaimaoFahuo jdyRole);
// 全年分类表
    List<WaimaoFahuo> seleteQuannianWaimaoAlllist(WaimaoFahuo jdyRole);

//    累计销售金额
    List<WaimaoFahuo> selelteLeijixiaoshou(WaimaoFahuo jdyRole);

    List<WaimaoFahuoDto> seleteMubiao(WaimaoFahuo jdyRole);
}
