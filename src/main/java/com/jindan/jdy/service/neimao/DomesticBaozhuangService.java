package com.jindan.jdy.service.neimao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.DomesticBaozhuang;

import java.util.List;

/**   
 * @Description:TODO(内贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DomesticBaozhuangService extends IService<DomesticBaozhuang> {

    List<DomesticBaozhuang> seletelist(DomesticBaozhuang domesticBaozhuang);
}