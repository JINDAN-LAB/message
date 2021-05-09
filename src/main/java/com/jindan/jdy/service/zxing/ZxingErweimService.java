package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.ZxingErweimDto;
import com.jindan.jdy.common.pojo.ZxingErweim;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jindan.jdy.common.pojo.ZxingErweimakg;

import java.util.List;

/**   
 * @Description:TODO(二维码服务层)
 * @version: V1.0
 * @author: kong
 * 
 */
public interface ZxingErweimService extends IService<ZxingErweim> {

     Page<ZxingErweim> seleteListZxing(ZxingErweimDto zxingErweimDto);

    List<ZxingErweim> listseleteOne(String id);

    List<ZxingErweim> seleteIdlist(String id);

    List<ZxingErweimDto> seletelistseleteOne(String id);
}