package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoFahuoDto;
import com.jindan.jdy.common.dto.WaimaoFahuoHuikuan;
import com.jindan.jdy.common.pojo.WaimaoFahuo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(规则服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoFahuoService extends IService<WaimaoFahuo> {

//    发货信息分页表
    Page<WaimaoFahuo> seletelist(WaimaoFahuoDto jdyRole);

//    发货回款关联表
    List<WaimaoFahuoHuikuan> seleteAlllist(WaimaoFahuo param);

//    产品分类统计表
    List<WaimaoFahuo> seleteAlllistgroupby(WaimaoFahuoDto jdyRole);

    //    销售区域统计表
    List<WaimaoFahuoDto> seleteMarketgroupby(WaimaoFahuoDto jdyRole);

//  客户销量排名表
    List<WaimaoFahuoDto> seleteTranking(WaimaoFahuoDto jdyRole);

//    销售人员完成情况表
    List<WaimaoFahuoDto> seleteCompletion(WaimaoFahuoDto jdyRole);

// 根据年份进行查询汇总
    List<WaimaoFahuoDto> seleteYearCompletion(WaimaoFahuoDto jdyRole);

//    销售区域统计表按照月份统计
    List<WaimaoFahuo> seleteYuefenTongji(WaimaoFahuo jdyRole);

//    按照目的国进行统计分析
    List<WaimaoFahuo> seleteAnzhaoQuyuYuefenTongji(WaimaoFahuoDto jdyRole);

//    月份大于
    List<WaimaoFahuo> seleteYuefenDayuAlllist(WaimaoFahuo param);

//    当月的发货信息
    List<WaimaoFahuo> seleteDangyuefenDayuAlllist(WaimaoFahuo param);

    List<WaimaoFahuoDto> seleteDangyueCompletion(WaimaoFahuo  jdyRole) throws ParseException;

    List<WaimaoFahuoDto> seleteShangyueCompletion(WaimaoFahuo jdyRole);

    List<WaimaoFahuoDto> seleteLeijiCompletion(WaimaoFahuo  jdyRole);

    List<WaimaoFahuo> seletePaimingTranking(WaimaoFahuo jdyRole);

    List<WaimaoFahuo> seleteFahuoDanjuhao(WaimaoFahuo param);

    Map<String, WaimaoFahuo> selelteLeijixiaoshou(WaimaoFahuo jdyRole) throws ParseException;

    Map<String, Float> selelteLeiqiankuan(WaimaoFahuo jdyRole) throws ParseException;

//    根据月份查询汇总
//    List<WaimaoFahuo> productByTheMonth(String yuefen);
}