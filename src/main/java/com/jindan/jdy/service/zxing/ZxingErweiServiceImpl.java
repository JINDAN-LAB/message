package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.ZxingErweiDto;
import com.jindan.jdy.common.pojo.ZxingErwei;
import com.jindan.jdy.common.mapper.ZxingErweiDao;
import com.jindan.jdy.common.pojo.ZxingErweim;
import com.jindan.jdy.service.zxing.ZxingErweiService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(二维码服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class ZxingErweiServiceImpl  extends ServiceImpl<ZxingErweiDao,ZxingErwei> implements ZxingErweiService  {

    @Autowired
    ZxingErweiDao zxingErweiDao;

    @Override
    public Page<ZxingErwei> seletelist(ZxingErweiDto zxingErweiDto) {

        if(zxingErweiDto.getCurrentPage() <= 0  || zxingErweiDto.getPageSize()  <= 0){
            zxingErweiDto.setCurrentPage(1);
        }
        Page<ZxingErwei> page =new Page<>(zxingErweiDto.getCurrentPage(),zxingErweiDto.getPageSize());
        QueryWrapper<ZxingErwei> queryWrapper =new QueryWrapper<>();
        if( zxingErweiDto.getSuttle() !=null &&  zxingErweiDto.getSuttle()!= ""  ){
            queryWrapper.like("suttle",zxingErweiDto.getSuttle());
        }
        if( zxingErweiDto.getNametitle() !=null &&  zxingErweiDto.getNametitle()!= ""  ){
            queryWrapper.like("nametitle",zxingErweiDto.getNametitle());
        }
        if( zxingErweiDto.getStandard() !=null && !zxingErweiDto.getStandard().isEmpty() ){
            queryWrapper.like("standard",zxingErweiDto.getStandard());
        }
        if( zxingErweiDto.getHanliang() !=null &&  zxingErweiDto.getHanliang()!= ""  ){
            queryWrapper.like("hanliang",zxingErweiDto.getHanliang());
        }
        if( zxingErweiDto.getTradename() !=null && !zxingErweiDto.getTradename().isEmpty() ){
            queryWrapper.like("tradename",zxingErweiDto.getTradename());
        }
        if( zxingErweiDto.getTab() !=null &&  zxingErweiDto.getTab()!= ""  ){
            queryWrapper.like("tab",zxingErweiDto.getTab());
        }
        if( zxingErweiDto.getKg() !=null && !zxingErweiDto.getKg().isEmpty() ){
            queryWrapper.like("kg",zxingErweiDto.getKg());
        }
        if( zxingErweiDto.getType() !=null &&  zxingErweiDto.getType()!= ""  ){
            queryWrapper.like("type",zxingErweiDto.getType());
        }
        if( zxingErweiDto.getBeizhu() !=null && !zxingErweiDto.getBeizhu().isEmpty() ){
            queryWrapper.like("beizhu",zxingErweiDto.getBeizhu());
        }
        Page<ZxingErwei> page1 = (Page<ZxingErwei>) zxingErweiDao.selectPage(page, queryWrapper);
        return page1;

    }


}