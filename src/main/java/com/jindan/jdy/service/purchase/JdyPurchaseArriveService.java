package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.pojo.JdyPurchaseArrive;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(到货单表头信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPurchaseArriveService extends IService<JdyPurchaseArrive> {

    List<JdyPurchaseArrive> seletelist(JdyPurchaseArrive jdyPurchaseDto);

    boolean insertSave(JdyPurchaseArrive jdyPurchaseDto);

}