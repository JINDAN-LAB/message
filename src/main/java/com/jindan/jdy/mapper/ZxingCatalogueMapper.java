package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.ZxingCatalogueDto;
import com.jindan.jdy.common.pojo.ZxingCatalogue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(二维码目录数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface ZxingCatalogueMapper extends BaseMapper<ZxingCatalogue> {

    List<ZxingCatalogueDto> seletelistCatalogueTemplate(ZxingCatalogueDto queryWrapper);
}
