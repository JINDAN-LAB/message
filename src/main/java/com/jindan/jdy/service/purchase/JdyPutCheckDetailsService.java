package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyPutCheckDetails;

import java.util.List;

/**   
 * @Description:TODO(风险控制任务超期服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPutCheckDetailsService extends IService<JdyPutCheckDetails> {

    boolean insertSave(JdyPutCheckDetails jdyPurchaseDto);

    List<JdyPutCheckDetails> seletelist(JdyPutCheckDetails jdyPurchaseDto);
}