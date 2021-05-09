package com.jindan.jdy.service.purchase;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.JdyDetailsGoods;

import java.util.List;

/**   
 * @Description:TODO(仓库基本信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyDetailsGoodsService extends IService<JdyDetailsGoods> {

    List<JdyDetailsGoods> seletelist(JdyDetailsGoods jdyPurchaseDto);

    boolean insertSave(JdyDetailsGoods jdyPurchaseDto);


}