package com.jindan.jdy.service.zxing;

import com.jindan.jdy.common.pojo.ZxingVerify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(流程控制服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingVerifyService extends IService<ZxingVerify> {

    List<ZxingVerify > seletelist(ZxingVerify zxingStand);


}