package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoTargetDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;

import java.util.List;
import java.util.Map;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengFahuoService extends IService<WaimaoTichengFahuo> {

    Page<WaimaoTichengFahuo> seletelistByPage(WaimaoTichengFahuoDto jdyRole);

    List<WaimaoTichengFahuo> selectFahuolist(WaimaoTichengFahuoDto param);

    List<WaimaoTichengFahuoTargetDto> selectFahuoShuliang();

    List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list);

    List<WaimaoTichengFahuoDto> selectFaHuiCal(String fahuoDate);

    List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);

    void sumTargetshuliang(Map<String, Map<String, Float>> map);

    void sumFahuoshuliang(Map<String, Map<String, Float>> map);
}