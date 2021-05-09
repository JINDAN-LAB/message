package com.jindan.jdy.service.consumable;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableChuruDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;
/**   
 * @Description:TODO(耗材资产出入库内容服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutConsumableChuruService extends IService<StarchOrganizationPutConsumableChuru> {

    PageInfo<StarchOrganizationPutConsumableChuru> queryRelevanceCatList(StarchOrganizationPutConsumableChuruDto jdyFlatalog);

}