package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyPurchaseArriveTest;

import java.util.List;

/**   
 * @Description:TODO(化验室委托单服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPurchaseArriveTestService extends IService<JdyPurchaseArriveTest> {

    boolean insertSave(JdyPurchaseArriveTest jdyPurchaseDto);

    List<JdyPurchaseArriveTest> seletelist(JdyPurchaseArriveTest jdyPurchaseDto);

}