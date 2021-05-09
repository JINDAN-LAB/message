package com.jindan.jdy.service.neimao;

import com.jindan.jdy.common.pojo.DomesticJijiabiao;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(内贸提成服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface DomesticJijiabiaoService extends IService<DomesticJijiabiao> {

    List<DomesticJijiabiao> seletelist(DomesticJijiabiao domesticJijiabiao);
}