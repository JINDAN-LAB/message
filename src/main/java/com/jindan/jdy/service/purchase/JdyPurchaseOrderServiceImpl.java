package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.pojo.JdyPurchaseOrder;
import com.jindan.jdy.common.mapper.JdyPurchaseOrderDao;
import com.jindan.jdy.service.purchase.JdyPurchaseOrderService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

/**   
 * @Description:TODO(采购订单信息服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Service(version = "service.version")
@Component
public class JdyPurchaseOrderServiceImpl  extends ServiceImpl<JdyPurchaseOrderDao,JdyPurchaseOrder> implements JdyPurchaseOrderService  {
	
}