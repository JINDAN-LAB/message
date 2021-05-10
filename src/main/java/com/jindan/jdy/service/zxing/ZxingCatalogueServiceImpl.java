package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jindan.jdy.common.dto.ZxingCatalogueDto;
import com.jindan.jdy.mapper.ZxingCatalogueMapper;
import com.jindan.jdy.common.pojo.ZxingCatalogue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(二维码目录服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class ZxingCatalogueServiceImpl  extends ServiceImpl<ZxingCatalogueMapper,ZxingCatalogue> implements ZxingCatalogueService  {

    @Autowired
    ZxingCatalogueMapper zxingCatalogueMapper;


    @Override
    public List<ZxingCatalogue> seletelist(ZxingCatalogue zxingCatalogue) {

        QueryWrapper<ZxingCatalogue> queryWrapper =new QueryWrapper<>();
        if( zxingCatalogue.getId() !=null &&  !zxingCatalogue.getId().isEmpty()  ){
            queryWrapper.eq("id",zxingCatalogue.getId());
        }
        if( zxingCatalogue.getCatalogueName() !=null && !zxingCatalogue.getCatalogueName().isEmpty() ){
            queryWrapper.eq("catalogue_name",zxingCatalogue.getCatalogueName());
        }
        if( zxingCatalogue.getId() !=null &&  !zxingCatalogue.getId().isEmpty()  ){
            queryWrapper.eq("id",zxingCatalogue.getId());
        }
        if( zxingCatalogue.getInsertTime() !=null ){
            queryWrapper.eq("insert_time",zxingCatalogue.getInsertTime());
        }
        return zxingCatalogueMapper.selectList(queryWrapper);
    }

    @Override
    public List<ZxingCatalogueDto> seletelistCatalogueTemplate(ZxingCatalogueDto zxingCatalogue) {
        return  zxingCatalogueMapper.seletelistCatalogueTemplate(zxingCatalogue);
    }

}