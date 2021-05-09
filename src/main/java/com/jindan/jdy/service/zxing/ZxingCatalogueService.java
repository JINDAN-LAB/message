package com.jindan.jdy.service.zxing;

import com.jindan.jdy.common.dto.ZxingCatalogueDto;
import com.jindan.jdy.common.pojo.ZxingCatalogue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**   
 * @Description:TODO(二维码目录服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingCatalogueService extends IService<ZxingCatalogue> {

    List<ZxingCatalogue> seletelist(ZxingCatalogue zxingCatalogue);

    List<ZxingCatalogueDto> seletelistCatalogueTemplate(ZxingCatalogueDto zxingCatalogue);
}