package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.dto.JdyPutTypeDto;
import com.jindan.jdy.common.pojo.JdyPutType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPutTypeService extends IService<JdyPutType> {

    List<JdyPutType> seletelist(JdyPutType jdyPurchaseDto);

    boolean insertSave(JdyPutType jdyPurchaseDto);

    List<JdyPutTypeDto> seletelistOrder();
}