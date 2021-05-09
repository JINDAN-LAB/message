package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.dto.JdyPurchaseDto;
import com.jindan.jdy.common.pojo.JdyPurchase;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPurchaseService extends IService<JdyPurchase> {

    List<JdyPurchase> seletelist(JdyPurchaseDto jdyPurchaseDto);

    List<JdyPurchaseDto> seleteAllJdyPurchase(JdyPurchaseDto jdyPurchaseDto);

    JdyPurchase insertSave(JdyPurchase jdyPurchase);
}