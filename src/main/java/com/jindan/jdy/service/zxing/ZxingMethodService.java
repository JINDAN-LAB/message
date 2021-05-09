package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.ZxingMethod;

import java.util.List;

/**   
 * @Description:TODO(自定义验证二维码域名目录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingMethodService extends IService<ZxingMethod> {

    List<ZxingMethod> seletelist(ZxingMethod zxingMethod);
}