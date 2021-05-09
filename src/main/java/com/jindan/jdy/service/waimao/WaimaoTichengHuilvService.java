package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengHuilv;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengHuilvService extends IService<WaimaoTichengHuilv> {

    List<WaimaoTichengHuilv> seletelist(WaimaoTichengHuilv jdyRole);
}