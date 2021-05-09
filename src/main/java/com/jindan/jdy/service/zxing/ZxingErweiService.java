package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.dto.ZxingErweiDto;
import com.jindan.jdy.common.pojo.ZxingErwei;

/**   
 * @Description:TODO(二维码服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingErweiService extends IService<ZxingErwei> {

    Page<ZxingErwei> seletelist(ZxingErweiDto zxingErweiDto);
}