package com.jindan.jdy.service.department;

import com.jindan.jdy.common.pojo.JdyPacking;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(领料单服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyPackingService extends IService<JdyPacking> {

    List<JdyPacking> seletelist(JdyPacking jdyPacking);
}