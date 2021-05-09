package com.jindan.jdy.service.purchase;

import com.jindan.jdy.common.pojo.JdyCommodityCatalog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(仓库目录信息服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface JdyCommodityCatalogService extends IService<JdyCommodityCatalog> {

    List<JdyCommodityCatalog> seletelist(JdyCommodityCatalog jdyPurchaseDto);

    boolean insertSave(JdyCommodityCatalog jdyPurchaseDto);
}