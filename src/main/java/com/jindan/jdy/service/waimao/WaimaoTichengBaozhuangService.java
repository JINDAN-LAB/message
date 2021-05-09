package com.jindan.jdy.service.waimao;

import com.jindan.jdy.common.pojo.WaimaoTichengBaozhuang;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(外贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface WaimaoTichengBaozhuangService extends IService<WaimaoTichengBaozhuang> {

    List<WaimaoTichengBaozhuang> seletelist(WaimaoTichengBaozhuang jdyRole);

}