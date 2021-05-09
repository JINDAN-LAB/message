package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.ZxingErweimakg;
import com.jindan.jdy.common.mapper.ZxingErweimakgDao;
import com.jindan.jdy.service.zxing.ZxingErweimakgService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**   
 * @Description:TODO(自定义验证二维码域名目录服务实现)
 *
 * @version: V1.0
 * @author: kong
 * 
 */

@Component
public class ZxingErweimakgServiceImpl  extends ServiceImpl<ZxingErweimakgDao,ZxingErweimakg> implements ZxingErweimakgService  {

    @Autowired
    ZxingErweimakgDao zxingErweimakgDao;



    @Override
    public List<ZxingErweimakg> seletelist(ZxingErweimakg zxingErweimakg) {
        QueryWrapper<ZxingErweimakg> queryWrapper =new QueryWrapper<>();
      if( zxingErweimakg.getJingzhong() !=null &&  zxingErweimakg.getJingzhong()!= ""  ){
            queryWrapper.like("jingzhong",zxingErweimakg.getJingzhong());
        }
        if( zxingErweimakg.getMaozhong() !=null &&  zxingErweimakg.getMaozhong()!= ""  ){
            queryWrapper.like("maozhong",zxingErweimakg.getMaozhong());
        }
        if( zxingErweimakg.getInsertTime() !=null ){
            queryWrapper.like("insert_time",zxingErweimakg.getInsertTime());
        }
        if( zxingErweimakg.getPid() !=null &&  !zxingErweimakg.getPid().isEmpty()  ){
            queryWrapper.like("pid",zxingErweimakg.getPid());
        }
        return zxingErweimakgDao.selectList(queryWrapper);
    }
}