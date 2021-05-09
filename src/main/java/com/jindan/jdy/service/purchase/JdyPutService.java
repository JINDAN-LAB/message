package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.dto.JdyPutTypeDetails;
import com.jindan.jdy.common.pojo.JdyPut;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPutService extends IService<JdyPut> {

    List<JdyPut> seletelist(JdyPut jdyPut);

    boolean insertSave(JdyPut jdyPurchaseDto);

    List<JdyPutTypeDetails> seleteDetailslist(JdyPut jdyPurchase);

}