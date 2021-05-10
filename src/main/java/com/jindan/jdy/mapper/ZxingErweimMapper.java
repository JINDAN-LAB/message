package com.jindan.jdy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jindan.jdy.common.dto.ZxingErweimDto;
import com.jindan.jdy.common.pojo.ZxingErweim;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**   
 * @Description:TODO(二维码数据访问层)
 *
 * @version: V1.0
 * @author: kong
 * 
 */
@Mapper
public interface ZxingErweimMapper extends BaseMapper<ZxingErweim> {

    List<ZxingErweimDto> seletelistseleteOne(String id);

}
