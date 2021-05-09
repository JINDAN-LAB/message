package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.DomesticFahuoDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.DomesticHuikuan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @Description:TODO(内贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DomesticFahuoService extends IService<DomesticFahuo> {

    Page<DomesticFahuo> seletepage(DomesticFahuoDto domesticFahuoDto);


    Page<DomesticFahuo> seleteyijisuanpage(DomesticFahuoDto domesticFahuoDto);

     Page<DomesticFahuo> selebudayinpage(DomesticFahuoDto domesticFahuoDto);

    Page<DomesticFahuo> seleteyidayinpage(DomesticFahuoDto domesticFahuoDto);

//    按照客户或者年份汇总信息
    List<DomesticFahuo> seleGroupBy(DomesticFahuo domesticFahuo);
//    按照客户或者年份汇总信息
    List<DomesticHuikuan> seleGrouphuikuanBy(DomesticHuikuan huikuan);

//    根据提交的ID获取发货单信息
    List<DomesticFahuoDto> seleteInFaHui(List<String> list);

//   查询发货信息
    List<DomesticFahuo> seleteFhuolist(DomesticFahuo domesticFahuo);

//    根据发货信息参数到处发货信息
    List<DomesticFahuo> seleteExclelist(DomesticFahuoDto param);

//    根据条件进行导出信息
    List<DomesticFahuoDto> seleteYijisuanexcle(DomesticFahuoDto param);

    void saveAllBatch(List<DomesticFahuo> jindantichengList);

//    客户发货余额
    List<DomesticFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);

    List<DomesticFahuoDto> getOneById(Integer ids);

    List<DomesticFahuoDto> seleteInFaDetails(@Param("filterList") Set<String> filterList);
}