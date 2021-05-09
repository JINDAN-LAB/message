package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyCommodityArrive;

import java.util.List;

/**   
 * @Description:TODO(到货单表体信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyCommodityArriveService extends IService<JdyCommodityArrive> {

    List<JdyCommodityArrive> seletelist(JdyCommodityArrive jdyPurchaseDto);

    boolean insertSave(JdyCommodityArrive jdyPurchaseDto);
}