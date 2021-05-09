package com.jindan.jdy.service.zxing;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jindan.jdy.common.pojo.ZxingMethod;
import com.jindan.jdy.common.pojo.ZxingStand;
import com.jindan.jdy.common.mapper.ZxingStandDao;
import com.jindan.jdy.service.zxing.ZxingStandService;

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
public class ZxingStandServiceImpl  extends ServiceImpl<ZxingStandDao,ZxingStand> implements ZxingStandService  {

    @Autowired
    ZxingStandDao zxingStandDao;


    @Override
    public List<ZxingStand> seletelist(ZxingStand zxingStand) {
        QueryWrapper<ZxingStand> queryWrapper =new QueryWrapper<>();
        if( zxingStand.getStandard() !=null &&  zxingStand.getStandard()!= ""  ){
            queryWrapper.like("standard",zxingStand.getStandard());
        }
        if( zxingStand.getSubmitperson() !=null &&  zxingStand.getSubmitperson()!= ""  ){
            queryWrapper.like("submitperson",zxingStand.getSubmitperson());
        }
        if( zxingStand.getId() !=null &&  zxingStand.getId()!= ""  ){
            queryWrapper.eq("id",zxingStand.getId());
        }
        if( zxingStand.getInsertTime() !=null){
            queryWrapper.like("insert_time",zxingStand.getInsertTime());
        }
        return zxingStandDao.selectList(queryWrapper);
    }



}