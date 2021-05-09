package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jindan.jdy.common.dto.ZxingErweimDto;
import com.jindan.jdy.common.pojo.ZxingErweim;
import com.jindan.jdy.common.mapper.ZxingErweimMapper;

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
public class ZxingErweimServiceImpl extends ServiceImpl<ZxingErweimMapper, ZxingErweim> implements ZxingErweimService {

    @Autowired
    ZxingErweimMapper zxingErweimDao;


    @Override
    public  Page<ZxingErweim> seleteListZxing(ZxingErweimDto zxingErweimDto) {

        if(zxingErweimDto.getCurrentPage() <= 0){
            zxingErweimDto.setCurrentPage(1);
        }
        Page<ZxingErweim> page =new Page<>(zxingErweimDto.getCurrentPage(),zxingErweimDto.getPageSize());
        QueryWrapper<ZxingErweim> queryWrapper =new QueryWrapper<>();
        if( zxingErweimDto.getNametitle() !=null &&  zxingErweimDto.getNametitle()  != ""  ){
            queryWrapper.like("nametitle",zxingErweimDto.getNametitle());
        }
        if( zxingErweimDto.getSuttle() !=null && !zxingErweimDto.getSuttle().isEmpty() ){
            queryWrapper.like("suttle",zxingErweimDto.getSuttle());
        }
        if(zxingErweimDto.getMd() !=null && !zxingErweimDto.getMd().isEmpty()){
            queryWrapper.eq("md",zxingErweimDto.getMd());
        }
        if( zxingErweimDto.getNametitle() !=null && !zxingErweimDto.getNametitle().isEmpty() ){
            queryWrapper.like("nametitle",zxingErweimDto.getNametitle());
        }
        if(zxingErweimDto.getHanliang() !=null && !zxingErweimDto.getHanliang().isEmpty()){
            queryWrapper.like("hanliang",zxingErweimDto.getHanliang());
        }
        queryWrapper.orderByDesc("dateproduced");
        queryWrapper.orderByDesc("insert_time");
        Page<ZxingErweim> page1 = (Page<ZxingErweim>) zxingErweimDao.selectPage(page, queryWrapper);
        return page1;
    }


    @Override
    public List<ZxingErweim> listseleteOne(String id) {
        QueryWrapper<ZxingErweim> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("md",id);
        return zxingErweimDao.selectList(queryWrapper);
    }

    @Override
    public List<ZxingErweim> seleteIdlist(String id) {
        QueryWrapper<ZxingErweim> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("md",id);
        return zxingErweimDao.selectList(queryWrapper);
    }

    @Override
    public List<ZxingErweimDto> seletelistseleteOne(String id) {
        return zxingErweimDao.seletelistseleteOne(id);
    }


}