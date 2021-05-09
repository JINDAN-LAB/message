package com.jindan.jdy.service.sys;

import com.jindan.jdy.common.pojo.JdyCommodityApplet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(小程序商品服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyCommodityAppletService extends IService<JdyCommodityApplet> {

    boolean saveJdyDomain(JdyCommodityApplet jdyDomain);

    List<JdyCommodityApplet> seleteAlllist(JdyCommodityApplet jdyDomain);
}