package com.jindan.jdy.service.waimao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.WaimaoTichengFahuoDto;
import com.jindan.jdy.common.pojo.DomesticFahuo;
import com.jindan.jdy.common.pojo.WaimaoTichengFahuo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengFahuoService extends IService<WaimaoTichengFahuo> {

    Page<WaimaoTichengFahuo> seletelist(WaimaoTichengFahuoDto jdyRole);

    List<WaimaoTichengFahuoDto> seleteInFaHui(List<String> list);

    List<WaimaoTichengFahuo> seleteYuebiaoFa(DomesticFahuo fahuo);
}