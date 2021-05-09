package com.jindan.jdy.service.zxing;

import com.jindan.jdy.common.pojo.ZxingErweimakg;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(自定义验证二维码域名目录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingErweimakgService extends IService<ZxingErweimakg> {

    List<ZxingErweimakg> seletelist(ZxingErweimakg zxingErweimakg);
}