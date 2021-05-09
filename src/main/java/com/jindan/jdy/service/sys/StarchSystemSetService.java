package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.StarchSystemSet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(系统-设置表服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchSystemSetService extends IService<StarchSystemSet> {

    List<StarchSystemSet> seletelist(StarchSystemSet starchSystemSet);
}