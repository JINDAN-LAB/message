package com.jindan.jdy.service.consumable;

import com.github.pagehelper.PageInfo;
import com.jindan.jdy.common.dto.StarchOrganizationAccessConsumableDto;
import com.jindan.jdy.common.dto.StarchOrganizationPutConsumableChuruDto;
import com.jindan.jdy.common.pojo.StarchOrganizationPutConsumableChuru;
import com.baomidou.mybatisplus.extension.service.IService;
/**   
 * @Description:TODO(耗材资产出入库内容服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface StarchOrganizationPutConsumableChuruService extends IService<StarchOrganizationPutConsumableChuru> {

    PageInfo<StarchOrganizationPutConsumableChuru> queryRelevanceCatList(StarchOrganizationPutConsumableChuruDto jdyFlatalog);

}