package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyPutCheck;

import java.util.List;

/**   
 * @Description:TODO(风险控制任务超期服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPutCheckService extends IService<JdyPutCheck> {

    boolean insertSave(JdyPutCheck jdyPurchaseDto);

    List<JdyPutCheck> seletelist(JdyPutCheck jdyPurchaseDto);
}