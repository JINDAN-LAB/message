package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.dto.JdyCommodityDto;
import com.jindan.jdy.common.pojo.JdyCommodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyPurchase;

import java.util.List;

/**   
 * @Description:TODO(API应用KEY服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyCommodityService extends IService<JdyCommodity> {

    boolean deleteremove(String purchaseId);

    boolean deleteremoveByid(String commodityId);

    List<JdyCommodity> seleteListJdyCommodity(JdyCommodityDto jdyCommodityDto);

}