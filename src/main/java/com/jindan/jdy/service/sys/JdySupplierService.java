package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.JdySupplier;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdySupplierService extends IService<JdySupplier> {

    List<JdySupplier> seletelist(JdySupplier jdySupplier);
}